package com.example.biciclik.Api;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenApiAdapter {
    private static ApiService API_SERVICE;
    public static ApiService getApiService2(){

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder newRequest = request.newBuilder().header("Authorization", "mytoken");
                        return chain.proceed(newRequest.build());
                    }
                });
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseContext.getContext().getString(R.string.server))
                    .client(okHttpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            API_SERVICE=retrofit.create(ApiService.class);
        }
        return API_SERVICE;
    }
}
