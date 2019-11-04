package com.intek.kalabean.Request_Product;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ActivityKindList;

public interface RequestContract {
    interface  View extends BaseView{
        void showMessage(String msg);
        void getActivityKindRequest(ActivityKindList activityKindList);
    }

    interface Presenter extends BasePresenter<View>{
        void activityKind();
        void onSuccess(ActivityKindList activityKindList);
        void onError(String message);
    }
}
