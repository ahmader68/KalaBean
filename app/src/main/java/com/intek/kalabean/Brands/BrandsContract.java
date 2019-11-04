package com.intek.kalabean.Brands;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.BrandList;

public interface BrandsContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getBrandsList(BrandList brandLists);
    }

    interface Presenter extends BasePresenter<View>{
        void getBrands(int SellCenterCatID);
        void onSuccessGetBrand(BrandList brandList);
        void onError(String message);
    }
}
