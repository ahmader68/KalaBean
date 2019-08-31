package com.intek.kalabean.Data;

import com.intek.kalabean.Model.User;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerDataSource implements KalaBeanDataSource {
    private ApiService apiService;
    public ServerDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("192.168.1.1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Single<User> register(User user) {
        return apiService.register(user.getName(),user.getFamily(),user.getGender(),user.getEmail(),user.getMobile(),user.getPhone(),user.getPassword(),user.getState(),user.getCity(),user.getAddress());
    }

    @Override
    public Single<String> upload(MultipartBody.Part file, RequestBody name) {
        return apiService.uploadFile(file , name);
    }
}
