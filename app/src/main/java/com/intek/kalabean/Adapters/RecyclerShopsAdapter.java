package com.intek.kalabean.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerShopsAdapter extends RecyclerView.Adapter<RecyclerShopsAdapter.ShopViewHolder> {

    private ShopsList shopsList;
    private Context context;

    public RecyclerShopsAdapter(Context context , ShopsList shopsList){
        this.context = context;
        this.shopsList = shopsList;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_shops , parent , false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        ShopsList.Shops shop = shopsList.getItems().get(position);
        Picasso.get().load(shop.getImage()).into(holder.imgShop);
        holder.txtTitle.setText(shop.getTitleFA());
    }

    @Override
    public int getItemCount() {
        return shopsList.getItems().size();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgShop;
        private TextView txtTitle;
        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShop = itemView.findViewById(R.id.img_rvShops_shopImage);
            txtTitle = itemView.findViewById(R.id.txt_rvShops_shopTitle);
        }
    }
}