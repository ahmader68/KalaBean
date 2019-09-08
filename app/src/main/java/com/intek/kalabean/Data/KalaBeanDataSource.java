package com.intek.kalabean.Data;

import com.intek.kalabean.Model.ActivityKind;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.Store;
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
    Single<List<ActivityKind>> getActivityKind();
    Single<Ticket> sendTicket(Ticket ticket);
    Single<List<Store>> getMarkets(int catId , int cityId);
}
