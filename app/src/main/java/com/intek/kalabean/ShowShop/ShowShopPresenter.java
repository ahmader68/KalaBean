package com.intek.kalabean.ShowShop;
import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ProductList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShowShopPresenter implements ShowShopContract.Presenter {

    private static ShowShopContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 12;

    public ShowShopPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }


    @Override
    public void attachView(ShowShopContract.View view) {
        ShowShopPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getProduct(int shopID) {
        DatabaseMethods.getProductList(databaseFlag,shopID);

    }

    @Override
    public void onSuccessGetProduct(ProductList productList) {
        ShowShopPresenter.view.getProductList(productList);
    }

    @Override
    public void onError(String message) {
        ShowShopPresenter.view.showMessage(message);
    }
}
