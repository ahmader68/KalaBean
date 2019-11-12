package com.intek.kalabean.Shops;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerShopsAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopsFragment extends BaseFragment implements ShopsContract.View {

    private TextView txtTitle;
    private CircleImageView cimgShop;
    private RecyclerView rvShopList;


    private ShopsContract.Presenter presenter;
    private RecyclerShopsAdapter adapter;
    private Bundle extras;
    public int SellCenterID;
    public String image;
    public String title;
    public int ShopId;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ShopsPresenter(new KalaBeanRepository());
        extras = getArguments();
        SellCenterID = extras.getInt("SellCenterID" , 1);
        image = extras.getString("image" , "");
        title = extras.getString("title" , "");
        ShopId = extras.getInt("ShopId", 0);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_show_shop;
    }

    @Override
    public void setupViews() {

        txtTitle = rootView.findViewById(R.id.txt_fragmentShowShops_title);
        cimgShop = rootView.findViewById(R.id.img_fragmentShowShops_showShop);
        rvShopList = rootView.findViewById(R.id.rv_fragmentShowShops_shopList);
        presenter.getShops(SellCenterID,0);
        txtTitle.setText(title);
        Picasso.get().load(image).into(cimgShop);

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getShopsList(ShopsList shops) {
        adapter = new RecyclerShopsAdapter(getViewContext(),shops);
        rvShopList.setLayoutManager(new GridLayoutManager(getViewContext(),3,RecyclerView.VERTICAL,false));
        rvShopList.setAdapter(adapter);

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
