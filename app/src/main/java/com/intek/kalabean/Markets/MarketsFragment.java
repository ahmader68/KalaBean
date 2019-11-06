package com.intek.kalabean.Markets;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerCircleImageAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.R;

import java.util.ArrayList;
import java.util.List;

public class MarketsFragment extends BaseFragment implements MarketsContract.View {
    private MarketsContract.Presenter presenter;
    private RecyclerCircleImageAdapter marketsAdapter;
    private List<StoreList.Store> stores;
    private RecyclerView rvMarkets;
    private ConstraintLayout conFragmentMarkets;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MarketsPresenter(new KalaBeanRepository());
        stores = new ArrayList<>();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_markets;
    }

    @Override
    public void setupViews() {
        conFragmentMarkets = rootView.findViewById(R.id.con_fragmentMarkets_mainLayout);

        rvMarkets = rootView.findViewById(R.id.rv_fragmentMarkets_list);

        presenter.getMarkets(1206 , 1202);
    }

    @Override
    public Context getViewContext() {
        return getActivity();
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
    public void getMarketList(StoreList stores) {
        marketsAdapter = new RecyclerCircleImageAdapter(getViewContext(),stores);
        rvMarkets.setLayoutManager(new LinearLayoutManager(getViewContext(),RecyclerView.VERTICAL,false));
        rvMarkets.setAdapter(marketsAdapter);
    }
}
