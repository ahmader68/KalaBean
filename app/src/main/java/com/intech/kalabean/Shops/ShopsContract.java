package com.intech.kalabean.Shops;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.ShopsList;

public interface ShopsContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void getShopsList(ShopsList shops);
    }

    interface Presenter extends BasePresenter<View> {
        void getShops(int SellCenterID , int FloorID);
    }
}
