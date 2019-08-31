package com.intek.kalabean.Data;

import com.intek.kalabean.Model.User;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public interface KalaBeanDataSource {
    Single<User> register(User user);
    Single<String> upload(@Part MultipartBody.Part file, @Part("name") RequestBody name);
}
