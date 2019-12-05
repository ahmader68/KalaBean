package com.intek.kalabean.Shops;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.ShopsList;

import java.sql.DataTruncation;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopsPresenter implements ShopsContract.Presenter {

    private static ShopsContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 11;

    public ShopsPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void getShops(int SellCenterID, int FloorID) {
        DatabaseMethods.getShopList(databaseFlag,SellCenterID,FloorID);
    }

    @Override
    public void onSuccessGetShops(ShopsList shopsList) {
        ShopsPresenter.view.getShopsList(shopsList);
    }

    @Override
    public void onSuccessGetFloor(FloorList floorList) {
        ShopsPresenter.view.getFloorList(floorList);
    }

    @Override
    public void onError(String message) {
        ShopsPresenter.view.showMessage(message);
    }

    @Override
    public void floorList(int idCenter) {
        DatabaseMethods.getFloorList(databaseFlag , idCenter);
    }

    @Override
    public void attachView(ShopsContract.View view) {
        ShopsPresenter.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }
}
