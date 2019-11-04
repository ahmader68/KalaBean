package com.intek.kalabean.ShowUserShop;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.UserShop;

public interface UserShopContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void showUserShop(UserShop userShop);
        void getProductList(ProductList productList);
    }
    interface Presenter extends BasePresenter<View>{
        void getUserShop(int CreatorId);
        void getProduct(int shopID);
        void onSuccessGetProduct(ProductList productList);
        void onError(String message);
        void onSuccessUserShop(UserShop userShop);
    }
}
