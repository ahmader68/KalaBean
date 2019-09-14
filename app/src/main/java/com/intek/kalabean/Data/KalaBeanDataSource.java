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
import retrofit2.http.Part;

public interface KalaBeanDataSource {
    Single<User> register(User user);
    Single<String> upload(@Part MultipartBody.Part file, @Part("name") RequestBody name);
    Single<User> login(User user);
    Single<MallKindList> getStoreKind();
    Single<ActivityKindList> getActivityKind();
    Single<Ticket> sendTicket(Ticket ticket);
    Single<StoreList> getMarkets(int SellCenterCatID , int CityCenterID);
    Single<ShopCenterList>getShopCenterList(int idCenterCat);
    Single<FloorList>getFloorList(int idCenter);
    Single<StoreDif> storeDefinition(StoreDif storeDif);

}
