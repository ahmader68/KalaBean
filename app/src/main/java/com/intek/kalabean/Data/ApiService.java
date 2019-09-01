package com.intek.kalabean.Data;

import com.intek.kalabean.Model.Product;

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
    Single<User>register(@Field("name") String name,
                         @Field("family") String family,
                         @Field("gender") String gender,
                         @Field("email") String email,
                         @Field("mobile") String mobile,
                         @Field("phone") String phone,
                         @Field("password") String password,
                         @Field("state") String state,
                         @Field("city") String city,
                         @Field("address") String address);

    @POST("")
    Single<String> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);
}
