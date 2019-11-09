package com.intek.kalabean.Category;

import android.content.Context;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.R;

public class CatFragment extends BaseFragment implements CatContract.View {
    @Override
    public int getLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void setupViews() {

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
