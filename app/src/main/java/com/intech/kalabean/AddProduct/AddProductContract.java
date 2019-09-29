package com.intech.kalabean.AddProduct;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;

public interface AddProductContract {
    interface View extends BaseView {
        void showMesssage(String msg);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
