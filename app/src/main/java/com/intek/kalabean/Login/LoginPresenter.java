package com.intek.kalabean.Login;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.User;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {
    private static LoginContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = -1;
    public LoginPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(LoginContract.View view) {
        LoginPresenter.view = view;
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
        DatabaseMethods.login(databaseFlag,user);
    }

    @Override
    public void onSuccessLogin(LoggedinUser loggedinUser) {
        int id = loggedinUser.getItems().get(0).getResult();
        if(id <= -1 && id >= -3){
            switch (id){
                case -1:
                    LoginPresenter.view.showMessage("نام کاربری یا کلمه عبور صحیح نمی باشد");
                    break;
                case -2:
                    LoginPresenter.view.showMessage("حساب کاربری شما یرفعال شده است");
                    break;
                case -3:
                    LoginPresenter.view.showMessage("هیچ کاربری با این مشخصات یافت نشد");
                    break;
            }
        }else{
            LoginPresenter.view.loginSuccess(loggedinUser);
        }
    }

    @Override
    public void onError(String message) {
        LoginPresenter.view.showMessage(message);
    }
}
