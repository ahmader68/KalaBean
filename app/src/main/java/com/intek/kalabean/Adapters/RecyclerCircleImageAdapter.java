package com.intek.kalabean.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsFragment;
import com.squareup.picasso.Picasso;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerCircleImageAdapter extends RecyclerView.Adapter<RecyclerCircleImageAdapter.StoreInfoHolder> {
    private Context context;
    private StoreList stores;
    public RecyclerCircleImageAdapter(Context context,StoreList stores) {
        this.context = context;
        this.stores = stores;
    }

    @NonNull
    @Override
        public StoreInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_circle,parent,false);
        return new StoreInfoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreInfoHolder holder, int position) {
        final StoreList.Store store = stores.getStoreList().get(position);

        String imgUrl = store.getImage();
        final String[] separated = imgUrl.split("'");
        final String url = separated[0];

        Picasso.get().load(url).into(holder.imgProfile);
        holder.txtStoreName.setText(store.getTitleFA());
        holder.txtStoreCount.setText(store.getShopCount());
        //holder.txtFloorCount.setText(store.getStoreCount());
        holder.txtAddress.setText(store.getAddress());

        List<StoreList.Store.SubSettings> settings = store.getSettings();

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
                bundle.putInt("SellCenterID" , store.getSellCenterID());
                bundle.putString("image" , url);
                bundle.putString("title" , store.getTitleFA());
                bundle.putInt("flag",store.getSellCenterCatID());
                FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ShopsFragment shopsFragment = new ShopsFragment();
                shopsFragment.setArguments(bundle);
                transaction.replace(R.id.frm_fragmentMain_mainLayout , shopsFragment);
                transaction.commit();
            }
        });
/*
        if(store.isStair()){
            holder.imgStair.setImageResource(R.drawable.ic_launcher_background);
        }
        if(store.isCafe()){
            holder.imgCafe.setImageResource(R.drawable.ic_launcher_background);
        }
        if(store.isElevator()){
            holder.imgElevator.setImageResource(R.drawable.ic_launcher_background);
        }
        if(store.isGift()){
            holder.imgGift.setImageResource(R.drawable.ic_launcher_background);
        }
        if(store.isParking()){
            holder.imgParking.setImageResource(R.drawable.ic_launcher_background);
        }
        if(store.isPlay()){
            holder.imgPlay.setImageResource(R.drawable.ic_launcher_background);
        }
        if(store.isShop()){
            holder.imgShop.setImageResource(R.drawable.ic_launcher_background);
        }
        if(store.isWc()){
            holder.imgWC.setImageResource(R.drawable.ic_launcher_background);
        }if(store.isWifi()){
            holder.imgNet.setImageResource(R.drawable.ic_launcher_background);
        }
        */
    }

    @Override
    public int getItemCount() {
        return stores.getStoreList().size();
    }

    class StoreInfoHolder extends RecyclerView.ViewHolder{
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
        StoreInfoHolder(@NonNull View itemView) {
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
