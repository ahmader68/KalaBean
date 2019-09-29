package com.intech.kalabean.Complex;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.ComplexList;

public interface ComplexContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getComplexList(ComplexList complexList);
    }
    interface Presenter extends BasePresenter<View>{
        void getComplex(int SellCenterCatID, int CityId);
    }
}
