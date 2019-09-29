package com.intech.kalabean.Chain_Store;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.ChainStoreList;

public interface ChainContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getChainStoreList(ChainStoreList chainStoreList);
    }
    interface Presenter extends BasePresenter<View>{
        void getChainStore(int SellCenterCatID , int CityId);
    }
}
