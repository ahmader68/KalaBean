package com.intech.kalabean.Ticket;

import com.intech.kalabean.Base.BasePresenter;
import com.intech.kalabean.Base.BaseView;
import com.intech.kalabean.Model.Ticket;

import java.util.List;

public interface TicketContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void showTickets(List<Ticket> tickets);
        void showSuccess(int id);
    }
    interface Presenter extends BasePresenter<View>{
        void sendTicket(Ticket ticket);
        void getTicket(List<Ticket> tickets);
    }
}
