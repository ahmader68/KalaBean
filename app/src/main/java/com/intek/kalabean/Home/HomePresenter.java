package com.intek.kalabean.Home;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.ShopsList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {
    private static HomeContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 7;

    public HomePresenter(KalaBeanDataSource kalaBeanDataSource) {
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(HomeContract.View view) {
        HomePresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if (compositeDisposable != null && compositeDisposable.size() > 0) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void getOrderList() {

    }

    @Override
    public void getProductList(int ShopId) {
        DatabaseMethods.getProductList(databaseFlag,ShopId);
    }

    @Override
    public void getReductedProductList(int ShopId) {
        DatabaseMethods.getReductedProductList(databaseFlag,ShopId);
    }

    @Override
    public void getShopList(int SellCenterID, int FloorID) {
        DatabaseMethods.getShopList(databaseFlag,SellCenterID,FloorID);
    }

    @Override
    public void onSuccessGetProductList(ProductList productList) {
        HomePresenter.view.showProductList(productList);
    }

    @Override
    public void onSuccessGetReductedProductList(ProductList productList) {
        HomePresenter.view.showReductedProductList(productList);
    }

    @Override
    public void onSuccessGetShopList(ShopsList shopsList) {

    }

    @Override
    public void onError(String message) {
        HomePresenter.view.showError(message);
    }
}
