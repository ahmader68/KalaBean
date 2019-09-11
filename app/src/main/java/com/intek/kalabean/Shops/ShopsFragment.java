package com.intek.kalabean.Shops;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerShopsAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;

import java.util.List;

public class ShopsFragment extends BaseFragment implements ShopsContract.View {

    private RecyclerView rv_fragmentShops_list;
    private ConstraintLayout conFragmentShops;
    private ShopsContract.Presenter presenter;
    private Bundle extras;
    public int SellCenterID;
    private RecyclerShopsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ShopsPresenter(new KalaBeanRepository());
        extras = getArguments();
        SellCenterID = extras.getInt("SellCenterID" , 1);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_shops;
    }

    @Override
    public void setupViews() {
        conFragmentShops = rootView.findViewById(R.id.con_fragmentShops_mainLayout);
        conFragmentShops.setRotationY(180);
        rv_fragmentShops_list = rootView.findViewById(R.id.rv_fragmentShops_list);

        presenter.getShops(SellCenterID , -1);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getShopsList(ShopsList shops) {
        adapter = new RecyclerShopsAdapter(getViewContext() , shops);
        rv_fragmentShops_list.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.VERTICAL , false));
        rv_fragmentShops_list.setAdapter(adapter);
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
}
