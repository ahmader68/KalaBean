package com.intek.kalabean.Base;

import android.view.View;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
