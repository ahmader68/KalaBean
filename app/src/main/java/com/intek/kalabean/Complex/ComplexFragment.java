package com.intek.kalabean.Complex;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.intek.kalabean.Adapters.RecyclerComplexAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.ComplexList;
import com.intek.kalabean.R;

public class ComplexFragment extends BaseFragment implements ComplexContract.View {
    private ComplexContract.Presenter presenter;
    private RecyclerView rvComplex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ComplexPresenter(new KalaBeanRepository());


    }

    @Override
    public int getLayout() {
        return R.layout.fragment_complex;
    }

    @Override
    public void setupViews() {
        ConstraintLayout conComplex = rootView.findViewById(R.id.con_fragmentComplex_mainLayout);
        rvComplex = rootView.findViewById(R.id.rv_fragmentComplex_list);


        presenter.getComplex(1207 , 1202);
        //complexAdapter = new RecyclerCircleImageAdapter(getViewContext(),stores);
        //rvComplex.setLayoutManager(new LinearLayoutManager(getViewContext(),RecyclerView.VERTICAL,false));
        //rvComplex.setAdapter(complexAdapter);
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

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getComplexList(ComplexList complexList) {
        RecyclerComplexAdapter complexAdapter = new RecyclerComplexAdapter(getViewContext(), complexList);
        rvComplex.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.VERTICAL ,false));
        rvComplex.setAdapter(complexAdapter);
    }
}
