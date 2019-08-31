package com.intek.kalabean.Data;

import com.intek.kalabean.Model.Product;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @POST("")
    Single<String> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);
}
