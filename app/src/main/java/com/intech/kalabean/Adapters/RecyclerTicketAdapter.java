package com.intech.kalabean.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.intech.kalabean.Model.Ticket;
import com.intech.kalabean.R;

import java.util.List;

public class RecyclerTicketAdapter extends RecyclerView.Adapter<RecyclerTicketAdapter.TicketViewHolder> {

    private Context context;
    private List<Ticket> tickets;

    public RecyclerTicketAdapter(Context context , List<Ticket> tickets){
        this.context = context;
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_ticket , parent , false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    TextView txt_rvTicket_nameHolder;
    TextView txt_rvTicket_subjectHolder;
    TextView txt_rvTicket_contentHolder;
    TextView txt_rvTicket_statusHolder;
    TextView txt_rvTicket_dateHolder;
    TextView txt_rvTicket_hourHolder;

    class TicketViewHolder extends RecyclerView.ViewHolder{

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_rvTicket_nameHolder = itemView.findViewById(R.id.txt_rvTicket_nameHolder);
            txt_rvTicket_subjectHolder = itemView.findViewById(R.id.txt_rvTicket_subjectHolder);
            txt_rvTicket_contentHolder = itemView.findViewById(R.id.txt_rvTicket_contentHolder);
            txt_rvTicket_statusHolder = itemView.findViewById(R.id.txt_rvTicket_statusHolder);
            txt_rvTicket_dateHolder = itemView.findViewById(R.id.txt_rvTicket_dateHolder);
            txt_rvTicket_hourHolder = itemView.findViewById(R.id.txt_rvTicket_hourHolder);
        }
    }
}
