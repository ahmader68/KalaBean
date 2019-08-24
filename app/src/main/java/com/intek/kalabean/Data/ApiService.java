package com.intek.kalabean.Data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("index.aspx")
    Single<String> str();
    @POST("login.aspx")
    Single<List<String>> users();

    @GET("somthing")
    Single<List<String>> getStrings();
}
