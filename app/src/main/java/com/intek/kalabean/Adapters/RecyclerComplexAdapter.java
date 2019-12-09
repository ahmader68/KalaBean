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

import com.intek.kalabean.Model.ComplexList;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerComplexAdapter extends RecyclerView.Adapter<RecyclerComplexAdapter.ComplexViewHolder> {

    private Context context;
    private ComplexList complexList;

    public RecyclerComplexAdapter(Context context, ComplexList complexList) {
        this.context = context;
        this.complexList = complexList;
    }

    @NonNull
    @Override
    public ComplexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_circle, parent, false);
        return new ComplexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplexViewHolder holder, int position) {
        final ComplexList.Complex complex = complexList.getStoreList().get(position);

        String imgUrl = complex.getImage();
        final String[] separated = imgUrl.split("'");
        final String url = separated[0];
        final String storeCount = "فروشگاه ها  " + complex.getShopCount();


        Picasso.get().load(url).into(holder.imgProfile);
        holder.txtStoreName.setText(complex.getTitleFA());
        holder.txtStoreCount.setText(storeCount);



        holder.con_rvCircle_layout.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("SellCenterID", complex.getSellCenterID());
            bundle.putString("image", url);
            bundle.putString("title", complex.getTitleFA());
            bundle.putString("address", complex.getAddress());
            bundle.putInt("flag", complex.getSellCenterCatID());
//            bundle.putInt("back", 3);
            ShopsFragment.flag = 3;

            FragmentManager manager = ((FragmentActivity) context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            ShopsFragment shopsFragment = new ShopsFragment();
            shopsFragment.setArguments(bundle);
            transaction.replace(R.id.frm_fragmentMain_mainLayout, shopsFragment);

        holder.con_rvCircle_layout.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("SellCenterID" , complex.getSellCenterID());
            bundle.putString("image" , url);
            bundle.putString("title" , complex.getTitleFA());
            bundle.putString("address" , complex.getAddress());
            bundle.putInt("flag",complex.getSellCenterCatID());

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
        return complexList.getStoreList().size();
    }

    class ComplexViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout con_rvCircle_layout;
        ImageView imgProfile;
        TextView txtStoreName;
        TextView txtStoreCount;

        ComplexViewHolder(@NonNull View itemView) {
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

