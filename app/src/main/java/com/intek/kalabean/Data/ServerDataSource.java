package com.intek.kalabean.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intek.kalabean.Classes.Network;

import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.ChainStoreList;
import com.intek.kalabean.Model.ComplexList;
import com.intek.kalabean.Model.Init;
import com.intek.kalabean.Model.MallKindList;

import com.intek.kalabean.Model.Product;
import com.intek.kalabean.Model.ShopCenter;

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
    public Single<ShopsList> getShops(int SellCenterID, int FloorID) {
        return apiService.getShops(SellCenterID , FloorID);
    }

    @Override
    public Single<Init> getInit() {
        return apiService.getInit();
    }

    @Override
    public Single<List<Product>> getProduct(int shopID) {
        return apiService.getProduct(shopID);
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
