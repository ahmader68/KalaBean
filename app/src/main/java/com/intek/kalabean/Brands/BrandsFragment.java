package com.intek.kalabean.Brands;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerBrandsAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.BrandList;
import com.intek.kalabean.R;


public class BrandsFragment extends BaseFragment implements BrandsContract.View {

    private BrandsContract.Presenter presenter;
    private RecyclerView rv_fragmentBrands_list;
    private RecyclerBrandsAdapter recyclerBrandsAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        presenter = new BrandsPresenter(new KalaBeanRepository());
        presenter.getBrands(1319);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_brands;
    }

    @Override
    public void setupViews() {
        rv_fragmentBrands_list = rootView.findViewById(R.id.rv_fragmentBrands_list);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getBrandsList(BrandList brandLists) {
        recyclerBrandsAdapter = new RecyclerBrandsAdapter(getViewContext() , brandLists);
        rv_fragmentBrands_list.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.VERTICAL , false));
        rv_fragmentBrands_list.setAdapter(recyclerBrandsAdapter);
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
