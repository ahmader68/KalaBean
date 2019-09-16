package com.intek.kalabean.ShowShop;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.Product;

import java.util.List;

public interface ShowShopContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getProductList(List<Product> photoURL);
    }

    interface Presenter extends BasePresenter<View>{
        void getProduct(int shopID);
    }
}
