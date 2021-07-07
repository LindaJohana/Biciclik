package com.example.biciclik.Api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RegisterAdapter2 {
    @Multipart
    @POST("api/user/")
    @Headers("content-type: multipart/form-data;")
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
}
