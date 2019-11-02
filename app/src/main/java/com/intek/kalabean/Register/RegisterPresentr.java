package com.intek.kalabean.Register;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.User;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresentr implements RegisterContract.Presenter {
    private KalaBeanDataSource kalaBeanDataSource;
    private RegisterContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public RegisterPresentr(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void register(User user) {
        kalaBeanDataSource.register(user).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(User user) {
                        view.showSuccess(user.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }

    @Override
    public void login(User user) {
        kalaBeanDataSource.login(user).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoggedinUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(LoggedinUser user) {
                        int id = user.getItems().get(0).getResult();
                        if(id <= -1 && id >= -3){
                            switch (id){
                                case -1:
                                    view.showMessage("نام کاربری یا کلمه عبور صحیح نمی باشد");
                                    break;
                                case -2:
                                    view.showMessage("حساب کاربری شما یرفعال شده است");
                                    break;
                                case -3:
                                    view.showMessage("هیچ کاربری با این مشخصات یافت نشد");
                                    break;
                            }
                        }else{
                            view.successLogin(user);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
