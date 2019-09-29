package com.intech.kalabean.Shops;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.intech.kalabean.Adapters.RecyclerShopsAdapter;
import com.intech.kalabean.Base.BaseFragment;
import com.intech.kalabean.Data.KalaBeanRepository;
import com.intech.kalabean.Main_Page.MainFragment;
import com.intech.kalabean.Model.ShopsList;
import com.intech.kalabean.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopsFragment extends BaseFragment implements ShopsContract.View {

    private RecyclerView rv_fragmentShops_list;
    private ConstraintLayout conFragmentShops;
    private ShopsContract.Presenter presenter;
    private Bundle extras;
    public int SellCenterID;
    public String image;
    public String title;
    private RecyclerShopsAdapter adapter;
    private CircleImageView img_fragmentShops_Market;
    private TextView txt_fragmentShops_title;
    private TextView txt_fragmentShops_Null;
    private int sellCenterCatId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ShopsPresenter(new KalaBeanRepository());
        extras = getArguments();
        SellCenterID = extras.getInt("SellCenterID", 1);
        image = extras.getString("image", "");
        title = extras.getString("title", "");
        sellCenterCatId = extras.getInt("flag", 0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_shops;
    }

    @Override
    public void setupViews() {
        rv_fragmentShops_list = rootView.findViewById(R.id.rv_fragmentShops_list);
        img_fragmentShops_Market = rootView.findViewById(R.id.img_fragmentShops_Market);
        txt_fragmentShops_title = rootView.findViewById(R.id.txt_fragmentShops_title);
        txt_fragmentShops_Null = rootView.findViewById(R.id.txt_fragmentShops_Null);

        Picasso.get().load(image).into(img_fragmentShops_Market);
        txt_fragmentShops_title.setText(title);

        presenter.getShops(SellCenterID, -1);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getShopsList(ShopsList shops) {
        if (shops.getItems() == null){
            txt_fragmentShops_Null.setVisibility(View.VISIBLE);
        } else {
            adapter = new RecyclerShopsAdapter(getViewContext(), shops);
            rv_fragmentShops_list.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
            rv_fragmentShops_list.setAdapter(adapter);
        }
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
    public void onResume() {
        super.onResume();
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_MainActivity_mainLayout,new MainFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }
}
