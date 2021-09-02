package com.colombiagames.biciclick.Api;


import com.colombiagames.biciclick.objects.BikeData;
import com.colombiagames.biciclick.objects.CompanyResponse;
import com.colombiagames.biciclick.objects.MessageResponse;
import com.colombiagames.biciclick.objects.PointData;
import com.colombiagames.biciclick.objects.PointsResponse;
import com.colombiagames.biciclick.objects.ProfileData;
import com.colombiagames.biciclick.objects.StatisticsData;
import com.colombiagames.biciclick.objects.TokenResponse;
import com.colombiagames.biciclick.objects.TravelTopData;
import com.colombiagames.biciclick.objects.TripResponse;
import com.colombiagames.biciclick.objects.TripResponseFinal;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("api/travel/top/")
    Call<ArrayList<TravelTopData>>travelTop(
    );

    @GET("api/travel/month/")
    Call<ArrayList<Integer>>travelMonth(
    );

    @GET("api/travel/statistics/")
    Call<StatisticsData>travelStatistics(
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
            @Body MultipartBody profile
    );

    @GET("api/bike/unlock/")
    Call<BikeData> bikeUnlock(
            @Query("mac") String mac
    );
    @FormUrlEncoded
    @POST("api/travel/")
    Call<TripResponse>createTrip(
            @Field("user") String user,
            @Field("bike") String bike,
            @Field("start_point") String start_point,
            @Field("start_date") String start_date,
            @Field("status") String status
    );
    @GET("api/point/")
    Call<PointsResponse>getPoint(
    );

    @PATCH("api/travel/{id}/")
    Call<TripResponse>updateTrip(
            @Path("id") String id,
            @Body MultipartBody trip
    );

    @GET("api/travel/{id}/")
    Call<TripResponseFinal>finalTrip(
            @Path("id") String id
    );
    @FormUrlEncoded
    @POST("api/travel/")
    Call<ResponseBody>tokenPush(
            @Field("token") String token
    );
}
