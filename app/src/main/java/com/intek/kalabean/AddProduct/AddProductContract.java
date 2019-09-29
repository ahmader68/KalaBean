package com.intek.kalabean.AddProduct;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;

public interface AddProductContract {
    interface View extends BaseView {
        void showMesssage(String msg);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
