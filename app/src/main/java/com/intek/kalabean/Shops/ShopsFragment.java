package com.intek.kalabean.Shops;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.FloorAdapter;
import com.intek.kalabean.Adapters.RecyclerShopsAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopsFragment extends BaseFragment implements ShopsContract.View {

    private TextView txtTitle;
    private RecyclerView rvShopList ,
            rv_fragmentShowShops_floorList;
    private ImageView img_fragmentShowShops_leftArrow ,
            img_fragmentShowShops_rightArrow;


    private ShopsContract.Presenter presenter;
    private RecyclerShopsAdapter adapter;
    private FloorAdapter adapterFloor;
    private Bundle extras;
    public int SellCenterID;
    public String image;
    public String title;
    public int ShopId;
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ShopsPresenter(new KalaBeanRepository());
        extras = getArguments();
        SellCenterID = extras.getInt("SellCenterID" , 1);
        image = extras.getString("image" , "");
        title = extras.getString("title" , "");
        ShopId = extras.getInt("ShopId", 0);

        presenter.floorList(SellCenterID);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_show_shop;
    }

    @Override
    public void setupViews() {

        txtTitle = rootView.findViewById(R.id.txt_fragmentShowShops_title);
        rvShopList = rootView.findViewById(R.id.rv_fragmentShowShops_shopList);
        presenter.getShops(SellCenterID,0);
        txtTitle.setText(title);

        rv_fragmentShowShops_floorList = rootView.findViewById(R.id.rv_fragmentShowShops_floorList);

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getShopsList(ShopsList shops) {
        adapter = new RecyclerShopsAdapter(getViewContext(),shops);
        rvShopList.setLayoutManager(new GridLayoutManager(getViewContext(),2,RecyclerView.VERTICAL,false));
        rvShopList.setAdapter(adapter);
    }

    @Override
    public void getFloorList(FloorList floorList) {

        adapterFloor = new FloorAdapter(getViewContext() , floorList , SellCenterID);
        rv_fragmentShowShops_floorList.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.HORIZONTAL , false));
        rv_fragmentShowShops_floorList.setAdapter(adapterFloor);
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
