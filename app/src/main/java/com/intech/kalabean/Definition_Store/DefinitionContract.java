package com.intech.kalabean.Definition_Store;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.ActivityKindList;
import com.intech.kalabean.Model.FloorList;
import com.intech.kalabean.Model.MallKindList;
import com.intech.kalabean.Model.ShopCenterList;
import com.intech.kalabean.Model.StoreDif;

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
    }
}
