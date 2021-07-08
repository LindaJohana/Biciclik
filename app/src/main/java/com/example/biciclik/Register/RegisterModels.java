package com.example.biciclik.Register;

import android.net.Uri;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;

import com.example.biciclik.Api.RegisterAdapter;
import com.example.biciclik.Api.RegisterAdapter2;
import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.objects.MessageResponse;
import com.example.biciclik.objects.Register1Data;
import com.example.biciclik.objects.Register2Data;
import com.example.biciclik.objects.TokenResponse;
import com.example.biciclik.objects.UserData;
import com.example.biciclik.objects.UserResponse;
import com.example.biciclik.utils.CustomErrorResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PartMap;

public class RegisterModels implements RegisterInterfaces.models{
    private RegisterInterfaces.models model;
    //private RegisterRequests request;
    LocalData localData;
    RegisterAdapter registerAdapter;
    RegisterAdapter2 registerAdapter2;

    public RegisterModels() {
        this.model = model;
        //this.request = new RegisterRequests();
        this.localData=new LocalData();
        this.registerAdapter=new RegisterAdapter();

    }

    @Override
    public void register1Model(UserData userData, Register1Data register1Data, RegisterInterfaces.presenters presenter) {
        localData.register(userData.getUsername(), "USER");
        localData.register(userData.getFirst_name(), "FIRTS_NAME");
        localData.register(userData.getLast_name(), "LAST_NAME");
        localData.register(userData.getEmail(), "EMAIL");
        localData.register(userData.getPassword(), "PASSWORD");
        localData.register(register1Data.getPhone().toString(), "PHONE");
        localData.register(register1Data.getCompany().toString(), "COMPANY");
        localData.register(register1Data.getAddress().toString(), "ADDRESS");
        Log.e("Model1", "Model1");

        presenter.toRegister2();

    }

    @Override
    public void register2Model(Register2Data register2Data, RegisterInterfaces.presenters presenter) {
        localData.register(register2Data.getSelfie().toString(), "SELFIE");
        localData.register(register2Data.getDocumentFrontPhoto().toString(), "DOCUMENT_FRONT_PHOTO");
        localData.register(register2Data.getDocumentBackPhoto().toString(), "DOCUMENT_BACK_PHOTO");
        Log.e("Model2", "Model2");

        File fileSelfie = new File(localData.getRegister("SELFIE"));
        File fileFront = new File(localData.getRegister("DOCUMENT_FRONT_PHOTO"));
        File fileBack = new File(localData.getRegister("DOCUMENT_BACK_PHOTO"));
        RequestBody requestFileSelfie = RequestBody.create(MediaType.parse("multipart/form-data"), fileSelfie);
        RequestBody requestFileFront = RequestBody.create(MediaType.parse("multipart/form-data"), fileFront);
        RequestBody requestFileBack = RequestBody.create(MediaType.parse("multipart/form-data"), fileBack);
        MultipartBody.Part bodySelfie = MultipartBody.Part.createFormData("selfie", fileSelfie.getName(), requestFileSelfie);
        MultipartBody.Part bodyFront = MultipartBody.Part.createFormData("document_front_photo", fileFront.getName(), requestFileFront);
        MultipartBody.Part bodyBack = MultipartBody.Part.createFormData("document_back_photo", fileBack.getName(), requestFileBack);

        Log.e("NO ESTA LLEGANFO", localData.getRegister("USER"));
        RequestBody username = RequestBody.create(MediaType.parse("text/plain"), localData.getRegister("USER"));
        RequestBody firtsname = RequestBody.create(MediaType.parse("text/plain"), localData.getRegister("FIRTSNAME"));
        RequestBody lastname = RequestBody.create(MediaType.parse("text/plain"), localData.getRegister("LASTNAME"));
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), localData.getRegister("PASSWORD"));
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), localData.getRegister("EMAIL"));
        RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), localData.getRegister("PHONE"));
        RequestBody company = RequestBody.create(MediaType.parse("text/plain"), localData.getRegister("COMPANY"));
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), localData.getRegister("ADDRESS"));



        Call<ResponseBody> call = RegisterAdapter.getApiService2().sendInfo(username, firtsname, lastname, password, email,
                phone, company, address, bodySelfie, bodyFront, bodyBack);
        try {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("tag", "onResponse: " + response.message().toString());
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("tag", "onResponse: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("tag", "onCreate: " + e.getMessage());
        }
//        Call<ResponseBody> call = registerAdapter.getApiService().sendInfo(username, firtsname, lastname, password, email,
//                phone, company, address, bodySelfie, bodyFront, bodyBack);
//        call.enqueue(new Callback<ResponseBody>() {
//                         @Override
//                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                             if (response.isSuccessful()){
//                                 UserResponse objects_list = null;
////                                 objects_list=response.body();
//                                 presenter.onSuccessRegister();
//                             }else {
//                                 CustomErrorResponse custom_error = new CustomErrorResponse();
//                                 String response_user = "Intentalo nuevamente";
//                                 try {
//                                     response_user = custom_error.returnMessageError(response.errorBody().string());
//                                 } catch (IOException e) {
//                                     e.printStackTrace();
//                                 }
//                                 presenter.onErrorPresenterRegister(response_user);
//                             }
//                         }
//
//                         @Override
//                         public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                         }
//                     });
    }

    @Override
    public void getCompanyModel(RegisterInterfaces.presenters presenter) {
        Call<CompanyResponse> call= registerAdapter.getApiService().companies();
        call.enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                if (response.isSuccessful()){
                    CompanyResponse objects_list = null;
                    objects_list=response.body();
                    presenter.setCompaniesPresenters(objects_list.getCompanies());
                }else {
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorPresenterCompany(response_user);
                }
            }
            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void verifyModel(String token, RegisterInterfaces.presenters presenters) {
        Call<MessageResponse> call=registerAdapter.getApiService().tokenVerify(token);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()){
                    presenters.onSuccessCod();
                }else {
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenters.onErrorPresenterCod(response_user);
                }
            }
            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Log.e("MALOMALITOMALO", ":( ");
            }
        });
    }

}
