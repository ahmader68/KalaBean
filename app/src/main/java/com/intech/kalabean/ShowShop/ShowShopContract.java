package com.intech.kalabean.ShowShop;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.ProductList;

public interface ShowShopContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getProductList(ProductList productList);
    }

    interface Presenter extends BasePresenter<View>{
        void getProduct(int shopID);
    }
}
