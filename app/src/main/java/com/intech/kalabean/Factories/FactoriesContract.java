package com.intech.kalabean.Factories;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;

public interface FactoriesContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
