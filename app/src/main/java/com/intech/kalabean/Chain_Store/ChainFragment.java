package com.intech.kalabean.Chain_Store;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intech.kalabean.Adapters.RecyclerChainStoreAdapter;
import com.intech.kalabean.Base.BaseFragment;
import com.intech.kalabean.Data.KalaBeanRepository;
import com.intech.kalabean.Model.ChainStoreList;
import com.intech.kalabean.R;


public class ChainFragment extends BaseFragment implements ChainContract.View {
    private RecyclerView rvChainStore;
    private ChainContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChainPresenter(new KalaBeanRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_chain_store;
    }

    @Override
    public void setupViews() {
        ConstraintLayout conChainStore = rootView.findViewById(R.id.con_fragmentChainStore_mainLayout);
        conChainStore.setRotationY(180);
        rvChainStore = rootView.findViewById(R.id.rv_fragmentChainStore_list);
        presenter.getChainStore(1208 , 1202);
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
    public void getChainStoreList(ChainStoreList chainStoreList) {
        RecyclerChainStoreAdapter chainStoreAdapter = new RecyclerChainStoreAdapter(getViewContext(), chainStoreList);
        rvChainStore.setLayoutManager(new LinearLayoutManager(getViewContext(),RecyclerView.VERTICAL,false));
        rvChainStore.setAdapter(chainStoreAdapter);
    }
}
