package com.intek.kalabean.Markets;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.Store;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MarketsPresenter implements MarketsContract.Presenter {
    private MarketsContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public MarketsPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(MarketsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getMarkets(int catId , int cityId) {

        kalaBeanDataSource.getMarkets(catId , cityId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Store>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Store> stores) {
                        view.getMarketList(stores);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
