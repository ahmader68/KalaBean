package com.intek.kalabean.AddProduct;

import com.intek.kalabean.Data.KalaBeanDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class AddProductPresenter implements AddProductContract.Presenter {

    private AddProductContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public AddProductPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(AddProductContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }
}
