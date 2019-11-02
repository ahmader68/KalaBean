package com.intek.kalabean.Request_Product;

import com.intek.kalabean.Data.KalaBeanDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class RequestPresenter implements RequestContract.Presenter {

    private RequestContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable;

    public RequestPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void attachView(RequestContract.View view) {
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
    public void activityKind() {

    }
}
