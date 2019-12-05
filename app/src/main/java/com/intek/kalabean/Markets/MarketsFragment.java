package com.intek.kalabean.Markets;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerCircleImageAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import jrizani.jrspinner.JRSpinner;

public class MarketsFragment extends BaseFragment implements MarketsContract.View {
    private MarketsContract.Presenter presenter;
    private RecyclerCircleImageAdapter marketsAdapter;
    private RecyclerView rvMarkets;
    private ConstraintLayout conFragmentMarkets;
    private JRSpinner spinner;
    private String[] spinnerItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MarketsPresenter(new KalaBeanRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_markets;
    }

    @Override
    public void setupViews() {
        conFragmentMarkets = rootView.findViewById(R.id.con_fragmentMarkets_mainLayout);

        rvMarkets = rootView.findViewById(R.id.rv_fragmentMarkets_list);

        presenter.getMarkets(1206, 1202);

        spinner = rootView.findViewById(R.id.sp_fragmentMarkets_markets);
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
    public void getMarketList(final StoreList stores) {
        marketsAdapter = new RecyclerCircleImageAdapter(getViewContext(), stores);
        rvMarkets.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rvMarkets.setAdapter(marketsAdapter);

        spinnerItems = new String[stores.getStoreList().size()];
        for (int i = 0; i < stores.getStoreList().size(); i++) {
            spinnerItems[i] = stores.getStoreList().get(i).getTitleFA();
        }
        spinner.setItems(spinnerItems); //this is important, you must set it to set the item list
        spinner.setTitle("انتخاب"); //change title of spinner-dialog programmatically
        spinner.setExpandTint(R.color.colorAccent); //change expand icon tint programmatically

        spinner.setMultiple(false);
        spinner.setOnItemClickListener(new JRSpinner.OnItemClickListener() { //set it if you want the callback
            @Override
            public void onItemClick(int position) {
                //do what you want to the selected position
                StoreList.Store store = stores.getStoreList().get(position);
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


//        spinner.setOnSelectMultipleListener(new JRSpinner.OnSelectMultipleListener() {
//            @Override
//            public void onMultipleSelected(List<Integer> selectedPosition) {
//                //do what you want to selected position list
//            }
//        }); //use this listener instead if you use multiple
    }
}
