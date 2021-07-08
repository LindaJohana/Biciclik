package com.example.biciclik.Api;


import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.objects.LoginResponse;
import com.example.biciclik.objects.MessageResponse;
import com.example.biciclik.objects.TokenResponse;
import com.example.biciclik.objects.UserData;
import com.example.biciclik.objects.UserResponse;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

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
    @FormUrlEncoded
    @POST("api/recover_password/")
    Call<MessageResponse> sendEmail(
            @Field("username") String username
    );

    @Multipart
    @Headers({ "Content-Type: multipart/form-data;charset=UTF-8"})
    @POST("api/user/")
    Call<UserResponse> sendInfo(
            @PartMap HashMap<String, Object> user,
            @Part("phone_number") RequestBody phone_number,
            @Part("company") RequestBody company,
            @Part("address") RequestBody address,
            @Part MultipartBody.Part selfie,
            @Part MultipartBody.Part document_front_photo,
            @Part MultipartBody.Part document_back_photo
    );
}
