package com.intek.kalabean.Shops;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.intek.kalabean.Adapters.RecyclerShopsAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Best.BestFragment;
import com.intek.kalabean.Chain_Store.ChainFragment;
import com.intek.kalabean.Complex.ComplexFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Markets.MarketsFragment;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

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

        Picasso.get().load(image).into(img_fragmentShops_Market);
        txt_fragmentShops_title.setText(title);

        presenter.getShops(-1, -1);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getShopsList(ShopsList shops) {
        adapter = new RecyclerShopsAdapter(getViewContext(), shops);
        rv_fragmentShops_list.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
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
