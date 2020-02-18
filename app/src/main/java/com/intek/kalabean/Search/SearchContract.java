package com.intek.kalabean.Search;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.ShopCenterList;

public interface SearchContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void getStoreKind(MallKindList mallKindList);
        void getActivityKind(ActivityKindList activityKindList);
        void getShopCenterList(ShopCenterList shopCenterList);
        void getFloorList(FloorList floorList);
    }

    interface Presenter extends BasePresenter<View>{

        void storeKind();
        void activityKind();
        void shopCenterList(int centerCatId);
        void floorList(int idCenter);
        void onSuccessActivityKind(ActivityKindList activityKindList);
        void onSuccessStoreKind(MallKindList mallKindList);
        void onSuccessShopCenterList(ShopCenterList shopCenterList);
        void onSuccessFloorList(FloorList floorList);
        void onError(String msg);
    }
}
