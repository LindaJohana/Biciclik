package com.example.biciclik.Api;


import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.objects.LoginResponse;
import com.example.biciclik.objects.MessageResponse;
import com.example.biciclik.objects.PointData;
import com.example.biciclik.objects.PointsResponse;
import com.example.biciclik.objects.ProfileData;
import com.example.biciclik.objects.ProfileResponse;
import com.example.biciclik.objects.ResultsResponse;
import com.example.biciclik.objects.ResultsTopHome;
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
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
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


    @POST("api/user/")
//    @Headers("content-type: multipart/form-data;")
    Call<ResponseBody> sendInfo(
            @Body MultipartBody file
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

    @GET("api/travel/")
    Call<ResultsTopHome>topCompanyTrip(
            @Query("limit") String limit
    );

    @GET("api/point/")
    Call<PointsResponse>listMaps(
    );
    @GET("api/point/")
    Call<PointData>point(
            @Query("id") String id
    );
    @GET("api/current_user")
    Call<ProfileData>profile(
    );
    @PATCH("api/user/{id}/")
    Call<ProfileData>update(
            @Path("id") String id,
//            @Body ProfileData profileData
            @Body MultipartBody profile
    );

}
