package com.intek.kalabean.ShowUserShop;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.UserShop;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserShopPresenter implements UserShopContract.Presenter {

    private static UserShopContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 13;

    public UserShopPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(UserShopContract.View view) {
        UserShopPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getUserShop(int CreatorId) {
        DatabaseMethods.getUserShop(databaseFlag,CreatorId);
    }

    @Override
    public void getProduct(int shopID) {
        DatabaseMethods.getProductList(databaseFlag,shopID);
    }

    @Override
    public void onSuccessGetProduct(ProductList productList) {
        UserShopPresenter.view.getProductList(productList);
    }

    @Override
    public void onError(String message) {
        UserShopPresenter.view.showMessage(message);
    }

    @Override
    public void onSuccessUserShop(UserShop userShop) {
        UserShopPresenter.view.showUserShop(userShop);
    }
}
