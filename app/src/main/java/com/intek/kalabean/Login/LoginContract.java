package com.intek.kalabean.Login;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.User;

public interface LoginContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void loginSuccess(LoggedinUser user);
    }
    interface Presenter extends BasePresenter<View>{
        void login(User user);
    }
}
