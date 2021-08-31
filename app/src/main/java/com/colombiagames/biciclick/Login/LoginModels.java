package com.colombiagames.biciclick.Login;

import android.util.Log;

import com.colombiagames.biciclick.Api.LoginAdapter;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.LoginResponse;
import com.colombiagames.biciclick.objects.TokenResponse;
import com.colombiagames.biciclick.utils.CustomErrorResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModels implements LoginInterfaces.models {
    private LoginInterfaces.models model;
    private LoginAdapter loginAdapter;
    private LocalData localData;

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
                        Log.e("VERIFYTOKEN", localData.getAccess());
                    }else {
                        refreshToken(presenter, localData.getRefresh());
                        Log.e("VERIFYTOKEN ELSE", localData.getAccess());
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
                    Log.e("LOGINNNNNNN", "REFRESHtOKEN");
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
