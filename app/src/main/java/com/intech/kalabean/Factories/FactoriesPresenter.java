package com.intech.kalabean.Factories;

public class FactoriesPresenter implements FactoriesContract.Presenter {
    private FactoriesContract.View view;
    @Override
    public void attachView(FactoriesContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
