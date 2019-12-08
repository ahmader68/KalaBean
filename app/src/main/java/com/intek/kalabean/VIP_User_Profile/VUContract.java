package com.intek.kalabean.VIP_User_Profile;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ShopCenter;
import com.intek.kalabean.Model.ShopCenterList;

public interface VUContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getShopInfo(ShopCenterList shopCenter);
    }
    interface Presenter extends BasePresenter<View>{
        void getShopInfo(int shopId, int usrid);
        void onSuccessGetShopInfo(ShopCenterList shopCenter);
        void onError(String msg);
    }
}
