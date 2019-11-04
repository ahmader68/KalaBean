package com.intek.kalabean.Definition_Store;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.ShopCenterList;
import com.intek.kalabean.Model.StoreDif;

public interface DefinitionContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getStoreKind(MallKindList mallKindList);
        void getActivityKind(ActivityKindList activityKindList);
        void getShopCenterList(ShopCenterList shopCenterList);
        void getFloorList(FloorList floorList);
        void getStoreId(StoreDif storeDif);
    }
    interface Presenter extends BasePresenter<View>{
        void storeKind();
        void activityKind();
        void ShopCenterList(int idCenterCat);
        void floorList(int idCenter);
        void storeDefinition(StoreDif storeDif);

        void onSuccess(ActivityKindList activityKindList);
        void onSuccessStoreKind(MallKindList mallKindList);
        void onSuccessShopCenterList(ShopCenterList shopCenterList);
        void onSuccessFloorList(FloorList floorList);
        void onSuccessStoreDefinition(StoreDif storeDif);
        void onError(String message);
    }
}
