package com.intek.kalabean.Classes;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ActivityKindList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public  class DatabaseMethods {
    private KalaBeanDataSource kalaBeanDataSource;
    private CompositeDisposable compositeDisposable;
    private ActivityKindList activityKindListResult;
    public String message = null;


    public  DatabaseMethods(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
        compositeDisposable = new CompositeDisposable();
        activityKindListResult = new ActivityKindList();
    }

    public  ActivityKindList getActivityKind(){
        kalaBeanDataSource.getActivityKind().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ActivityKindList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ActivityKindList activityKindList) {
                        activityKindListResult = activityKindList;
                    }

                    @Override
                    public void onError(Throwable e) {
                        message = e.toString();
                    }
                });

        if(message == null && activityKindListResult.getItems().size() > 0){
            return activityKindListResult;
        }else{
            return null;
        }
    }
}
