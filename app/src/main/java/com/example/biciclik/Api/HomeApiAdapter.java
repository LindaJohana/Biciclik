package com.example.biciclik.Api;

import android.os.Build;
import android.service.voice.AlwaysOnHotwordDetector;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.Home.HomePresenters;
import com.example.biciclik.Login.LoginModels;
import com.example.biciclik.R;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.Payload;
import com.example.biciclik.objects.TokenResponse;
import com.example.biciclik.utils.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Base64;
import java.util.TimeZone;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeApiAdapter {
    private static ApiService API_SERVICE;
    public static ApiService getApiService(){
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrl = BaseContext.getContext().getString(R.string.server) ;

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- usamos el log level
                    .build();
            API_SERVICE = retrofit.create(ApiService.class);
        }

        return API_SERVICE;
    }
    public static ApiService getApiService2(){
        LocalData localData=new LocalData();
        Gson gson1=new Gson();
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder
                .addInterceptor(new Interceptor() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder newRequest = request.newBuilder();
                        String[] chunks = localData.getAccess().split("\\.");
                        Base64.Decoder decoder = Base64.getDecoder();
                        String payloadS = new String(decoder.decode(chunks[1]));
                        Payload payload = gson1.fromJson(payloadS, Payload.class);
                        if (Long.parseLong(payload.getExp()+"000")< System.currentTimeMillis()){
                            Token token=new Token();
                            token.refreshToken();
                        }
                        //token.refreshToken();
                        return chain.proceed(newRequest.addHeader("Authorization", "Bearer "+localData.getAccess()).build());
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
