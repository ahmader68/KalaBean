package com.intek.kalabean.VIP_User_Profile;

import com.intek.kalabean.Data.KalaBeanDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class VUPresenter implements VUContract.Presenter {
    private VUContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private KalaBeanDataSource kalaBeanDataSource;
    public VUPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(VUContract.View view) {
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
