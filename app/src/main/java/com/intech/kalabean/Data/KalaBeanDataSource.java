package com.intech.kalabean.Data;


import com.intech.kalabean.Model.ActivityKindList;
import com.intech.kalabean.Model.BrandList;
import com.intech.kalabean.Model.FloorList;
import com.intech.kalabean.Model.ChainStoreList;
import com.intech.kalabean.Model.ComplexList;
import com.intech.kalabean.Model.Init;
import com.intech.kalabean.Model.MallKindList;

import com.intech.kalabean.Model.ProductList;

import com.intech.kalabean.Model.ShopCenterList;
import com.intech.kalabean.Model.StoreDif;
import com.intech.kalabean.Model.ShopsList;
import com.intech.kalabean.Model.StoreList;
import com.intech.kalabean.Model.Ticket;
import com.intech.kalabean.Model.User;
import com.intech.kalabean.Model.UserShop;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface KalaBeanDataSource {
    Single<User> register(User user);
    Single<String> upload(MultipartBody.Part file,RequestBody name);
    Single<User> login(User user);
    Single<MallKindList> getStoreKind();
    Single<ActivityKindList> getActivityKind();
    Single<Ticket> sendTicket(Ticket ticket);
    Single<Ticket> getTicket(int id);
    Single<ShopCenterList>getShopCenterList(int idCenterCat);
    Single<FloorList>getFloorList(int idCenter);
    Single<StoreDif> storeDefinition(StoreDif storeDif);

    Single<StoreList> getMarkets(int SellCenterCatID , int CityId);
    Single<ComplexList> getComplex(int SellCenterCatID , int CityId);
    Single<ChainStoreList> getChainStore(int SellCenterCatID , int CityId);
    Single<BrandList> getBrands(int SellCenterCatID);
    Single<ShopsList> getShops(int SellCenterID , int FloorID);
    Single<Init> getInit();
    Single<ProductList> getProduct(int ShopId);
    Single<UserShop> getUserShop(int CreatorId);
}
