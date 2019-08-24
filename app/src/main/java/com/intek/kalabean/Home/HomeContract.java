package com.intek.kalabean.Home;

import android.view.View;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;

public interface HomeContract {
    interface View extends BaseView {

    }
    interface Presenter extends BasePresenter<View> {

    }
}
