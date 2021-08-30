package com.colombiagames.biciclick.Api;

import com.colombiagames.biciclick.BaseContext.BaseContext;
import com.colombiagames.biciclick.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAdapter {
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
}
