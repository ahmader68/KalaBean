package com.intek.kalabean.VIP_User_Profile;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ShopCenter;
import com.intek.kalabean.Model.ShopCenterList;

import io.reactivex.disposables.CompositeDisposable;

public class VUPresenter implements VUContract.Presenter {
    private static VUContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private KalaBeanDataSource kalaBeanDataSource;
    private final int databaseFlag = 16;
    public VUPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void attachView(VUContract.View view) {
        VUPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void getShopInfo(int shopId, int usrid) {
        DatabaseMethods.getShopInfo(databaseFlag,shopId, usrid);
    }

    @Override
    public void onSuccessGetShopInfo(ShopCenterList shopCenter) {
        VUPresenter.view.getShopInfo(shopCenter);
    }


    @Override
    public void onError(String msg) {
        VUPresenter.view.showMessage(msg);
    }
}
