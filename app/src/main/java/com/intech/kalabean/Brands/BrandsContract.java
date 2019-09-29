package com.intech.kalabean.Brands;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.BrandList;

public interface BrandsContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getBrandsList(BrandList brandLists);
    }

    interface Presenter extends BasePresenter<View>{
        void getBrands(int SellCenterCatID);
    }
}
