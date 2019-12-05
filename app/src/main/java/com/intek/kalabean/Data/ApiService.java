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
import com.intek.kalabean.Model.UserShop;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("default.aspx?Action=Login")
    Single<LoggedinUser> login(@Field("usr") String usr,
                               @Field("pwd") String pwd,
                               @Field("ShowImageType") int showimage);

    @FormUrlEncoded
    @POST("default.aspx?Action=Register")
    Single<User> register(@Field("firstname") String firstname,
                          @Field("lastname") String lastname,
                          @Field("pwd") String pwd,
                          @Field("province") String province,
                          @Field("city") String city,
                          @Field("mobile") String mobile,
                          @Field("pwd2") String pwd2);

    @FormUrlEncoded
    @POST("default.aspx?Action=EditUser")
    Single<User> editUser(@Field("usrid") int usrid,
                          @Field("mobile") String mobile,
                          @Field("email") String email);

    @POST("default.aspx?Action=shopCenterList")
    Single<ShopCenterList> getShopCenterList(@Query("idCenterCat") int idCenterCat);

    @POST("default.aspx?Action=floorList")
    Single<FloorList> getFloorList(@Query("idCenter") int idCenter);

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

    @POST("")
    Single<Ticket> getTicket(@Query("userId") int id);

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

    @POST("default.aspx?Action=ListBazar")
    Single<BrandList> getBrands(@Query("SellCenterCatID") int SellCenterCatID);

    @FormUrlEncoded
    @POST("default.aspx?Action=ShowShopsList")
    Single<ShopsList> getShops(@Field("SellCenterID") int SellCenterID,
                               @Field("FloorID") int FloorID);

    @FormUrlEncoded
    @POST("default.aspx?Action=store_definition")
    Single<StoreDif> storeDefinition(@Field("userid") int userid,
                                     @Field("shopCenterKind") int shopCenterKind,
                                     @Field("shopCenterName") int shopCenterName,
                                     @Field("storeFloor") int storeFloor,
                                     @Field("fstoreName") String fstoreName,
                                     @Field("phone") String phone,
                                     @Field("faddress") String faddress,
                                     @Field("cityid") int cityid,
                                     @Field("jobcatid") int jobcatid,
                                     @Field("fax") String fax,
                                     @Field("email") String email);

    @POST("default.aspx?Action=Init")
    Single<Init> getInit();

    @FormUrlEncoded
    @POST("default.aspx?Action=ShowPeroductShop")
    Single<ProductList> getProduct(@Field("ShopId") int ShopId);

    @FormUrlEncoded
    @POST("default.aspx?Action=GetUserShop")
    Single<UserShop> getUserShop(@Field("CreatorId") int CreatorId);

    @FormUrlEncoded
    @POST("default.aspx?Action=InsertProducts")
    Single<AddProduct> insertProduct(@Field("usrid") int usrid,
                                     @Field("autolang") String autolang,
                                     @Field("CategoryId") int CategoryId,
                                     @Field("SubCategoryId") int SubCategoryId,
                                     @Field("Producer") int Producer,
                                     @Field("Price") int Price,
                                     @Field("TitleFA") String TitleFA,
                                     @Field("OrderNo") int OrderNo);

    @POST("default.aspx?Action=GetJobPosition")
    Single<Positions> getPositions();

    @FormUrlEncoded
    @POST("default.aspx?Action=GetInfoUser")
    Single<User> getUserInfo(@Field("usrid") int usrid);

    @FormUrlEncoded
    @POST("default.aspx?Action=GetInfoShop")
    Single<ShopCenterList> getShopInfo(@Field("shopId") int shopId,
                                       @Field("usrid") int usrid);

}
