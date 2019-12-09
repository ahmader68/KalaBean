package com.intek.kalabean.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        final String storeCount ="فروشگاه ها  " + store.getShopCount();


        Picasso.get().load(url).into(holder.imgProfile);
        holder.txtStoreName.setText(store.getTitleFA());
        holder.txtStoreCount.setText(storeCount);


        holder.con_rvCircle_layout.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("SellCenterID" , store.getSellCenterID());
            bundle.putString("image" , url);
            bundle.putString("title" , store.getTitleFA());
            bundle.putInt("flag",store.getSellCenterCatID());
//                bundle.putInt("back",4);
            ShopsFragment.flag = 4;
            FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            ShopsFragment shopsFragment = new ShopsFragment();
            shopsFragment.setArguments(bundle);
            transaction.replace(R.id.frm_fragmentMain_mainLayout , shopsFragment);
            transaction.commit();
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
        ConstraintLayout con_rvCircle_layout;
        ImageView imgProfile;
        TextView txtStoreName;
        TextView txtStoreCount;
        StoreInfoHolder(@NonNull View itemView) {
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
