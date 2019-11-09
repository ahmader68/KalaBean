package com.intek.kalabean.Login_With_User_Pass;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Login.LoginPresenter;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.User;

import io.reactivex.disposables.CompositeDisposable;

public class LoginWithUserPassPresenter implements LoginWithUserPassContract.Presenter {

    private static LoginWithUserPassContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 9;

    public LoginWithUserPassPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(LoginWithUserPassContract.View view) {
       LoginWithUserPassPresenter.view = view;
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
        int id = 0;
        if (loggedinUser.getItems() != null) {
            id = loggedinUser.getItems().get(0).getResult();
        }else {
            id = -1;
        }
        if(id < 0){
            switch (id){
                case -1:
                    LoginWithUserPassPresenter.view.showMessage("نام کاربری یا کلمه عبور صحیح نمی باشد");
                    break;
                case -2:
                    LoginWithUserPassPresenter.view.showMessage("حساب کاربری شما یرفعال شده است");
                    break;
                case -3:
                    LoginWithUserPassPresenter.view.showMessage("هیچ کاربری با این مشخصات یافت نشد");
                    break;
            }
        }else if(loggedinUser.getItems() != null){

            LoginWithUserPassPresenter.view.loginSuccess(loggedinUser);
        }else{
            LoginWithUserPassPresenter.view.showMessage("نام کاربری یا کلمه عبور صحیح نمی باشد");
        }
    }

    @Override
    public void onError(String message) {
        LoginWithUserPassPresenter.view.showMessage(message);
    }
}
