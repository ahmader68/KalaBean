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

public class RecyclerNewJobAdapter extends RecyclerView.Adapter<RecyclerNewJobAdapter.ProductHolder> {
    private Context context;
    private ShopsList shopsList;
    public RecyclerNewJobAdapter(Context context, ShopsList shopsList) {
        this.context = context;
        this.shopsList = shopsList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_newjobs,parent,false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        final ShopsList.Shops shops = shopsList.getItems().get(position);
        if (!(shops.getImage().isEmpty())) {

            Picasso.get().load(shops.getImage()).fit().into(holder.img_rvNewJobs_image);
        }
        holder.txt_rvNewJobs.setText(shops.getTitleFA());
        //holder.txt_rvNewJobCat.setText(String.valueOf(shops.getJobCatid()));
    }

    @Override
    public int getItemCount() {
        return shopsList.getItems().size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{
        ImageView img_rvNewJobs_image;
        TextView txt_rvNewJobs;
        TextView txt_rvNewJobCat;
        ProductHolder(@NonNull View itemView) {
            super(itemView);
            img_rvNewJobs_image = itemView.findViewById(R.id.img_rvNewJobs_image);
            txt_rvNewJobs = itemView.findViewById(R.id.txt_rvNewJobs);
            txt_rvNewJobCat = itemView.findViewById(R.id.txt_rvNewJobCat);
        }
    }
}
