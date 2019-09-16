package com.intek.kalabean.Best;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerCircleImageAdapter;
import com.intek.kalabean.Base.BaseFragment;

import com.intek.kalabean.R;



public class BestFragment extends BaseFragment implements BestContract.View {
    private BestContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BestPresenter();

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_best;
    }

    @Override
    public void setupViews() {
        ConstraintLayout conBest = rootView.findViewById(R.id.con_fragmentBest_mainLayout);
        RecyclerView rvBest = rootView.findViewById(R.id.rv_fragmentBest_list);
        conBest.setRotationY(180);
        //bestAdapter = new RecyclerCircleImageAdapter(getViewContext(),stores);
        rvBest.setLayoutManager(new LinearLayoutManager(getViewContext(),RecyclerView.VERTICAL,false));
        //rvBest.setAdapter(bestAdapter);
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
