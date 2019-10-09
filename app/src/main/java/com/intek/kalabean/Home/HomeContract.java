package com.intek.kalabean.Home;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.Order;
import com.intek.kalabean.Model.ProductList;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void showError(String msg);
        void showOrderList(List<Order> orders);
        void showProductList(ProductList productList);
    }
    interface Presenter extends BasePresenter<View> {
        void getOrderList();
        void getProductList(int ShopId);
    }
}
