package com.intek.kalabean.Oridinary_User_Profile;

import com.intek.kalabean.Data.KalaBeanDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class OUPresenter implements OUContract.Presenter {
    private OUContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private KalaBeanDataSource kalaBeanDataSource;
    public OUPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(OUContract.View view) {
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
    public void getFavShops() {

    }

    @Override
    public void getFavProd() {

    }
}
