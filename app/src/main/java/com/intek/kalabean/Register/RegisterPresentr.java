package com.intek.kalabean.Register;

import com.intek.kalabean.Data.KalaBeanDataSource;
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
                        view.showSuccess(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
