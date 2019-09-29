package com.intech.kalabean.Handmade;

public class HandmadePresenter implements HandmadeContract.Presenter {
    private HandmadeContract.View view;
    @Override
    public void attachView(HandmadeContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
