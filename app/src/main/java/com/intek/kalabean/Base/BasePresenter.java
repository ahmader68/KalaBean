package com.intek.kalabean.Base;



public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
