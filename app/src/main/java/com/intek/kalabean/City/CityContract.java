package com.intek.kalabean.City;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;

public interface CityContract {
    interface View extends BaseView{
        void showMessage(String msg);

    }

    interface Presenter extends BasePresenter<View>{

    }
}
