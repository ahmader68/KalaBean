package com.intek.kalabean.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Model.BrandList;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsFragment;
import com.squareup.picasso.Picasso;

public class RecyclerBrandsAdapter extends RecyclerView.Adapter<RecyclerBrandsAdapter.BrandsViewHolder> {

    private Context context;
    private BrandList brandList;

    public RecyclerBrandsAdapter(Context context, BrandList brandList){
        this.context = context;
        this.brandList = brandList;
    }

    @NonNull
    @Override
    public BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_circle , parent , false);
        return new BrandsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsViewHolder holder, int position) {
        final BrandList.Brands brand = brandList.getStoreList().get(position);

        String imgUrl = brand.getImage();
        final String[] separated = imgUrl.split("'");
        final String url = separated[0];
        final String storeCount ="فروشگاه ها  " + brand.getShopCount();

        Picasso.get().load(url).into(holder.imgProfile);
        holder.txtStoreName.setText(brand.getTitleFA());
        holder.txtStoreCount.setText(storeCount);


        holder.con_rvCircle_layout.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("SellCenterID" , brand.getSellCenterID());
            bundle.putString("image" , url);
            bundle.putString("title" , brand.getTitleFA());
            bundle.putString("address" , brand.getAddress());
            bundle.putInt("flag",brand.getSellCenterCatID());
            //bundle.putInt("back",1);
            ShopsFragment.flag = 1;
            FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            ShopsFragment shopsFragment = new ShopsFragment();
            shopsFragment.setArguments(bundle);
            transaction.replace(R.id.frm_fragmentMain_mainLayout , shopsFragment);
            transaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return brandList.getStoreList().size();
    }

    class BrandsViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout con_rvCircle_layout;
        ImageView imgProfile;
        TextView txtStoreName;
        TextView txtStoreCount;

        public BrandsViewHolder(@NonNull View itemView) {
            super(itemView);
            con_rvCircle_layout = itemView.findViewById(R.id.con_rvCirle_layout);
            imgProfile = itemView.findViewById(R.id.img_rvCircle_profile);
            txtStoreName = itemView.findViewById(R.id.txt_rvCircle_storeNameContent);
            txtStoreCount = itemView.findViewById(R.id.txt_rvCircle_coutnStore);
            txtStoreCount.setAlpha(0.5f);
            txtStoreName.setAlpha(0.5f);

        }
    }
}
