package com.intech.kalabean.Markets;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.StoreList;

public interface MarketsContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getMarketList(StoreList stores);
    }
    interface Presenter extends BasePresenter<View>{
        void getMarkets(int SellCenterCatID , int CityId);
    }
}
