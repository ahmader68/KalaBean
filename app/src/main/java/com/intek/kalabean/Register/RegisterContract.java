package com.intek.kalabean.Register;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.User;

public interface RegisterContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void showSuccess(int id);
    }
    interface Presenter extends BasePresenter<View> {
        void register(User user);
    }
}
