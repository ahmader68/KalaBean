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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @FormUrlEncoded
    @POST("default.aspx?Action=Login")
    Single<User>login(@Field("usr") String usr,
                      @Field("pwd") String pwd,
                      @Field("ShowImageType") int showimage);
    @FormUrlEncoded
    @POST("default.aspx?Action=Register")
    Single<User>register(@Field("firstname") String firstname,
                         @Field("lastname") String lastname,
                         @Field("pwd") String pwd,
                         @Field("province") String province,
                         @Field("city") String city,
                         @Field("mobile") String mobile,
                         @Field("pwd2") String pwd2);

    @POST("")
    Single<String> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);

    @POST("default.aspx?Action=SellCenterCat")
    Single<MallKindList> getStoreKind();

    @GET("")
    Single<List<ActivityKind>> getActivityKind();

    @FormUrlEncoded
    @POST("")
    Single<Ticket> sendTicket(@Field("userId") int id,
                              @Field("title") String title,
                              @Field("content") String content);

    @FormUrlEncoded
    @POST("default.aspx?Action=ListBazar")
    Single<StoreList> getMarkets(@Field("SellCenterCatID") int SellCenterCatID,
                                 @Field("CityId") int CityId);

    @FormUrlEncoded
    @POST("default.aspx?Action=ListBazar")
    Single<ComplexList> getComplex(@Field("SellCenterCatID") int SellCenterCatID,
                                   @Field("CityId") int CityId);

    @FormUrlEncoded
    @POST("default.aspx?Action=ListBazar")
    Single<ChainStoreList> getChainStore(@Field("SellCenterCatID") int SellCenterCatID,
                                         @Field("CityId") int CityId);

    @FormUrlEncoded
    @POST("default.aspx?Action=ShowShopsList")
    Single<ShopsList> getShops(@Field("SellCenterID") int SellCenterID,
                                     @Field("FloorID") int FloorID);
}
