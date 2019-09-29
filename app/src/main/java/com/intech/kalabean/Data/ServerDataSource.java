package com.intech.kalabean.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intech.kalabean.Classes.Network;

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
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerDataSource implements KalaBeanDataSource {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private ApiService apiService;
    ServerDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(Network.getOkHttpClient())
                .baseUrl("http://kalabean.com/wservice/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Single<User> register(User user) {
        return apiService.register(user.getFirstName(),user.getLastName(),user.getPassword(),user.getProvince(),user.getCity(),user.getMobile(),user.getPassword());
    }

    @Override
    public Single<String> upload(MultipartBody.Part file, RequestBody name) {
        return apiService.uploadFile(file , name);
    }

    @Override
    public Single<User> login(User user) {
        return apiService.login(user.getMobile(),user.getPassword(),0);
    }

    @Override
    public Single<MallKindList> getStoreKind() {
        return apiService.getStoreKind();
    }

    @Override
    public Single<ActivityKindList> getActivityKind() {
        return apiService.getActivityKind();
    }


    @Override
    public Single<Ticket> sendTicket(Ticket ticket) {
        return apiService.sendTicket(ticket.getUserId(),ticket.getTitle(),ticket.getContent());
    }

    @Override
    public Single<Ticket> getTicket(int id) {
        return apiService.getTicket(id);
    }

    @Override
    public Single<StoreList> getMarkets(int SellCenterCatID, int CityId) {
        return apiService.getMarkets(SellCenterCatID , CityId);
    }

    @Override
    public Single<ComplexList> getComplex(int SellCenterCatID, int CityId) {
        return apiService.getComplex(SellCenterCatID , CityId);
    }

    @Override
    public Single<ChainStoreList> getChainStore(int SellCenterCatID, int CityId) {
        return apiService.getChainStore(SellCenterCatID , CityId);
    }

    @Override
    public Single<BrandList> getBrands(int SellCenterCatID) {
        return apiService.getBrands(SellCenterCatID);
    }

    @Override
    public Single<ShopsList> getShops(int SellCenterID, int FloorID) {
        return apiService.getShops(SellCenterID , FloorID);
    }

    @Override
    public Single<Init> getInit() {
        return apiService.getInit();
    }

    @Override
    public Single<ProductList> getProduct(int shopID) {
        return apiService.getProduct(shopID);
    }

    @Override
    public Single<UserShop> getUserShop(int CreatorId) {
        return apiService.getUserShop(CreatorId);
    }


    @Override
    public Single<ShopCenterList> getShopCenterList(int idCenterCat) {
        return apiService.getShopCenterList(idCenterCat);
    }

    @Override
    public Single<FloorList> getFloorList(int idCenter) {
        return apiService.getFloorList(idCenter);
    }

    @Override
    public Single<StoreDif> storeDefinition(StoreDif storeDif) {
        return apiService.storeDefinition(storeDif.getShopCenterKind(),storeDif.getShopCenterName(),storeDif.getStoreFloor(),storeDif.getFstoreName(),storeDif.getPhone(),storeDif.getFaddress(),storeDif.getCityid(),storeDif.getJobcatid(),storeDif.getFax(),storeDif.getEmail());
    }


}
