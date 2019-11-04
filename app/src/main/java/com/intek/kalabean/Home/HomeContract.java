package com.intek.kalabean.Home;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.Order;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.ShopsList;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void showError(String msg);
        void showOrderList(List<Order> orders);
        void showProductList(ProductList productList);
        void showReductedProductList(ProductList productList);
        void showNewJob(ShopsList shopsList);
    }
    interface Presenter extends BasePresenter<View> {
        void getOrderList();
        void getProductList(int ShopId);
        void getReductedProductList(int ShopId);
        void getShopList(int SellCenterID , int FloorID);
        void onSuccessGetProductList(ProductList productList);
        void onSuccessGetReductedProductList(ProductList productList);
        void onSuccessGetShopList(ShopsList shopsList);
        void onError(String message);
    }
}
