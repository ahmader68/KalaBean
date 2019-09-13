package com.intek.kalabean.Chain_Store;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerChainStoreAdapter;
import com.intek.kalabean.Adapters.RecyclerCircleImageAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.ChainStoreList;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;

public class ChainFragment extends BaseFragment implements ChainContract.View {
    private RecyclerView rvChainStore;
    private RecyclerChainStoreAdapter chainStoreAdapter;
    private List<StoreList.Store> stores;
    private ChainContract.Presenter presenter;
    private ConstraintLayout conChainStore;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChainPresenter(new KalaBeanRepository());
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
        presenter.getChainStore(1208 , 1201);
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
        chainStoreAdapter = new RecyclerChainStoreAdapter(getViewContext(),chainStoreList);
        rvChainStore.setLayoutManager(new LinearLayoutManager(getViewContext(),RecyclerView.VERTICAL,false));
        rvChainStore.setAdapter(chainStoreAdapter);
    }
}
