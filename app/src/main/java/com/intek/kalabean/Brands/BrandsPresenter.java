package com.intek.kalabean.Brands;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.BrandList;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BrandsPresenter implements BrandsContract.Presenter {

    private BrandsContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable;

    BrandsPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getBrands(int SellCenterCatID) {

        kalaBeanDataSource.getBrands(SellCenterCatID).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BrandList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(BrandList brandList) {
                        view.getBrandsList(brandList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void attachView(BrandsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }
}
