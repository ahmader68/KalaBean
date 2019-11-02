package com.intek.kalabean.Request_Product;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ActivityKindList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RequestPresenter implements RequestContract.Presenter {

    private RequestContract.View view;
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RequestPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;

    }
    @Override
    public void attachView(RequestContract.View view) {
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
                        view.getActivityKindRequest(activityKindList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
