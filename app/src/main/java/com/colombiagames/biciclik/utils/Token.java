package com.colombiagames.biciclik.utils;

import android.util.Log;

import com.colombiagames.biciclik.Api.LoginAdapter;
import com.colombiagames.biciclik.local_data.LocalData;
import com.colombiagames.biciclik.objects.TokenResponse;

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
                    Log.e("TOKENNNNN", "REFRESHtOKEN");
                    localData.SaveToken(localData.getRefresh(),response.body().getAccess());
                }else {
                    localData.LogOutApp();
                }
            }
            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("FAIRULE TOKEN", t.toString());
            }
        });
    }
}
