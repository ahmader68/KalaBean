package com.intek.kalabean.Data;

import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.ChainStoreList;
import com.intek.kalabean.Model.ComplexList;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.Model.Ticket;
import com.intek.kalabean.Model.User;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class KalaBeanRepository implements KalaBeanDataSource {
    private ServerDataSource serverDataSource = new ServerDataSource();
    @Override
    public Single<User> register(User user) {
        return serverDataSource.register(user);
    }

    @Override
    public Single<String> upload(MultipartBody.Part file, RequestBody name) {
        return serverDataSource.upload(file , name);
    }

    @Override
    public Single<User> login(User user) {
        return serverDataSource.login(user);
    }

    @Override
    public Single<MallKindList> getStoreKind() {
        return serverDataSource.getStoreKind();
    }

    @Override
    public Single<List<ActivityKind>> getActivityKind() {
        return serverDataSource.getActivityKind();
    }

    @Override
    public Single<Ticket> sendTicket(Ticket ticket) {
        return serverDataSource.sendTicket(ticket);
    }

    @Override
    public Single<StoreList> getMarkets(int SellCenterCatID, int CityId) {
        return serverDataSource.getMarkets(SellCenterCatID , CityId);
    }

    @Override
    public Single<ComplexList> getComplex(int SellCenterCatID, int CityId) {
        return serverDataSource.getComplex(SellCenterCatID, CityId);
    }

    @Override
    public Single<ChainStoreList> getChainStore(int SellCenterCatID, int CityId) {
        return serverDataSource.getChainStore(SellCenterCatID, CityId);
    }

    @Override
    public Single<ShopsList> getShops(int SellCenterID, int FloorID) {
        return serverDataSource.getShops(SellCenterID , FloorID);
    }
}
