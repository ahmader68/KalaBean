package com.intek.kalabean.Complex;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerCircleImageAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Model.Store;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;

public class ComplexFragment extends BaseFragment implements ComplexContract.View {
    private ComplexContract.Presenter presenter;
    private RecyclerView rvComplex;
    private RecyclerCircleImageAdapter complexAdapter;
    private List<Store> stores;
    private ConstraintLayout conComplex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stores = new ArrayList<>();
        presenter = new ComplexPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_complex;
    }

    @Override
    public void setupViews() {
        conComplex = rootView.findViewById(R.id.con_fragmentComplex_mainLayout);
        rvComplex = rootView.findViewById(R.id.rv_fragmentComplex_list);
        conComplex.setRotationY(180);
        //complexAdapter = new RecyclerCircleImageAdapter(getViewContext(),stores);
        rvComplex.setLayoutManager(new LinearLayoutManager(getViewContext(),RecyclerView.VERTICAL,false));
        rvComplex.setAdapter(complexAdapter);
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
