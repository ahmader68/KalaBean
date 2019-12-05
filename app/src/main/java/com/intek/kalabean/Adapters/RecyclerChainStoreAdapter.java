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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Model.ChainStoreList;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsFragment;
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
        final String storeCount ="فروشگاه ها  " + chainStore.getShopCount();
        Picasso.get().load(url).into(holder.imgProfile);
        holder.txtStoreName.setText(chainStore.getTitleFA());
        holder.txtStoreCount.setText(storeCount);
        //holder.txtFloorCount.setText(store.getStoreCount());


        holder.con_rvCircle_layout.setOnClickListener(new View.OnClickListener() {
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
                transaction.replace(R.id.frm_fragmentMain_mainLayout , shopsFragment);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chainStoreList.getStoreList().size();
    }

    class ChainStorViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout con_rvCircle_layout;
        ImageView imgProfile;
        TextView txtStoreName;
        TextView txtStoreCount;
        ChainStorViewHolder(@NonNull View itemView) {
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
