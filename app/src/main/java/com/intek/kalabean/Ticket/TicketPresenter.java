package com.intek.kalabean.Ticket;

import com.intek.kalabean.Classes.DatabaseMethods;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Model.Ticket;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TicketPresenter implements TicketContract.Presenter {
    private static TicketContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private KalaBeanDataSource kalaBeanDataSource;
    private final int databaseFlag = 14;
    public TicketPresenter(KalaBeanDataSource kalaBeanDataSource){
        this.kalaBeanDataSource = kalaBeanDataSource;
    }
    @Override
    public void sendTicket(Ticket ticket) {
        DatabaseMethods.sendTicket(databaseFlag,ticket);
    }

    @Override
    public void getTicket(List<Ticket> tickets) {

    }

    @Override
    public void onSuccessSendTicket(Ticket ticket) {
        TicketPresenter.view.showSuccess(ticket.getId());
    }

    @Override
    public void onError(String message) {
        TicketPresenter.view.showMessage(message);
    }

    @Override
    public void attachView(TicketContract.View view) {
        TicketPresenter.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if(compositeDisposable != null && compositeDisposable.size() > 0){
            compositeDisposable.clear();
        }
    }
}
