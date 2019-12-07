package com.intek.kalabean.Main_Page;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;

public interface MainContract {
    interface View extends BaseView{
        void showMessage(String msg);
    }
    interface Presenter extends BasePresenter<View>{

    }
}
