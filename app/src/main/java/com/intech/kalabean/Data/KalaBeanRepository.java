package com.intech.kalabean.Data;

import com.intech.kalabean.Model.BrandList;
import com.intech.kalabean.Model.ChainStoreList;
import com.intech.kalabean.Model.ComplexList;
import com.intech.kalabean.Model.ActivityKindList;
import com.intech.kalabean.Model.FloorList;
import com.intech.kalabean.Model.Init;
import com.intech.kalabean.Model.MallKindList;

import com.intech.kalabean.Model.ProductList;

import com.intech.kalabean.Model.ShopCenterList;
import com.intech.kalabean.Model.ShopsList;
import com.intech.kalabean.Model.StoreDif;
import com.intech.kalabean.Model.StoreList;
import com.intech.kalabean.Model.Ticket;
import com.intech.kalabean.Model.User;
import com.intech.kalabean.Model.UserShop;

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
    public Single<Ticket> getTicket(int id) {
        return serverDataSource.getTicket(id);
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
    public Single<BrandList> getBrands(int SellCenterCatID) {
        return serverDataSource.getBrands(SellCenterCatID);
    }

    @Override
    public Single<ShopsList> getShops(int SellCenterID, int FloorID) {
        return serverDataSource.getShops(SellCenterID , FloorID);
    }

    @Override
    public Single<Init> getInit() {
        return serverDataSource.getInit();
    }

    @Override
    public Single<ProductList> getProduct(int shopID) {
        return serverDataSource.getProduct(shopID);
    }

    @Override
    public Single<UserShop> getUserShop(int CreatorId) {
        return serverDataSource.getUserShop(CreatorId);
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
