package com.intek.kalabean.Chain_Store;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ChainStoreList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChainPresenter implements ChainContract.Presenter {
    private static ChainContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 5;

    public ChainPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(ChainContract.View view) {
        ChainPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getChainStore(int SellCenterCatID, int CityId) {

        DatabaseMethods.getChainStore(databaseFlag,SellCenterCatID,CityId);
    }

    @Override
    public void onSuccessGetChainStore(ChainStoreList chainStoreList) {
        ChainPresenter.view.getChainStoreList(chainStoreList);
    }

    @Override
    public void onError(String message) {
        ChainPresenter.view.showMessage(message);
    }
}
