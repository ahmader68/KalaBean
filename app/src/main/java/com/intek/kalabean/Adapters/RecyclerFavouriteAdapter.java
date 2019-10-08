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

public class RecyclerFavouriteAdapter extends RecyclerView.Adapter<RecyclerFavouriteAdapter.ShopList> {
    ShopsList favShopsList;
    Context context;
    public RecyclerFavouriteAdapter(ShopsList favShopsList,Context context){
        this.favShopsList = favShopsList;
        this.context = context;
    }


    @NonNull
    @Override
    public ShopList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_favourite,parent,false);
        return new ShopList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopList holder, int position) {
        final ShopsList.Shops shops = favShopsList.getItems().get(position);
        Picasso.get().load(shops.getImage()).into(holder.imgFavShop);
        holder.txtShopName.setText(shops.getTitleFA());
        holder.txtCat.setText(shops.getDescriptionFA());
    }

    @Override
    public int getItemCount() {
        return favShopsList.getItems().size();
    }
    class ShopList extends RecyclerView.ViewHolder{
        ImageView imgFavShop;
        TextView txtShopName;
        TextView txtCat;
        public ShopList(@NonNull View itemView) {
            super(itemView);
            imgFavShop = itemView.findViewById(R.id.img_rvFavourite_image);
            txtShopName = itemView.findViewById(R.id.txt_rvFavourite_name);
            txtCat = itemView.findViewById(R.id.txt_rvFavourite_cat);
        }
    }
}
