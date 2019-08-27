package com.intek.kalabean.Chain_Store;

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

public class ChainFragment extends BaseFragment implements ChainContract.View {
    private RecyclerView rvChainStore;
    private RecyclerCircleImageAdapter chainStoreAdapter;
    private List<Store> stores;
    private ChainContract.Presenter presenter;
    private ConstraintLayout conChainStore;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChainPresenter();
        stores = new ArrayList<>();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_chain_store;
    }

    @Override
    public void setupViews() {
        conChainStore = rootView.findViewById(R.id.con_fragmentChainStore_mainLayout);
        conChainStore.setRotationY(180);
        rvChainStore = rootView.findViewById(R.id.rv_fragmentChainStore_list);
        chainStoreAdapter = new RecyclerCircleImageAdapter(getViewContext(),stores);
        rvChainStore.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
        rvChainStore.setAdapter(chainStoreAdapter);
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
