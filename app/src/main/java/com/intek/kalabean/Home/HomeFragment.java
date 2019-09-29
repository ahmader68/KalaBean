package com.intek.kalabean.Home;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.R;

public class HomeFragment extends BaseFragment implements HomeContract.View  {

    ConstraintLayout con_fragmentHome_markets;
    ConstraintLayout con_fragmentHome_kalaBeanLook;
    ConstraintLayout con_fragmentHome_malls;
    ConstraintLayout con_fragmentHome_search;
    ConstraintLayout con_fragmentHome_chainStore;
    ConstraintLayout con_fragmentHome_gallery;
    ConstraintLayout con_fragmentHome_brands;

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setupViews() {
        con_fragmentHome_markets = rootView.findViewById(R.id.con_fragmentHome_markets);
        con_fragmentHome_kalaBeanLook = rootView.findViewById(R.id.con_fragmentHome_kalaBeanLook);
        con_fragmentHome_malls = rootView.findViewById(R.id.con_fragmentHome_malls);
        con_fragmentHome_search = rootView.findViewById(R.id.con_fragmentHome_search);
        con_fragmentHome_chainStore = rootView.findViewById(R.id.con_fragmentHome_chainStore);
        con_fragmentHome_gallery = rootView.findViewById(R.id.con_fragmentHome_gallery);
        con_fragmentHome_brands = rootView.findViewById(R.id.con_fragmentHome_brands);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
