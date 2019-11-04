package com.intek.kalabean.Brands;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.BrandList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BrandsPresenter implements BrandsContract.Presenter {

    private static BrandsContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 4;

    public BrandsPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;

    }

    @Override
    public void getBrands(int SellCenterCatID) {

        DatabaseMethods.getBrands(databaseFlag,SellCenterCatID);
    }

    @Override
    public void onSuccessGetBrand(BrandList brandList) {
        BrandsPresenter.view.getBrandsList(brandList);
    }

    @Override
    public void onError(String message) {
        BrandsPresenter.view.showMessage(message);
    }

    @Override
    public void attachView(BrandsContract.View view) {
        BrandsPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }
}
