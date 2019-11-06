package com.intek.kalabean.Register;

import com.intek.kalabean.Classes.DatabaseMethods;
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
    private static RegisterContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 10;
    public RegisterPresentr(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(RegisterContract.View view) {
        RegisterPresentr.view = view;
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
        DatabaseMethods.register(user);
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
                    RegisterPresentr.view.showMessage("نام کاربری یا کلمه عبور صحیح نمی باشد");
                    break;
                case -2:
                    RegisterPresentr.view.showMessage("حساب کاربری شما یرفعال شده است");
                    break;
                case -3:
                    RegisterPresentr.view.showMessage("هیچ کاربری با این مشخصات یافت نشد");
                    break;
            }
        }else{
            RegisterPresentr.view.successLogin(loggedinUser);
        }
    }

    @Override
    public void onSuccessRegister(User user) {
        RegisterPresentr.view.showSuccess(user.getResult());
    }

    @Override
    public void onError(String message) {
        RegisterPresentr.view.showMessage(message);
    }
}
