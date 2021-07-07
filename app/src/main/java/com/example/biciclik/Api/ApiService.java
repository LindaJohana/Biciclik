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
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
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
    @POST("api/user/")
//    @Headers("content-type: multipart/form-data;")
    Call<ResponseBody> sendInfo(
            //@Part UserData user,
            @Part("user.username") RequestBody username,
            @Part("user.firts_name") RequestBody firtsname,
            @Part("user.last_name") RequestBody lastname,
            @Part("user.password") RequestBody password,
            @Part("user.email") RequestBody email,
            @Part("phone_number") RequestBody phone_number,
            @Part("company") RequestBody company,
            @Part("address") RequestBody address,
            @Part MultipartBody.Part selfie,
            @Part MultipartBody.Part document_front_photo,
            @Part MultipartBody.Part document_back_photo
    );

    @FormUrlEncoded
    @POST("api/user/verify-token/")
    Call<MessageResponse>tokenVerify(
            @Field("token") String token
    );
    @FormUrlEncoded
    @POST("api/recover_password/")
    Call<MessageResponse>recoverPassword(
            @Field("username") String username
    );
}
