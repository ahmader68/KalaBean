package com.intek.kalabean.Definition_Store;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.ShopCenterList;
import com.intek.kalabean.Model.StoreDif;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DefinitionPresenter implements DefinitionContract.Presenter {
    private KalaBeanDataSource kalaBeanDataSource;
    private static DefinitionContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<MallKindList.MallKind> kinds;
    private final int databseFlag = 1;

    private ActivityKindList activityKindList;
    public DefinitionPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
        DatabaseMethods.kalaBeanDataSource = kalaBeanDataSource;
        activityKindList = new ActivityKindList();
    }


    @Override
    public void attachView(DefinitionContract.View view) {
        DefinitionPresenter.view = view;
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
        DatabaseMethods.getStoreKind(databseFlag);
    }

    @Override
    public void activityKind() {
         DatabaseMethods.getActivityKind(databseFlag);

    }


    @Override
    public void ShopCenterList(int idCenterCat) {
        DatabaseMethods.getShopCenterList(databseFlag,idCenterCat);
    }

    @Override
    public void floorList(int idCenter) {
        DatabaseMethods.getFloorList(databseFlag,idCenter);
    }

    @Override
    public void storeDefinition(StoreDif storeDif) {
        DatabaseMethods.storeDefinition(databseFlag,storeDif);
    }

    @Override
    public void onSuccess(ActivityKindList activityKindList) {

        DefinitionPresenter.view.getActivityKind(activityKindList);
    }

    @Override
    public void onSuccessStoreKind(MallKindList mallKindList) {
        DefinitionPresenter.view.getStoreKind(mallKindList);
    }

    @Override
    public void onSuccessShopCenterList(ShopCenterList shopCenterList) {
        DefinitionPresenter.view.getShopCenterList(shopCenterList);
    }

    @Override
    public void onSuccessFloorList(FloorList floorList) {
        DefinitionPresenter.view.getFloorList(floorList);
    }

    @Override
    public void onSuccessStoreDefinition(StoreDif storeDif) {
        if(storeDif.getResult() <= -1 && storeDif.getResult() >= -2){
            switch (storeDif.getResult()){
                case -1:
                    view.showMessage("لطفا همه گزینه های الزامی را وارد کنید");
                    break;
                case -2:
                    view.showMessage("عملیات ثبت با خطا روبرو شد، لطفا مجدد تلاش کنید");
            }
        }else {
            DefinitionPresenter.view.getStoreId(storeDif);
        }

    }

    @Override
    public void onError(String message) {
        DefinitionPresenter.view.showMessage(message);
    }
}
