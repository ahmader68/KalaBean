package com.intek.kalabean.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.intek.kalabean.Model.Product;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.ProductHolder> {
    private Context context;
    private List<Product> products;
    public RecyclerProductAdapter(Context context,List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_shops,parent,false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        final Product product = products.get(position);
        Picasso.get().load(product.getImgProduct()).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        ImageView imgProduct;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_rvSquare_image);
        }
    }
}
