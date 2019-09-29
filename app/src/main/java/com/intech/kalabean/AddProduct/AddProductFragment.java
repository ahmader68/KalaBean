package com.intech.kalabean.AddProduct;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.intech.kalabean.Base.BaseFragment;
import com.intech.kalabean.Data.KalaBeanRepository;

public class AddProductFragment extends BaseFragment implements AddProductContract.View {

    private AddProductContract.Presenter presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new AddProductPresenter(new KalaBeanRepository());
    }

    @Override
    public void showMesssage(String msg) {

    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void setupViews() {

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
