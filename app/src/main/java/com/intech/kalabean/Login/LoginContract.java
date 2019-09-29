package com.intech.kalabean.Login;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.User;

public interface LoginContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void loginSuccess(User user);
    }
    interface Presenter extends BasePresenter<View>{
        void login(User user);
    }
}
