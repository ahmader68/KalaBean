package com.intek.kalabean.Chain_Store;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.ChainStoreList;

public interface ChainContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getChainStoreList(ChainStoreList chainStoreList);
    }
    interface Presenter extends BasePresenter<View>{
        void getChainStore(int SellCenterCatID , int CityId);
    }
}
