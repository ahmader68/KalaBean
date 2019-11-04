package com.intek.kalabean.Shops;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ShopsList;

public interface ShopsContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void getShopsList(ShopsList shops);
    }

    interface Presenter extends BasePresenter<View> {
        void getShops(int SellCenterID , int FloorID);
        void onSuccessGetShops(ShopsList shopsList);
        void onError(String message);
    }
}
