package com.intek.kalabean.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Model.Order;
import com.intek.kalabean.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerOrderAdapter extends RecyclerView.Adapter<RecyclerOrderAdapter.OrderViewHolder> {

    private List<Order> orders;
    private Context context;

    public RecyclerOrderAdapter(Context context , List<Order> orders){
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_orders , parent , false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        Picasso.get().load(order.getImage()).into(holder.img_rvOrder_image);
        holder.txt_rv_order_name.setText(order.getName());
        holder.txt_rv_order_time.setText(order.getTime());
        holder.txt_rv_order_title.setText(order.getTitle());
        holder.txt_rv_order_city.setText(order.getCity());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_rvOrder_image;
        private TextView txt_rv_order_name;
        private TextView txt_rv_order_time;
        private TextView txt_rv_order_title;
        private TextView txt_rv_order_city;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            img_rvOrder_image = itemView.findViewById(R.id.img_rvOrder_image);
            txt_rv_order_name = itemView.findViewById(R.id.txt_rv_order_name);
            txt_rv_order_time = itemView.findViewById(R.id.txt_rv_order_time);
            txt_rv_order_title = itemView.findViewById(R.id.txt_rv_order_title);
            txt_rv_order_city = itemView.findViewById(R.id.txt_rv_order_city);
        }
    }
}
