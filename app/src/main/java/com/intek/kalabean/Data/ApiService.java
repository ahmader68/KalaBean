package com.intek.kalabean.Data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("index.aspx")
    Single<String> str();

}
