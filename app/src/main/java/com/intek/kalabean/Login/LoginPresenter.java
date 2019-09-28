package com.intek.kalabean.Login;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.User;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public LoginPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(LoginContract.View view) {
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
    public void login(User user) {
        kalaBeanDataSource.login(user).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoggedinUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(LoggedinUser loggedinUser) {
                        int id = loggedinUser.getItems().get(0).getResult();
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
                            view.loginSuccess(loggedinUser);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
