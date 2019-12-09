package com.intek.kalabean.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.R;

import java.util.List;

public class RecyclerCategoryAdapter extends RecyclerView.Adapter<RecyclerCategoryAdapter.ViewHolder> {

    private List<String> items;
    private Context context;

    public RecyclerCategoryAdapter(Context context,List<String> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String item = items.get(position);
        holder.txtTitle.setText(item);
        holder.txtTitle.setOnClickListener(v -> Toast.makeText(context, item, Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_rvCategory_title);
        }
    }
}
