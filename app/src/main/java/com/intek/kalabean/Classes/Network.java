package com.intek.kalabean.Classes;

import android.webkit.WebSettings;

import com.intek.kalabean.Data.ApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    public static OkHttpClient getOkHttpClient(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(6 , TimeUnit.MINUTES)
                .readTimeout(6 , TimeUnit.MINUTES)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .removeHeader("User-Agent")
                                .addHeader("User-Agent",WebSettings.getDefaultUserAgent(G.context))
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        return httpClient;
    }
}
