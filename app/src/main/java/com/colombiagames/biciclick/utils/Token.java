package com.colombiagames.biciclick.utils;

import android.util.Log;

import com.colombiagames.biciclick.Api.LoginAdapter;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Token {
    LocalData localData=new LocalData();
    LoginAdapter loginAdapter = new LoginAdapter();
    String flag="";
    public void refreshToken() {
        Call<TokenResponse> call=loginAdapter.getApiService().refreshToken(localData.getRefresh());
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()){
                    localData.SaveToken(localData.getRefresh(),response.body().getAccess());
                }else {
                    localData.LogOutApp();
                    Log.e("TOKEN", "REFRESHTOKEN");
                    localData.register("", "ID_REGISTER_PUSH");
                }
            }
            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

            }
        });
    }
}
