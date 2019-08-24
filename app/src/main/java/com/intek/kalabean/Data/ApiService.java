package com.intek.kalabean.Data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("index.aspx")
    Single<String> str();

    @GET("somthing")
    Single<List<String>> getStrings();
}
