package com.intek.kalabean.Data;


import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.AddProduct;
import com.intek.kalabean.Model.BrandList;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.ChainStoreList;
import com.intek.kalabean.Model.ComplexList;
import com.intek.kalabean.Model.Init;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.MallKindList;

import com.intek.kalabean.Model.Positions;
import com.intek.kalabean.Model.ProductList;

import com.intek.kalabean.Model.ShopCenterList;
import com.intek.kalabean.Model.StoreDif;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.Model.Ticket;
import com.intek.kalabean.Model.User;


import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface KalaBeanDataSource {
    Single<User> register(User user);
    Single<String> upload(MultipartBody.Part file,RequestBody name);
    Single<LoggedinUser> login(User user);
    Single<MallKindList> getStoreKind();
    Single<ActivityKindList> getActivityKind();
    Single<Ticket> sendTicket(Ticket ticket);
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
    Single<AddProduct> insertProduct(AddProduct product);
    Single<Positions> getPositions();
}
