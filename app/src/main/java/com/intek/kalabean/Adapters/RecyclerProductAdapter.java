package com.intek.kalabean.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.glide.slider.library.SliderLayout;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.ProductHolder> {
    private Activity context;
    private ProductList productLists;
    private Dialog dialogProduct;
    //Product Dialog Views

    TextView txtTitle , txtDesc , txtPrice , txtPriceReduced , txtCalender , txtLink;
    SliderLayout slider;
    ConstraintLayout conClose , conShare;

    ////////////////////////
    public RecyclerProductAdapter(Activity context,ProductList productLists) {
        this.context = context;
        this.productLists = productLists;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_product,parent,false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        final ProductList.Product product = productLists.getItems().get(position);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int w = (width/3) - 20;
        holder.imgProduct.setMinimumWidth(w);
        holder.imgProduct.setMinimumHeight(w);
        if (!(product.getCoverimage().isEmpty())) {
            Picasso.get().load(product.getCoverimage()).fit().into(holder.imgProduct);
        }

        holder.imgProduct.setOnClickListener(v -> {
            /*
            dialogProduct = new Dialog(context);
            dialogProduct.setContentView(R.layout.dialog_product);
            txtTitle = dialogProduct.findViewById(R.id.txt_dialogProduct_toolbar);
            txtDesc = dialogProduct.findViewById(R.id.txt_dialogProduct_brief);
            txtPrice = dialogProduct.findViewById(R.id.txt_dialogProduct_price);
            txtPriceReduced = dialogProduct.findViewById(R.id.txt_dialogProduct_priceReduced);
            txtCalender = dialogProduct.findViewById(R.id.txt_dialogProduct_calender);
            txtLink = dialogProduct.findViewById(R.id.txt_dialogProduct_link);
            slider = dialogProduct.findViewById(R.id.slider_dialogProduct_slider);
            conClose = dialogProduct.findViewById(R.id.con_dialogProduct_close);
            conShare = dialogProduct.findViewById(R.id.con_dialogProduct_share);

            txtTitle.setText(product.getTitle());
            txtDesc.setText(product.getBrief());

             */
        });
    }

    @Override
    public int getItemCount() {
        return productLists.getItems().size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{
        ImageView imgProduct;
        ProductHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}
