package com.intek.kalabean.Complex;

import com.intek.kalabean.Data.KalaBeanDataSource;

public class ComplexPresenter implements ComplexContract.Presenter {
    private ComplexContract.View view;
    KalaBeanDataSource kalaBeanDataSource;
    public ComplexPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(ComplexContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
