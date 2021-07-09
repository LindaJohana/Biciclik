package com.example.biciclik.utils;

import com.example.biciclik.Api.TokenApiAdapter;
import com.example.biciclik.Home.HomePresenters;
import com.example.biciclik.Login.LoginInterfaces;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Token {
    LocalData localData=new LocalData();
    TokenApiAdapter tokenApiAdapter=new TokenApiAdapter();
    String flag="";
    public void refreshToken() {
        Call<TokenResponse> call=tokenApiAdapter.getApiService2().refreshToken(localData.getRefresh());
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()){
                    localData.SaveToken(localData.getRefresh(),response.body().getAccess());
                }else {
                    localData.LogOutApp();
                }
            }
            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
            }
        });
    }
}
