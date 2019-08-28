package com.intek.kalabean.Complex;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;

public interface ComplexContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}