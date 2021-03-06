package com.intek.kalabean.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;
import com.intek.kalabean.ShowShop.ShowShopFragment;
import com.squareup.picasso.Picasso;


public class RecyclerShopsAdapter extends RecyclerView.Adapter<RecyclerShopsAdapter.ShopViewHolder> {

    private ShopsList shopsList;
    private Activity context;
    private String complexName;

    public RecyclerShopsAdapter(Activity context , ShopsList shopsList,String complexName){
        this.context = context;
        this.shopsList = shopsList;
        this.complexName = complexName;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_shops , parent , false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        final ShopsList.Shops shop = shopsList.getItems().get(position);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int w = (width/2) - 40;

        holder.imgShop.setMinimumHeight(w);
        holder.imgShop.setMinimumWidth(w);

        Picasso.get().load(shop.getImage()).into(holder.imgShop);
        holder.txtTitle.setText(shop.getTitleFA());

        holder.cv_shops.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("SellCenterID" , shop.getSellCenterID());
            bundle.putString("image" , shop.getImage());
            bundle.putString("title" , shop.getTitleFA());
            bundle.putInt("ShopId" , shop.getShopid());
            bundle.putInt("visitCount",shop.getVisiteCount());
            bundle.putString("address",shop.getAddressFA());
            bundle.putString("tel",shop.getTel());
            bundle.putString("web",shop.getLinksite());
            bundle.putString("complexName",complexName);



            FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            ShowShopFragment shopShopFragment = new ShowShopFragment();
            shopShopFragment.setArguments(bundle);
            transaction.replace(R.id.frm_fragmentMain_mainLayout , shopShopFragment);
            transaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return shopsList.getItems().size();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_shops;
        private ImageView imgShop;
        private TextView txtTitle;
        ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_shops = itemView.findViewById(R.id.cv_shops);
            imgShop = itemView.findViewById(R.id.img_rvShops_shopImage);
            txtTitle = itemView.findViewById(R.id.txt_rvShops_shopTitle);
        }
    }
}