package com.intek.kalabean.Data;

import com.intek.kalabean.Model.User;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerDataSource implements KalaBeanDataSource {
    private ApiService apiService;
    public ServerDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.107/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Single<User> register(User user) {
        return apiService.register(user.getName(),user.getFamily(),user.getGender(),user.getEmail(),user.getMobile(),user.getPhone(),user.getPassword(),user.getState(),user.getCity(),user.getAddress());
    }
}
