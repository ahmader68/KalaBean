package com.intek.kalabean.Best;

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

public class BestFragment extends BaseFragment implements BestContract.View {
    private BestContract.Presenter presenter;
    private List<Store> stores;
    private RecyclerCircleImageAdapter bestAdapter;
    private RecyclerView rvBest;
    private ConstraintLayout conBest;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BestPresenter();
        stores = new ArrayList<>();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_best;
    }

    @Override
    public void setupViews() {
        conBest = rootView.findViewById(R.id.con_fragmentBest_mainLayout);
        rvBest = rootView.findViewById(R.id.rv_fragmentBest_list);
        conBest.setRotationY(180);
        bestAdapter = new RecyclerCircleImageAdapter(getViewContext(),stores);
        rvBest.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
        rvBest.setAdapter(bestAdapter);
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
