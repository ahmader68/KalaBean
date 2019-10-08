package com.intek.kalabean.AddProduct;

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

    private AddProductContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public AddProductPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }

    @Override
    public void attachView(AddProductContract.View view) {
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
    public void insertProduct(AddProduct product) {
        kalaBeanDataSource.insertProduct(product).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AddProduct>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(AddProduct product) {
                        int id = product.getResult();
                        if(id == -1 || id == -2){
                            switch (id){
                                case -1:
                                    view.showMesssage("لطفا تمامی فیلدهای اجباری را وارد کنید");
                                    break;
                                case -2:
                                    view.showMesssage("برنامه به درستی کار نمی کند");
                            }
                        }
                        view.showSuccess(product.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMesssage(e.toString());
                    }
                });
    }

    @Override
    public void activityKind() {
        kalaBeanDataSource.getActivityKind().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ActivityKindList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ActivityKindList activityKindList) {
                        view.getActivityKind(activityKindList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMesssage(e.toString());
                    }
                });
    }

    @Override
    public void subCatid() {
        kalaBeanDataSource.getPositions().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Positions>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Positions positions) {
                        view.getSubCatId(positions);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMesssage(e.toString());
                    }
                });
    }
}
