package com.intek.kalabean.Markets;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.StoreList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MarketsPresenter implements MarketsContract.Presenter {
    private static MarketsContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 8;


    public MarketsPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(MarketsContract.View view) {
        MarketsPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getMarkets(int SellCenterCatID , int CityCenterID) {

        DatabaseMethods.getMarkets(databaseFlag,SellCenterCatID,CityCenterID);
    }

    @Override
    public void onSuccessGetMarket(StoreList storeList) {
        MarketsPresenter.view.getMarketList(storeList);
    }

    @Override
    public void onError(String message) {
        MarketsPresenter.view.showMessage(message);
    }
}
