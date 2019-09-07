package com.intek.kalabean.Ticket;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.intek.kalabean.Base.BaseFragment;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Model.Ticket;
import com.intek.kalabean.R;

import java.util.List;

public class TicketFragment extends BaseFragment implements TicketContract.View {
    private TicketContract.Presenter presenter;

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

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTickets(List<Ticket> tickets) {

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
}
