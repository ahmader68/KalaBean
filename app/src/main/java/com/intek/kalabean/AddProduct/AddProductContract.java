package com.intek.kalabean.AddProduct;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.AddProduct;
import com.intek.kalabean.Model.Positions;

public interface AddProductContract {
    interface View extends BaseView {
        void showMesssage(String msg);
        void showSuccess(int id);
        void getActivityKind(ActivityKindList activityKindList);
        void getSubCatId(Positions positions);
    }

    interface Presenter extends BasePresenter<View> {
        void insertProduct(AddProduct product);
        void activityKind();
        void subCatid();
    }
}
