package com.example.biciclik.Login;

import android.util.Log;

import com.example.biciclik.Api.LoginAdapter;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.LoginResponse;
import com.example.biciclik.objects.TokenResponse;
import com.example.biciclik.utils.CustomErrorResponse;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.POST;

public class LoginModels implements LoginInterfaces.models {
    private LoginInterfaces.models model;
    private LoginAdapter loginAdapter;
    private LocalData localData;
    LoginResponse loginResponse;

    public LoginModels() {
        this.model = model;
        this.loginAdapter = new LoginAdapter();
        this.localData= new LocalData();
    }

    @Override
    public void setLogin(LoginResponse login, LoginInterfaces.presenters presenter) {
        Call<TokenResponse> call = loginAdapter.getApiService().postLogin(login.getUser(),login.getPassword());
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("onResultPostLogin", response.body().toString());
                    localData.SaveToken(response.body().getRefresh(), response.body().getAccess());
                    presenter.onSuccessLogin();
                }else {
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorLogin(response_user);
                }
            }
            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void getTokenModel(LoginInterfaces.presenters presenter) {
        if(localData.getAccess().equals("") == false){
            Call<TokenResponse> call=loginAdapter.getApiService().verifyToken(localData.getAccess());
            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    if (response.isSuccessful()){
                        presenter.onSuccessLogin();
                    }else {
                        refreshToken(presenter, localData.getRefresh());
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void refreshToken(LoginInterfaces.presenters presenter, String refresh) {
        Call<TokenResponse> call=loginAdapter.getApiService().refreshToken(refresh);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()){
                    localData.SaveToken(refresh,response.body().getAccess());
                    presenter.onSuccessLogin();
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
