package com.intech.kalabean.Ticket;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.intech.kalabean.Adapters.RecyclerTicketAdapter;
import com.intech.kalabean.Base.BaseFragment;
import com.intech.kalabean.Data.KalaBeanRepository;
import com.intech.kalabean.Model.Ticket;
import com.intech.kalabean.R;

import java.util.List;

public class TicketFragment extends BaseFragment implements TicketContract.View {
    private TicketContract.Presenter presenter;
    private TextInputLayout til_fragmentTicket_subject;
    private TextInputLayout til_fragmentTicket_question;
    private TextInputEditText edt_fragmentTicket_subject;
    private TextInputEditText edt_fragmentTicket_question;
    private Button btn_fragmentTicket_makeTicket;
    private Button btn_fragmentTicket_cancel;
    private Button btn_fragmentTicket_send;
    private RecyclerView rv_fragmentTicket_ticketList;
    private ConstraintLayout con_fragmentTicket_form;
    private RecyclerTicketAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TicketPresenter(new KalaBeanRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_ticket;
    }

    @Override
    public void setupViews() {
        til_fragmentTicket_subject = rootView.findViewById(R.id.til_fragmentTicket_subject);
        til_fragmentTicket_question = rootView.findViewById(R.id.til_fragmentTicket_question);
        edt_fragmentTicket_subject = rootView.findViewById(R.id.edt_fragmentTicket_subject);
        edt_fragmentTicket_question = rootView.findViewById(R.id.edt_fragmentTicket_question);
        btn_fragmentTicket_makeTicket = rootView.findViewById(R.id.btn_fragmentTicket_makeTicket);
        btn_fragmentTicket_cancel = rootView.findViewById(R.id.btn_fragmentTicket_cancel);
        btn_fragmentTicket_send = rootView.findViewById(R.id.btn_fragmentTicket_send);
        rv_fragmentTicket_ticketList = rootView.findViewById(R.id.rv_fragmentTicket_ticketList);
        con_fragmentTicket_form = rootView.findViewById(R.id.con_fragmentTicket_form);


        btn_fragmentTicket_makeTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (con_fragmentTicket_form.getVisibility() == View.GONE) {
                    con_fragmentTicket_form.setVisibility(View.VISIBLE);
                } else if (con_fragmentTicket_form.getVisibility() == View.VISIBLE){
                    con_fragmentTicket_form.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTickets(List<Ticket> tickets) {
        adapter = new RecyclerTicketAdapter(getViewContext() , tickets);
        rv_fragmentTicket_ticketList.setLayoutManager(new LinearLayoutManager(getViewContext() , RecyclerView.VERTICAL , false));
        rv_fragmentTicket_ticketList.setAdapter(adapter);
    }

    @Override
    public void showSuccess(int id) {
        if(id > 0){
            showMessage("تیکت با موفقیت ارسال شد");
        }
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
