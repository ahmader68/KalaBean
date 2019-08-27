package com.intek.kalabean.Chain_Store;

public class ChainPresenter implements ChainContract.Presenter {
    private ChainContract.View view;
    @Override
    public void attachView(ChainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
