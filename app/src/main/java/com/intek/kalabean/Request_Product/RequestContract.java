package com.intek.kalabean.Request_Product;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;

public interface RequestContract {
    interface  View extends BaseView{
        void showMessage(String msg);
        void getActivityKind();
    }

    interface Presenter extends BasePresenter<View>{
        void activityKind();
    }
}
