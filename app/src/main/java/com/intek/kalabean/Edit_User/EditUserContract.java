package com.intek.kalabean.Edit_User;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.User;

public interface EditUserContract {
    interface View extends BaseView {
        void showUserInfo(User user);
        void showMessage(String msg);
        void showEditSuccess(int result);
    }
    interface Presenter extends BasePresenter<View>{
        void userInfo(int userId);
        void editUser(int uid,String mobile,String email);
        void onSuccessEditUser(User user);
        void onSuccessGetUserInfo(User user);
        void onError(String msg);
    }
}
