package com.intek.kalabean.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.ProductHolder> {
    private Context context;
    private ProductList productLists;
    public RecyclerProductAdapter(Context context,ProductList productLists) {
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
        Picasso.get().load(product.getCoverimage()).into(holder.imgProduct);
        holder.txtProduct.setText(product.getTitle());
    }

    @Override
    public int getItemCount() {
        return productLists.getItems().size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{
        ImageView imgProduct;
        TextView txtProduct;
        ProductHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProduct = itemView.findViewById(R.id.txtProduct);
        }
    }
}
