package com.intek.kalabean.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.Floor;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.R;
import com.intek.kalabean.Shops.ShopsContract;
import com.intek.kalabean.Shops.ShopsPresenter;

import java.util.ArrayList;
import java.util.List;

import static kotlin.text.Typography.amp;

public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.FloorHolder> {

    private Context context;
    private FloorList floors;
    private int sellCenterId;
    private ShopsContract.Presenter presenter = new ShopsPresenter(new KalaBeanRepository());
    private int selectedPos = RecyclerView.NO_POSITION;

    private FloorHolder floorHolders;

    public FloorAdapter(Context context, FloorList floors, int sellCenterId) {
        this.context = context;
        this.floors = floors;
        this.sellCenterId = sellCenterId;
    }

    @NonNull
    @Override
    public FloorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_floor, viewGroup, false);
        return new FloorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FloorHolder holder, final int position) {
        final Floor floor = floors.getFloorList().get(position);

        holder.txt_rvFloor_title.setText(floor.getTitleFA());
        holder.cv_rvFloor.setOnClickListener(v -> {
            /* get position
            int pos = holder.getAdapterPosition();//position;//getAdapterPosition();
            holder.cv_rvFloor.setCardBackgroundColor(Color.GRAY);
            // check if item still exists
            if(pos != RecyclerView.NO_POSITION){
                Floor clickedDataItem = floors.getFloorList().get(pos);
                // here call your callback's method, or notify using other way
                notifyItemChanged(pos);
                pos = holder.getLayoutPosition();
                notifyItemChanged(pos);
            }

             */

            //notifyItemChanged(selectedPos);
            //selectedPos = holder.getLayoutPosition();
            //notifyItemChanged(selectedPos);
            //holder.cv_rvFloor.setCardBackgroundColor(Color.GRAY);
            /*
            for (int i = 0; i < floorHolders.size(); i++) {
                if (holder != floorHolders.get(i)){
                    floorHolders.get(i).cv_rvFloor.setCardBackgroundColor(Color.WHITE);
                    floorHolders.clear();
                }
            }
            floorHolders.add(holder);

             */
            if (floorHolders != null && holder != floorHolders){
                floorHolders.cv_rvFloor.setCardBackgroundColor(Color.WHITE);
            }
            floorHolders = holder;

            holder.cv_rvFloor.setCardBackgroundColor(Color.GRAY);
            presenter.getShops(sellCenterId, floor.getId());
        });
    }

    @Override
    public int getItemCount() {
        return floors.getFloorList().size();
    }

    class FloorHolder extends RecyclerView.ViewHolder {

        private CardView cv_rvFloor;
        private TextView txt_rvFloor_title;

        FloorHolder(@NonNull View itemView) {
            super(itemView);
            cv_rvFloor = itemView.findViewById(R.id.cv_rvFloor);
            txt_rvFloor_title = itemView.findViewById(R.id.txt_rvFloor_title);
        }
    }
}
