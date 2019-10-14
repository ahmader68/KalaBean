package com.intek.kalabean.Shops;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.intek.kalabean.Adapters.RecyclerShopsAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Main_Page.MainFragment;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopsFragment extends BaseFragment implements ShopsContract.View {

    private ShopsContract.Presenter presenter;
    private RecyclerShopsAdapter adapter;

    private ImageView imgShop;
    private ImageView imgHeart;
    private ImageView imgInstagram;
    private ImageView imgTelegram;
    private ImageView imgEmail;
    private ImageView imgDomain;
    private ImageView imgHambur;
    private ImageView imgShare;

    private Button btnInfo;
    private Button btnContact;

    private TextView txtAddress;
    private TextView txtViewCount;
    private TextView txtHourWork;

    private RecyclerView rvProductList;

    private boolean checkHeart = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ShopsPresenter(new KalaBeanRepository());
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
        imgDomain = rootView.findViewById(R.id.img_fragmentShop_domain);
        imgShare = rootView.findViewById(R.id.img_fragmentShop_share);
        imgHambur = rootView.findViewById(R.id.img_fragmentShop_hambur);

        btnInfo = rootView.findViewById(R.id.btn_fragmentShop_info);
        btnContact = rootView.findViewById(R.id.btn_fragmentShop_contact);

        txtAddress = rootView.findViewById(R.id.txt_fragmentShop_address);
        txtViewCount = rootView.findViewById(R.id.txt_fragmentShop_countView);
        txtHourWork = rootView.findViewById(R.id.txt_fragmentShop_hourWork);

        rvProductList = rootView.findViewById(R.id.rv_fragmentShop_list);

        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkHeart){
                    checkHeart = false;
                    imgHeart.setImageResource(R.drawable.white_heart);
                }else{
                    checkHeart = true;
                    imgHeart.setImageResource(R.drawable.red_heart);
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
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getShopsList(ShopsList shops) {

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
