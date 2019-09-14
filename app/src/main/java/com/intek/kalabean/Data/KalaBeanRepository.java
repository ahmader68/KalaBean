package com.intek.kalabean.Data;

import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.ShopCenter;
import com.intek.kalabean.Model.ShopCenterList;
import com.intek.kalabean.Model.StoreDif;
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
    public Single<ActivityKindList> getActivityKind() {
        return serverDataSource.getActivityKind();
    }


    @Override
    public Single<Ticket> sendTicket(Ticket ticket) {
        return serverDataSource.sendTicket(ticket);
    }

    @Override
    public Single<StoreList> getMarkets(int SellCenterCatID, int CityCenterID) {
        return serverDataSource.getMarkets(SellCenterCatID , CityCenterID);
    }

    @Override
    public Single<ShopCenterList> getShopCenterList(int idCenterCat) {
        return serverDataSource.getShopCenterList(idCenterCat);
    }

    @Override
    public Single<FloorList> getFloorList(int idCenter) {
        return serverDataSource.getFloorList(idCenter);
    }

    @Override
    public Single<StoreDif> storeDefinition(StoreDif storeDif) {
        return serverDataSource.storeDefinition(storeDif);
    }
}
