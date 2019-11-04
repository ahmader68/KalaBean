package com.intek.kalabean.AddProduct;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.AddProduct;
import com.intek.kalabean.Model.Positions;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddProductPresenter implements AddProductContract.Presenter {

    private static AddProductContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final int databaseFlag = 3;

    public AddProductPresenter(KalaBeanDataSource kalaBeanDataSource) {
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(AddProductContract.View view) {
        AddProductPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if (compositeDisposable != null && compositeDisposable.size() > 0) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void insertProduct(AddProduct product) {
        DatabaseMethods.insertProduct(databaseFlag,product);
    }

    @Override
    public void activityKind() {
        DatabaseMethods.getActivityKind(databaseFlag);
    }

    @Override
    public void subCatid() {
        DatabaseMethods.subCatId(databaseFlag);
    }

    @Override
    public void onSuccessActivityKind(ActivityKindList activityKindList) {
        AddProductPresenter.view.getActivityKind(activityKindList);
    }

    @Override
    public void onSuccessInsertProduct(AddProduct product) {
        int id = product.getResult();
        if (id < 0) {
            switch (id) {
                case -1:
                    AddProductPresenter.view.showMesssage("لطفا تمامی فیلدهای اجباری را وارد کنید");
                    break;
                case -2:
                    AddProductPresenter.view.showMesssage("برنامه به درستی کار نمی کند");
            }
        }
        AddProductPresenter.view.showSuccess(product.getResult());
    }

    @Override
    public void onSuccessSubCatId(Positions positions) {
        AddProductPresenter.view.getSubCatId(positions);
    }

    @Override
    public void onError(String message) {
        AddProductPresenter.view.showMesssage(message);
    }
}
