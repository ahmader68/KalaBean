package com.intek.kalabean.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intek.kalabean.Classes.Network;
import com.intek.kalabean.Model.User;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerDataSource implements KalaBeanDataSource {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private ApiService apiService;
    public ServerDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(Network.getOkHttpClient())
                .baseUrl("http://kalabean.com/wservice/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Single<User> register(User user) {
        return apiService.register(user.getFirstName(),user.getLastName(),user.getPassword(),user.getProvince(),user.getCity(),user.getMobile(),user.getPassword());
    }

    @Override
    public Single<String> upload(MultipartBody.Part file, RequestBody name) {
        return apiService.uploadFile(file , name);
    }

    @Override
    public Single<User> login(User user) {
        return apiService.login(user.getMobile(),user.getPassword(),0);
    }
}
