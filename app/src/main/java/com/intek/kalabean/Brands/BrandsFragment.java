package com.intek.kalabean.Brands;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerBrandsAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.BrandList;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsFragment;

import java.util.Objects;

import jrizani.jrspinner.JRSpinner;


public class BrandsFragment extends BaseFragment implements BrandsContract.View {

    private BrandsContract.Presenter presenter;
    private RecyclerView rv_fragmentBrands_list;
    private RecyclerBrandsAdapter recyclerBrandsAdapter;

    private JRSpinner spinner;
    private String[] spinnerItems;


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
        spinner = rootView.findViewById(R.id.sp_fragmentBrands_brands);

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getBrandsList(final BrandList brandLists) {
        recyclerBrandsAdapter = new RecyclerBrandsAdapter(getViewContext(), brandLists);
        rv_fragmentBrands_list.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rv_fragmentBrands_list.setAdapter(recyclerBrandsAdapter);

        spinnerItems = new String[brandLists.getStoreList().size()];
        for (int i = 0; i < brandLists.getStoreList().size(); i++) {
            spinnerItems[i] = brandLists.getStoreList().get(i).getTitleFA();
        }
        spinner.setItems(spinnerItems); //this is important, you must set it to set the item list
        spinner.setTitle("انتخاب"); //change title of spinner-dialog programmatically
        spinner.setExpandTint(R.color.colorAccent); //change expand icon tint programmatically

        spinner.setMultiple(false);
        spinner.setOnItemClickListener(new JRSpinner.OnItemClickListener() { //set it if you want the callback
            @Override
            public void onItemClick(int position) {
                //do what you want to the selected position
                BrandList.Brands store = brandLists.getStoreList().get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("SellCenterID", store.getSellCenterID());
                String image = store.getImage();
                String url[] = image.split("'");
                bundle.putString("image", url[0]);
                bundle.putString("title", store.getTitleFA());
                bundle.putInt("flag", store.getSellCenterCatID());
                FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ShopsFragment shopsFragment = new ShopsFragment();
                shopsFragment.setArguments(bundle);
                transaction.replace(R.id.frm_fragmentMain_mainLayout, shopsFragment);
                transaction.commit();
                Toast.makeText(getViewContext(), "ok", Toast.LENGTH_SHORT).show();
            }
        });
    }

        @Override
        public Context getViewContext () {
            return getContext();
        }

        @Override
        public void onStart () {
            super.onStart();
            presenter.attachView(this);
        }

        @Override
        public void onStop () {
            super.onStop();
            presenter.detachView();
        }
    }
