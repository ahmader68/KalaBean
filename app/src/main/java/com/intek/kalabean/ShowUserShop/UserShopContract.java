package com.intek.kalabean.ShowUserShop;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.UserShop;

public interface UserShopContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void showUserShop(UserShop userShop);
    }
    interface Presenter extends BasePresenter<View>{
        void getUserShop(int CreatorId);
    }
}
