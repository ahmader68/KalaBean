package com.intech.kalabean.Register;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.User;

public interface RegisterContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void showSuccess(int id);
    }
    interface Presenter extends BasePresenter<View> {
        void register(User user);
    }
}
