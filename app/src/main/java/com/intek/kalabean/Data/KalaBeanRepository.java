package com.intek.kalabean.Data;

import com.intek.kalabean.Model.AddProduct;
import com.intek.kalabean.Model.BrandList;
import com.intek.kalabean.Model.ChainStoreList;
import com.intek.kalabean.Model.ComplexList;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.Init;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.MallKindList;

import com.intek.kalabean.Model.Positions;
import com.intek.kalabean.Model.ProductList;

import com.intek.kalabean.Model.SearchedProduct;
import com.intek.kalabean.Model.SearchedStore;
import com.intek.kalabean.Model.ShopCenterList;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.Model.StoreDif;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.Model.Ticket;
import com.intek.kalabean.Model.User;
import com.intek.kalabean.Model.UserShop;

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
    public Single<LoggedinUser> login(User user) {
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
    public Single<AddProduct> insertProduct(AddProduct product) {
        return serverDataSource.insertProduct(product);
    }

    @Override
    public Single<Positions> getPositions() {
        return serverDataSource.getPositions();
    }

    @Override
    public Single<User> getUserInfo(int userId) {
        return serverDataSource.getUserInfo(userId);
    }

    @Override
    public Single<User> editUser(int uid, String mobile, String email) {
        return serverDataSource.editUser(uid, mobile, email);
    }

    @Override
    public Single<ShopCenterList> getShopInfo(int shopId, int usrid) {
        return serverDataSource.getShopInfo(shopId, usrid);
    }

    @Override
    public Single<SearchedStore> searchStore(int SellCenterCatID, int SellCenterID, int FloorId, int JobCatid, String Title) {
        return serverDataSource.searchStore(SellCenterCatID, SellCenterID, FloorId, JobCatid, Title);
    }

    @Override
    public Single<SearchedProduct> searchProduct(int SellCenterCatID, int SellCenterID, int FloorId, int JobCatid, String ProductName) {
        return serverDataSource.searchProduct(SellCenterCatID, SellCenterID, FloorId, JobCatid, ProductName);
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
