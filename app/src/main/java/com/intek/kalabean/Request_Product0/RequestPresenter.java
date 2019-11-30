package com.intek.kalabean.Request_Product0;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ActivityKindList;


import io.reactivex.disposables.CompositeDisposable;


public class RequestPresenter implements RequestContract.Presenter {

    private static RequestContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 2;

    public RequestPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;

    }
    @Override
    public void attachView(RequestContract.View view) {
        RequestPresenter.view = view;
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
        DatabaseMethods.getActivityKind(databaseFlag);
    }

    @Override
    public void onSuccess(ActivityKindList activityKindList) {
        RequestPresenter.view.getActivityKindRequest(activityKindList);
    }

    @Override
    public void onError(String message) {

        RequestPresenter.view.showMessage(message);

    }
}
