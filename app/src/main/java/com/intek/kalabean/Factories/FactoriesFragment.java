package com.intek.kalabean.Factories;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerCircleImageAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Classes.Alert_Dialog;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;

public class FactoriesFragment extends BaseFragment implements FactoriesContract.View {
    private FactoriesContract.Presenter presenter;
    private RecyclerCircleImageAdapter factoriesAdapter;
    private RecyclerView rvFactories;
    private List<StoreList.Store> stores;
    private ConstraintLayout conFactories;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FactoriesPresenter();
        stores = new ArrayList<>();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_factories;
    }

    @Override
    public void setupViews() {
        conFactories = rootView.findViewById(R.id.con_fragmentFactories_mainLayout);
        rvFactories = rootView.findViewById(R.id.rv_fragmentFactories_list);
        conFactories.setRotationY(180);
        //factoriesAdapter = new RecyclerCircleImageAdapter(getViewContext(),stores);
        rvFactories.setLayoutManager(new LinearLayoutManager(getViewContext(),RecyclerView.VERTICAL,false));
        rvFactories.setAdapter(factoriesAdapter);
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
    public void onResume() {
        super.onResume();
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout,new MainFragment()).commit();
                return true;
            }
            return false;
        });
    }
}
