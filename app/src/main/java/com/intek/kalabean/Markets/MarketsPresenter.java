package com.intek.kalabean.Markets;

public class MarketsPresenter implements MarketsContract.Presenter {
    private MarketsContract.View view;
    @Override
    public void attachView(MarketsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
