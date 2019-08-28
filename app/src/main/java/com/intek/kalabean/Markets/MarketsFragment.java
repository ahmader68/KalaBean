package com.intek.kalabean.Markets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerCircleImageAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Model.Store;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;

public class MarketsFragment extends BaseFragment implements MarketsContract.View {
    private MarketsContract.Presenter presenter;
    private RecyclerCircleImageAdapter marketsAdapter;
    private List<Store> stores;
    private RecyclerView rvMarkets;
    private ConstraintLayout conFragmentMarkets;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MarketsPresenter();
        stores = new ArrayList<>();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_markets;
    }

    @Override
    public void setupViews() {
        conFragmentMarkets = rootView.findViewById(R.id.con_fragmentMarkets_mainLayout);
        conFragmentMarkets.setRotationY(180);
        rvMarkets = rootView.findViewById(R.id.rv_fragmentMarkets_list);
        marketsAdapter = new RecyclerCircleImageAdapter(getViewContext(),stores);
        rvMarkets.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
        rvMarkets.setAdapter(marketsAdapter);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}