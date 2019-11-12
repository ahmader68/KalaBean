package com.intek.kalabean.Adapters;

import android.content.Context;
import android.os.Bundle;
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

import com.intek.kalabean.Model.BrandList;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

        Picasso.get().load(url).into(holder.imgProfile);
        holder.txtStoreName.setText(brand.getTitleFA());
        holder.txtStoreCount.setText(brand.getShopCount());
        //holder.txtFloorCount.setText(store.getStoreCount());
        holder.txtAddress.setText(brand.getAddress());

        List<BrandList.Brands.SubSettings> settings = brand.getSettings();

        if (settings.get(3).getValue().equals("1")){
            holder.imgCafe.setImageResource(R.drawable.ic_black_coffee);
        }

        if (settings.get(4).getValue().equals("1")){
            holder.imgStair.setImageResource(R.drawable.ic_black_parking);
        }

        holder.cv_rvCircle_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("SellCenterID" , brand.getSellCenterID());
                bundle.putString("image" , url);
                bundle.putString("title" , brand.getTitleFA());
                bundle.putInt("flag",brand.getSellCenterCatID());
                FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ShopsFragment shopsFragment = new ShopsFragment();
                shopsFragment.setArguments(bundle);
                transaction.replace(R.id.frm_fragmentMain_mainLayout , shopsFragment);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandList.getStoreList().size();
    }

    class BrandsViewHolder extends RecyclerView.ViewHolder{
        CardView cv_rvCircle_layout;
        CircleImageView imgProfile;
        TextView txtStoreName;
        TextView txtStoreCount;
        TextView txtFloorCount;
        TextView txtAddress;
        ImageView imgStair;
        ImageView imgCafe;
        ImageView imgShop;
        ImageView imgGift;
        ImageView imgElevator;
        ImageView imgPlay;
        ImageView imgWC;
        ImageView imgParking;
        ImageView imgNet;
        public BrandsViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_rvCircle_layout = itemView.findViewById(R.id.cv_rvCircle_layout);
            imgProfile = itemView.findViewById(R.id.cimg_rvCircle_profile);
            txtStoreName = itemView.findViewById(R.id.txt_rvCircle_storeNameContent);
            txtStoreCount = itemView.findViewById(R.id.txt_rvCircle_countStoreCount);
            txtFloorCount = itemView.findViewById(R.id.txt_rvCircle_floorCountContent);
            txtAddress = itemView.findViewById(R.id.txt_rvCircle_addressContent);
            imgStair = itemView.findViewById(R.id.img_rvCircle_stair);
            imgCafe = itemView.findViewById(R.id.img_rvCircle_cafe);
            imgShop = itemView.findViewById(R.id.img_rvCircle_shop);
            imgGift = itemView.findViewById(R.id.img_rvCircle_gift);
            imgElevator = itemView.findViewById(R.id.img_rvCircle_elevator);
            imgPlay = itemView.findViewById(R.id.img_rvCircle_play);
            imgWC = itemView.findViewById(R.id.img_rvCircle_wc);
            imgParking = itemView.findViewById(R.id.img_rvCircle_parking);
            imgNet = itemView.findViewById(R.id.img_rvCircle_net);
        }
    }
}
