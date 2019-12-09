package com.intek.kalabean.Shops;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.FloorAdapter;
import com.intek.kalabean.Adapters.RecyclerShopsAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Brands.BrandsFragment;
import com.intek.kalabean.Chain_Store.ChainFragment;
import com.intek.kalabean.Complex.ComplexFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Markets.MarketsFragment;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopsFragment extends BaseFragment implements ShopsContract.View {


    private CircleImageView cimgShop;


    private TextView
            txtTitle,
            txt_fragmentShowShops_address,
            txt_fragmentShowShops_phone,
            txt_fragmentShowShops_web,
            txt_fragmentShowShops_vTour,
            txtPhone;
    private RecyclerView rvShopList,
            rv_fragmentShowShops_floorList;
    private ImageView img_fragmentShowShops_showShop;


    private ShopsContract.Presenter presenter;
    private RecyclerShopsAdapter adapter;
    private FloorAdapter adapterFloor;
    private Bundle extras;
    public int SellCenterID;
    public String image;
    public String title;

    public int REQUEST_CODE_CALL = 500;

    public static int flag = 0;

    public String address;


    private RadioGroup rgSwitch;
    private RadioButton rbProduct;
    private RadioButton rbShops;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ShopsPresenter(new KalaBeanRepository());
        extras = getArguments();
        SellCenterID = extras.getInt("SellCenterID", 1);
        image = extras.getString("image", "");
        title = extras.getString("title", "");
        address = extras.getString("address", "");


        presenter.floorList(SellCenterID);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_show_shop;
    }

    @Override
    public void setupViews() {

        txtTitle = rootView.findViewById(R.id.txt_fragmentShowShops_title);

        txtPhone = rootView.findViewById(R.id.txt_fragmentShowShops_phone);
        cimgShop = rootView.findViewById(R.id.img_fragmentShowShops_showShop);

        rvShopList = rootView.findViewById(R.id.rv_fragmentShowShops_shopList);
        presenter.getShops(SellCenterID, 0);
        txtTitle.setText(title);

        img_fragmentShowShops_showShop = rootView.findViewById(R.id.img_fragmentShowShops_showShop);
        txt_fragmentShowShops_address = rootView.findViewById(R.id.txt_fragmentShowShops_address);
        txt_fragmentShowShops_phone = rootView.findViewById(R.id.txt_fragmentShowShops_phone);
        txt_fragmentShowShops_web = rootView.findViewById(R.id.txt_fragmentShowShops_web);
        txt_fragmentShowShops_vTour = rootView.findViewById(R.id.txt_fragmentShowShops_vTour);

        rgSwitch = rootView.findViewById(R.id.rg_fragmentShowShop_switch);
        rbProduct = rootView.findViewById(R.id.rb_fragmentShowShop_product);
        rbShops = rootView.findViewById(R.id.rb_fragmentShowShop_shops);

        Picasso.get().load(image).into(img_fragmentShowShops_showShop);
        txt_fragmentShowShops_address.setText(address);

        rv_fragmentShowShops_floorList = rootView.findViewById(R.id.rv_fragmentShowShops_floorList);

        rbShops.setChecked(true);
        rgSwitch.setOnCheckedChangeListener((radioGroup, id) -> {
            switch (id) {
                case R.id.rb_fragmentShowShop_product:
                    Toast.makeText(getViewContext(), "به زودی" +
                            "", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb_fragmentShowShop_shops:
                    presenter.getShops(SellCenterID, -1);
                    break;
            }
        });

        txtPhone.setOnClickListener(v -> {
            String tel = txtPhone.getText().toString().trim();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + tel));
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL);
            } else {
                startActivity(intent);
            }
        });

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getShopsList(ShopsList shops) {
        adapter = new RecyclerShopsAdapter(getActivity(), shops);
        rvShopList.setLayoutManager(new GridLayoutManager(getViewContext(), 2, RecyclerView.VERTICAL, false));
        rvShopList.setAdapter(adapter);
    }

    @Override
    public void getFloorList(FloorList floorList) {

        adapterFloor = new FloorAdapter(getViewContext(), floorList, SellCenterID);
        rv_fragmentShowShops_floorList.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.HORIZONTAL, false));
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
        if (getView() == null) {
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                switch (ShopsFragment.flag) {
                    case 1:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_fragmentMain_mainLayout, new BrandsFragment()).commit();
                        break;
                    case 2:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_fragmentMain_mainLayout, new ChainFragment()).commit();
                        break;
                    case 3:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_fragmentMain_mainLayout, new ComplexFragment()).commit();
                        break;
                    case 4:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frm_fragmentMain_mainLayout, new MarketsFragment()).commit();
                        break;
                }
                return true;
            }
            return false;
        });
    }
}
