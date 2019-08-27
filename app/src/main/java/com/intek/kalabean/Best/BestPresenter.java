package com.intek.kalabean.Best;

public class BestPresenter implements BestContract.Presenter {
    private BestContract.View view;
    @Override
    public void attachView(BestContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
