package com.intech.kalabean.Adapters;

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

import com.intech.kalabean.Model.ChainStoreList;
import com.intech.kalabean.R;
import com.intech.kalabean.Shops.ShopsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerChainStoreAdapter extends RecyclerView.Adapter<RecyclerChainStoreAdapter.ChainStorViewHolder> {

    private Context context;
    private ChainStoreList chainStoreList;

    public RecyclerChainStoreAdapter (Context context , ChainStoreList chainStoreList){
        this.context = context;
        this.chainStoreList = chainStoreList;
    }


    @NonNull
    @Override
    public ChainStorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_circle , parent , false);
        return new ChainStorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChainStorViewHolder holder, int position) {
        final ChainStoreList.ChainStore chainStore = chainStoreList.getStoreList().get(position);


        String imgUrl = chainStore.getImage();
        final String[] separated = imgUrl.split("'");
        final String url = separated[0];

        Picasso.get().load(url).into(holder.imgProfile);
        holder.txtStoreName.setText(chainStore.getTitleFA());
        holder.txtStoreCount.setText(chainStore.getShopCount());
        //holder.txtFloorCount.setText(store.getStoreCount());
        holder.txtAddress.setText(chainStore.getAddress());

        List<ChainStoreList.ChainStore.SubSettings> settings = chainStore.getSettings();

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
                bundle.putInt("SellCenterID" , chainStore.getSellCenterID());
                bundle.putString("image" , url);
                bundle.putString("title" , chainStore.getTitleFA());
                bundle.putInt("flag",chainStore.getSellCenterCatID());
                FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ShopsFragment shopsFragment = new ShopsFragment();
                shopsFragment.setArguments(bundle);
                transaction.replace(R.id.frm_MainActivity_mainLayout , shopsFragment);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chainStoreList.getStoreList().size();
    }

    class ChainStorViewHolder extends RecyclerView.ViewHolder {
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
        ChainStorViewHolder(@NonNull View itemView) {
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
