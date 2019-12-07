package com.intek.kalabean.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

public class RecyclerHomeProductAdapter extends RecyclerView.Adapter<RecyclerHomeProductAdapter.ProductHolder> {
    private Context context;
    private ProductList productLists;
    public RecyclerHomeProductAdapter(Context context, ProductList productLists) {
        this.context = context;
        this.productLists = productLists;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_selected_product,parent,false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        final ProductList.Product product = productLists.getItems().get(position);
        if (!(product.getCoverimage().isEmpty())) {
            Picasso.get().load(product.getCoverimage()).fit().into(holder.imgProduct);
        }
        holder.txtSelectedProduct.setText(product.getTitle());
    }

    @Override
    public int getItemCount() {
        return productLists.getItems().size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{
        ImageView imgProduct;
        TextView txtSelectedProduct;
        ProductHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_rvSelectedProduct_image);
            txtSelectedProduct = itemView.findViewById(R.id.txt_rvSelectedProduct);
        }
    }
}
