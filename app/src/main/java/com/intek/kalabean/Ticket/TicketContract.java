package com.intek.kalabean.Ticket;

import com.intek.kalabean.Base.BasePresenter;
import com.intek.kalabean.Base.BaseView;
import com.intek.kalabean.Model.Ticket;

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
        void onSuccessSendTicket(Ticket ticket);
        void onError(String message);
    }
}
