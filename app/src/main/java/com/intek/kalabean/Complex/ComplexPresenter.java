package com.intek.kalabean.Complex;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ComplexList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ComplexPresenter implements ComplexContract.Presenter {
    private static ComplexContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 6;
    public ComplexPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(ComplexContract.View view) {
        ComplexPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getComplex(int SellCenterCatID, int CityId) {

        DatabaseMethods.getComplex(databaseFlag,SellCenterCatID,CityId);
    }

    @Override
    public void onSuccessGetComplex(ComplexList complexList) {
        ComplexPresenter.view.getComplexList(complexList);
    }

    @Override
    public void onError(String message) {
        ComplexPresenter.view.showMessage(message);
    }
}
