package com.intek.kalabean.Factories;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;

public interface FactoriesContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
