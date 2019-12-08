package com.intek.kalabean.ShowShop;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransitionImpl;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.intek.kalabean.Adapters.RecyclerProductAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Fragment.ShowWebFragment;
import com.intek.kalabean.Login_With_User_Pass.LoginWithUserPassFragment;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowShopFragment extends BaseFragment implements ShowShopContract.View {

    private ShowShopContract.Presenter presenter;

    private ImageView
            imgHambur,
            imgShare,
            imgShop,
            imgHeart,
            imgMap,
            imgInstagram,
            imgTelegram,
            imgEmail,
            imgWeb,
            img_fragmentShowShops_leftArrow,
            img_fragmentShowShops_rightArrow;

    private Button
            btnInfo,
            btnContact;

    private TextView
            txtAddress,
            txtCountView,
            txtWorkHour,
            txtNull;

    private RecyclerView rvProductList;

    private Dialog dialog;

    private Bundle extras;
    public int SellCenterID;
    public String image;
    public String title;
    private String address,tel,web;
    public int ShopId;
    private int visitCount;
    private RecyclerProductAdapter adapter;
    private boolean heartCheck = false;
    private final int REQUEST_CALL_PHONE = 210;
    private int userId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getViewContext());
        userId = sharedPreferences.getInt("userid",0);
        presenter = new ShowShopPresenter(new KalaBeanRepository());
        extras = getArguments();
        SellCenterID = extras.getInt("SellCenterID" , 1);
        image = extras.getString("image" , "");
        title = extras.getString("title" , "");
        ShopId = extras.getInt("ShopId", 0);
        address = extras.getString("address","");
        tel = extras.getString("tel","");
        web = extras.getString("web","");
        visitCount = extras.getInt("visitCount",0);


    }

    @Override
    public int getLayout() {
        return R.layout.fragment_shops;
    }

    @Override
    public void setupViews() {

        imgShop = rootView.findViewById(R.id.img_fragmentShop_shop);
        imgHeart = rootView.findViewById(R.id.img_fragmentShop_heart);
        imgInstagram = rootView.findViewById(R.id.img_fragmentShop_instagram);
        imgTelegram = rootView.findViewById(R.id.img_fragmentShop_telegram);
        imgEmail = rootView.findViewById(R.id.img_fragmentShop_email);
        imgWeb = rootView.findViewById(R.id.img_fragmentShop_domain);
        imgShare = rootView.findViewById(R.id.img_fragmentShop_share);
        imgHambur = rootView.findViewById(R.id.img_fragmentShop_hambur);
        imgMap = rootView.findViewById(R.id.img_fragmentShop_map);

        btnInfo = rootView.findViewById(R.id.btn_fragmentShop_info);
        btnContact = rootView.findViewById(R.id.btn_fragmentShop_contact);
        txtNull = rootView.findViewById(R.id.txt_fragmentShop_null);

        rvProductList = rootView.findViewById(R.id.rv_fragmentShop_list);

        String shopInfo = "اطلاعات فروشگاه " + title;
        btnInfo.setText(shopInfo);
        Picasso.get().load(image).into(imgShop);

        presenter.getProduct(ShopId);



        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(heartCheck){
                    heartCheck = false;
                    imgHeart.setImageResource(R.drawable.empty_heart);
                }else{
                    heartCheck = true;
                    imgHeart.setImageResource(R.drawable.full_heart);
                }
            }
        });

        imgHambur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getViewContext(),imgHambur);
                popupMenu.getMenuInflater().inflate(R.menu.shop_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        switch (id){
                            case R.id.menu_store_catProduct:
                                Toast.makeText(getViewContext(), "1", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_store_description:
                                Toast.makeText(getViewContext(), "2", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_store_branchs:
                                Toast.makeText(getViewContext(), "3", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        imgTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId > 0) {
                    String idPack = "@hooman_hooshyar";
                    String[] seperated = idPack.split("@");
                    String id = seperated[1];

                    Intent telegram = new Intent(Intent.ACTION_VIEW);
                    telegram.setData(Uri.parse("http://telegram.me/" + id));
                    startActivity(telegram);
                }else{
                    firstLogin();
                }
            }
        });

        imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId > 0) {
                    Intent instagram = new Intent(Intent.ACTION_VIEW);
                    instagram.setData(Uri.parse("https://www.instagram.com/hooman_hooshiar"));
                    startActivity(instagram);
                }else{
                    firstLogin();
                }
            }
        });

        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userId > 0) {
                    if (web.isEmpty()) {
                        showMessage(getResources().getString(R.string.toast_this_store_has_no_website));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("web", web);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        ShowWebFragment webFragment = new ShowWebFragment();
                        webFragment.setArguments(bundle);
                        transaction.replace(R.id.frm_MainActivity_mainLayout, webFragment);
                        transaction.commit();
                    }
                }else{
                    firstLogin();
                }
            }
        });

        imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userId > 0) {
                    if (tel.isEmpty()) {
                        showMessage("هیچ شماره ای برای این فروشگاه ثبت نشده است");
                    } else {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + tel));
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                        } else {
                            startActivity(intent);
                        }
                    }
                }else{
                    firstLogin();
                }
            }
        });

        dialog = new Dialog(getViewContext());
        dialog.setContentView(R.layout.dialog_more_info_shop);
        dialog.setCanceledOnTouchOutside(true);

        txtAddress = dialog.findViewById(R.id.txt_fragmentShop_address);
        txtCountView = dialog.findViewById(R.id.txt_fragmentShop_countView);
        txtAddress.setText(address);
        txtCountView.setText(String.valueOf(visitCount));

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userId > 0) {
                    dialog.show();
                }else{
                    firstLogin();
                }
            }
        });
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

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getProductList(ProductList productLists) {
        if (productLists.getItems() == null){
            txtNull.setVisibility(View.VISIBLE);
            rvProductList.setVisibility(View.GONE);
        } else {
            txtNull.setVisibility(View.GONE);
            rvProductList.setVisibility(View.VISIBLE);
            adapter = new RecyclerProductAdapter(getViewContext(), productLists);
            rvProductList.setLayoutManager(new GridLayoutManager(getViewContext() , 3 , RecyclerView.VERTICAL , false));
            rvProductList.setAdapter(adapter);
        }
    }


    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    private void firstLogin(){
        showMessage("ابتدا باید وارد برنامه شوید");
        Fragment fragment = new LoginWithUserPassFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_fragmentMain_mainLayout,fragment);
        transaction.commit();
    }
}
