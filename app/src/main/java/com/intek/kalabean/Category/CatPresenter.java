package com.intek.kalabean.Category;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Data.KalaBeanDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class CatPresenter implements CatContract.Presenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private KalaBeanDataSource kalaBeanDataSource;
    private CatContract.View view;

    public CatPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(CatContract.View view) {
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
