package com.intek.kalabean.Oridinary_User_Profile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Adapters.RecyclerFavouriteAdapter;
import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class OUFragment extends BaseFragment implements OUContract.View {
    private OUContract.Presenter presenter;
    private CircularImageView cimgProfile;
    private TextView txtProfileName;
    ConstraintLayout conProfile;
    ConstraintLayout conBasket;
    ConstraintLayout conWallet;
    ConstraintLayout conMessage;
    RadioGroup sgFavourite;
    RadioButton rbFavProduct;
    RadioButton rbFavStore;
    RecyclerView rvList;
    RecyclerFavouriteAdapter favShopAdapter;
    ShopsList shops;
    ShopsList.Shops shop;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new OUPresenter(new KalaBeanRepository());
        shop.setTitleFA("hjgffhg");

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_ordinary_user_profile;
    }

    @Override
    public void setupViews() {
        cimgProfile = rootView.findViewById(R.id.cimg_fragmentOrdinaryUser_profilePic);
        txtProfileName = rootView.findViewById(R.id.txt_fragmentOrdinaryUser_name);
        conProfile = rootView.findViewById(R.id.con_fragmentOrdinaryUser_profile);
        conBasket = rootView.findViewById(R.id.con_fragmentOrdinaryUser_basket);
        conWallet = rootView.findViewById(R.id.con_fragmentOrdinaryUser_wallet);
        conMessage = rootView.findViewById(R.id.con_fragmentOrdinaryUser_message);
        sgFavourite = rootView.findViewById(R.id.sg_fragmentOrdinaryUser_fav);
        rbFavProduct = rootView.findViewById(R.id.rb_fragmentOrdinaryUser_favProd);
        rbFavStore = rootView.findViewById(R.id.rb_fragmentOrdinaryUser_favStore);
        rvList = rootView.findViewById(R.id.rv_fragmentOrdinaryUser_list);
        for (int i = 0;i < 20; i++){
            shop.setTitleFA("فروشگاه شماره"+i);
            shop.setDescriptionFA("پوشاک");
            shop.setImage("drawable://" + R.drawable.ic_launcher_foreground);
            shops.getItems().add(shop);
        }

        conProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "Profile", Toast.LENGTH_SHORT).show();
            }
        });
        conMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "Message", Toast.LENGTH_SHORT).show();
            }
        });
        conWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "Wallet", Toast.LENGTH_SHORT).show();
            }
        });
        conBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "Basket", Toast.LENGTH_SHORT).show();
            }
        });
        sgFavourite.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rb_fragmentOrdinaryUser_favProd){
                    Toast.makeText(getViewContext(), "1", Toast.LENGTH_SHORT).show();
                }else if(i == R.id.rb_fragmentOrdinaryUser_favStore){
                    favShopAdapter = new RecyclerFavouriteAdapter( shops,getViewContext());
                    rvList.setLayoutManager(new LinearLayoutManager(getViewContext(),RecyclerView.VERTICAL,false));
                    rvList.setAdapter(favShopAdapter);
                }
            }
        });
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showFavShop() {

    }

    @Override
    public void showFavProd() {

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
}
