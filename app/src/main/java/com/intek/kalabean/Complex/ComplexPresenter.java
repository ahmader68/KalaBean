package com.intek.kalabean.Complex;

public class ComplexPresenter implements ComplexContract.Presenter {
    private ComplexContract.View view;
    @Override
    public void attachView(ComplexContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
