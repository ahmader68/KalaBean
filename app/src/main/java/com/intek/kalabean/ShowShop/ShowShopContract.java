package com.intek.kalabean.ShowShop;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ProductList;

public interface ShowShopContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getProductList(ProductList productList);
    }

    interface Presenter extends BasePresenter<View>{
        void getProduct(int shopID);
        void onSuccessGetProduct(ProductList productList);
        void onError(String message);
    }
}
