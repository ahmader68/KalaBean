package com.intek.kalabean.Data;

import com.intek.kalabean.Model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @GET("")
    Single<User>register(@Field("name") String name,
                         @Field("family") String family,
                         @Field("gender") int gender,
                         @Field("email") String email,
                         @Field("mobile") String mobile,
                         @Field("phone") String phone,
                         @Field("password") String password,
                         @Field("state") String state,
                         @Field("city") String city,
                         @Field("address") String address);


}
