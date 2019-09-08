package com.intek.kalabean.Markets;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.Store;

import java.util.List;

public interface MarketsContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void getMarketList(List<Store> stores);
    }
    interface Presenter extends BasePresenter<View>{
        void getMarkets(int catId , int cityId);
    }
}
