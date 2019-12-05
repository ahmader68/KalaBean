package com.intek.kalabean.Shops;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.ShopsList;

public interface ShopsContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void getShopsList(ShopsList shops);void
        getFloorList(FloorList floorList);
    }

    interface Presenter extends BasePresenter<View> {
        void getShops(int SellCenterID , int FloorID);
        void onSuccessGetShops(ShopsList shopsList);
        void onSuccessGetFloor(FloorList floorList);
        void onError(String message);
        void floorList(int idCenter);
    }
}
