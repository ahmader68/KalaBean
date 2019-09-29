package com.intech.kalabean.ShowUserShop;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.UserShop;

public interface UserShopContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void showUserShop(UserShop userShop);
    }
    interface Presenter extends BasePresenter<View>{
        void getUserShop(int CreatorId);
    }
}
