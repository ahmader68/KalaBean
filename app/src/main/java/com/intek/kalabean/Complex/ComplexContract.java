package com.intek.kalabean.Complex;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ComplexList;

public interface ComplexContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getComplexList(ComplexList complexList);
    }
    interface Presenter extends BasePresenter<View>{
        void getComplex(int SellCenterCatID, int CityId);
    }
}
