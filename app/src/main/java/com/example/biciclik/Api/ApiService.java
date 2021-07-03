package com.example.biciclik.Api;


import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.objects.LoginResponse;
import com.example.biciclik.objects.TokenResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("api/token/")
    Call<TokenResponse> postLogin(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("api/token/verify/")
    Call<TokenResponse> verifyToken(
            @Field("token") String token
    );
    @FormUrlEncoded
    @POST("api/token/refresh/")
    Call<TokenResponse> refreshToken(
            @Field("refresh") String refresh
    );
    @GET("api/company/")
    Call<CompanyResponse> companies(
    );
}
