package com.intek.kalabean.Classes;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Definition_Store.DefinitionContract;
import com.intek.kalabean.Definition_Store.DefinitionPresenter;
import com.intek.kalabean.Model.ActivityKindList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DatabaseMethods {
    public static KalaBeanDataSource kalaBeanDataSource;
    private static CompositeDisposable compositeDisposable = new CompositeDisposable();
    public String message = null;
    private static DefinitionContract.Presenter presenter = new DefinitionPresenter(new KalaBeanRepository());

    public static int flag = 0;


    public static void getActivityKind(int id) {
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getActivityKind().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ActivityKindList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onSuccess(ActivityKindList activityKindList) {
                        //activityKindListResult = activityKindList;
                        switch (flag) {
                            case 1:
                                presenter.onSuccess(activityKindList);
                                break;
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag) {
                            case 1:
                                presenter.onError(e.toString());
                                break;
                        }

                    }
                });
    }
}
