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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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

    @POST("default.aspx?Action=shopCenterList")
    Single<ShopCenterList>getShopCenterList(@Query("idCenterCat") int idCenterCat);

    @POST("default.aspx?Action=floorList")
    Single<FloorList>getFloorList(@Query("idCenter") int idCenter);

    @POST("")
    Single<String> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);

    @POST("default.aspx?Action=SellCenterCat")
    Single<MallKindList> getStoreKind();

    @POST("default.aspx?Action=GetJobPosition")
    Single<ActivityKindList> getActivityKind();

    @FormUrlEncoded
    @POST("")
    Single<Ticket> sendTicket(@Field("userId") int id,
                              @Field("title") String title,
                              @Field("content") String content);

    @FormUrlEncoded
    @POST("default.aspx?Action=ListBazar")
    Single<StoreList> getMarkets(@Field("SellCenterCatID") int SellCenterCatID,
                                 @Field("CityId") int CityCenterID);

    @FormUrlEncoded
    @POST("default.aspx?Action=store_definition")
    Single<StoreDif> storeDefinition(@Field("shopCenterKind") int shopCenterKind,
                                     @Field("shopCenterName") int shopCenterName,
                                     @Field("storeFloor") int storeFloor,
                                     @Field("fstoreName") String fstoreName,
                                     @Field("activityField") int activityField,
                                     @Field("phone") String phone,
                                     @Field("faddress") String faddress,
                                     @Field("cityid") int cityid,
                                     @Field("jobcatid") int jobcatid,
                                     @Field("fax") String fax,
                                     @Field("email") String email);
}
