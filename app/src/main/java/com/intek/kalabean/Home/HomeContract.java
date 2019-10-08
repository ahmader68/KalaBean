package com.intek.kalabean.Home;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.Order;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void showError(String msg);
        void showOrderList(List<Order> orders);
    }
    interface Presenter extends BasePresenter<View> {
        void getOrderList();
    }
}
