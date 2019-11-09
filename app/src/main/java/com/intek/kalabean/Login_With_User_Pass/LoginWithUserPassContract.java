package com.intek.kalabean.Login_With_User_Pass;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.User;

public interface LoginWithUserPassContract {

    interface View extends BaseView {
        void showMessage(String msg);
        void loginSuccess(LoggedinUser loggedinUser);
    }

    interface Presenter extends BasePresenter<View>{
        void login(User user);
        void onSuccessLogin(LoggedinUser loggedinUser);
        void onError(String message);
    }
}
