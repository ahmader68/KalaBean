package com.intek.kalabean.Home;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.ShopsList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public HomePresenter(KalaBeanDataSource kalaBeanDataSource) {
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
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
        kalaBeanDataSource.getProduct(ShopId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProductList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ProductList productList) {
                        view.showProductList(productList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.toString());
                    }
                });
    }

    @Override
    public void getReductedProductList(int ShopId) {
        kalaBeanDataSource.getProduct(ShopId).subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProductList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ProductList productList) {
                        view.showReductedProductList(productList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.toString());
                    }
                });
    }

    @Override
    public void getShopList(int SellCenterID, int FloorID) {
        kalaBeanDataSource.getShops(SellCenterID , FloorID).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ShopsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ShopsList shopsList) {
                        view.showNewJob(shopsList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.toString());
                    }
                });
    }
}
