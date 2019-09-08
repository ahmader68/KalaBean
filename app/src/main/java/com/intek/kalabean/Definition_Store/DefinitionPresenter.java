package com.intek.kalabean.Definition_Store;

import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.MallKind;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DefinitionPresenter implements DefinitionContract.Presenter {
    private KalaBeanDataSource kalaBeanDataSource;
    private DefinitionContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<MallKind> kinds;
    public DefinitionPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }


    @Override
    public void attachView(DefinitionContract.View view) {
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
    public void storeKind() {
        kalaBeanDataSource.getStoreKind().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MallKind>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(MallKind mallKind) {
                        view.getStoreKind(mallKind);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }

    @Override
    public void activityKind() {
        kalaBeanDataSource.getActivityKind().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ActivityKind>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<ActivityKind> activityKinds) {
                        view.getActivityKind(activityKinds);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.toString());
                    }
                });
    }
}
