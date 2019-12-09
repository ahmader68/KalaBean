package com.intek.kalabean.Chain_Store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerChainStoreAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.ChainStoreList;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsFragment;

import java.util.Objects;

import jrizani.jrspinner.JRSpinner;


public class ChainFragment extends BaseFragment implements ChainContract.View {
    private RecyclerView rvChainStore;
    private ChainContract.Presenter presenter;

    private JRSpinner spinner;
    private String[] spinnerItems;

    private int cityId;

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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        ConstraintLayout conChainStore = rootView.findViewById(R.id.con_fragmentChainStore_mainLayout);

        cityId = sharedPreferences.getInt("cityId",0);
        if (cityId == 1204 || cityId == 1205 || cityId == 1248) {
            Toast.makeText(getViewContext(), "مجاز به انتخاب این شهر نمی باشید", Toast.LENGTH_SHORT).show();
        } else if (cityId == 0) {
            cityId = 1202;
        } else {
            presenter.getChainStore(1208 , cityId);
        }

        rvChainStore = rootView.findViewById(R.id.rv_fragmentChainStore_list);


        spinner = rootView.findViewById(R.id.sp_fragmentChainStore_chainStore);
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
    public void getChainStoreList(final ChainStoreList chainStoreList) {
        if(chainStoreList.getStoreList() == null){
            showMessage("هیچ فروشگاهی یافت نشد");
        }else {
            RecyclerChainStoreAdapter chainStoreAdapter = new RecyclerChainStoreAdapter(getViewContext(), chainStoreList);
            rvChainStore.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
            rvChainStore.setAdapter(chainStoreAdapter);

            spinnerItems = new String[chainStoreList.getStoreList().size()];
            for (int i = 0; i < chainStoreList.getStoreList().size(); i++) {
                spinnerItems[i] = chainStoreList.getStoreList().get(i).getTitleFA();
            }
            spinner.setItems(spinnerItems); //this is important, you must set it to set the item list
            spinner.setTitle("انتخاب"); //change title of spinner-dialog programmatically
            spinner.setExpandTint(R.color.colorAccent); //change expand icon tint programmatically

            spinner.setMultiple(false);
            //set it if you want the callback
            spinner.setOnItemClickListener(position -> {
                //do what you want to the selected position
                ChainStoreList.ChainStore store = chainStoreList.getStoreList().get(position);
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
            });
        }
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
