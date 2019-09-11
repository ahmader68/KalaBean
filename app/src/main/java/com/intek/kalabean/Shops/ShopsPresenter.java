package com.intek.kalabean.Shops;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ShopsList;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopsPresenter implements ShopsContract.Presenter {

    private ShopsContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ShopsPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void getShops(int SellCenterID, int FloorID) {
        kalaBeanDataSource.getShops(SellCenterID , FloorID).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ShopsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ShopsList shopsList) {
                        view.getShopsList(shopsList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }

    @Override
    public void attachView(ShopsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }
}
