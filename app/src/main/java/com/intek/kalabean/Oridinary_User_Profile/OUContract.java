package com.intek.kalabean.Oridinary_User_Profile;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;

public interface OUContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void showFavShop();
        void showFavProd();
    }
    interface Presenter extends BasePresenter<View>{
        void getFavShops();
        void getFavProd();
    }
}
