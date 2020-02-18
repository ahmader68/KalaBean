package com.intek.kalabean.Search;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.ShopCenterList;

import io.reactivex.disposables.CompositeDisposable;

public class SearchPresenter implements SearchContract.Presenter {
    private static SearchContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final int databaseFlag = 17;
    public SearchPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(SearchContract.View view) {
        SearchPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void storeKind() {
        DatabaseMethods.getStoreKind(databaseFlag);
    }

    @Override
    public void activityKind() {
        DatabaseMethods.getActivityKind(databaseFlag);
    }

    @Override
    public void shopCenterList(int centerCatId) {
        DatabaseMethods.getShopCenterList(databaseFlag,centerCatId);
    }

    @Override
    public void floorList(int idCenter) {
        DatabaseMethods.getFloorList(databaseFlag,idCenter);
    }

    @Override
    public void onSuccessActivityKind(ActivityKindList activityKindList) {
        SearchPresenter.view.getActivityKind(activityKindList);
    }

    @Override
    public void onSuccessStoreKind(MallKindList mallKindList) {
        SearchPresenter.view.getStoreKind(mallKindList);
    }

    @Override
    public void onSuccessShopCenterList(ShopCenterList shopCenterList) {
        SearchPresenter.view.getShopCenterList(shopCenterList);
    }

    @Override
    public void onSuccessFloorList(FloorList floorList) {
        SearchPresenter.view.getFloorList(floorList);
    }

    @Override
    public void onError(String msg) {
        SearchPresenter.view.showMessage(msg);
    }
}
