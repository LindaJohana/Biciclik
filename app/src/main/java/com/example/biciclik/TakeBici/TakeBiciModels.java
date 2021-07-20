package com.example.biciclik.TakeBici;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.biciclik.Api.HomeApiAdapter;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.BikeData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.objects.CreateTripData;
import com.example.biciclik.objects.PatchTrip;
import com.example.biciclik.objects.PointsResponse;
import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.utils.CustomErrorResponse;
import com.example.biciclik.utils.DDate;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TakeBiciModels implements TakeBiciInterfaces.models{
    private TakeBiciInterfaces.models model;
    private HomeApiAdapter homeApiAdapter;
    private LocalData localData;

    public TakeBiciModels() {
        this.model = model;
        this.homeApiAdapter = new HomeApiAdapter();
        this.localData = new LocalData();
    }

    @Override
    public void sendCodModel(TakeBiciInterfaces.presenters presenter, String cod) {
        Log.e("MODEL BIKE", "MODEL");
        Call<BikeData> call = homeApiAdapter.getApiService2().bikeUnlock(cod);
        call.enqueue(new Callback<BikeData>() {
            @Override
            public void onResponse(Call<BikeData> call, Response<BikeData> response) {
                if (response.isSuccessful()){
                    Log.e("SUCCESSFUL CODIGO", response.body().getMac_lock());
                    localData.register(response.body().getId(), "IDBIKE");
                    localData.register(response.body().getActual_point().getId(), "POINTBIKE");
                    presenter.onSuccesCod(response.body());
                }else {
                    Log.e("MODEL BIKE ERROR", "MODEL ERROR");
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorCod(response_user);
                }
            }

            @Override
            public void onFailure(Call<BikeData> call, Throwable t) {
                Log.e("MODEL BIKE FAIRULE", "MODEL FAIRULE");
            }
        });
    }

    @Override
    public void createTripModel(TakeBiciInterfaces.presenters presenter){
        DDate date=new DDate();
        Log.e("MODEL DE CREAR TRIP", "MODELLL");
        CreateTripData data=new CreateTripData(localData.getRegister("ID"), localData.getRegister("IDBIKE"),
                localData.getRegister("POINTBIKE"), date.getDate(),"INITIATED");
        Call<TripResponse> call = homeApiAdapter.getApiService2().createTrip(data.getUser(), data.getBike(),
                data.getStart_point(), data.getStart_date(), data.getStatus());
        call.enqueue(new Callback<TripResponse>() {
            @Override
            public void onResponse(Call<TripResponse> call, Response<TripResponse> response) {
                if (response.isSuccessful()){
                    Log.e("ISSUCCESSFUL", "CREATE TRIP");
                    TripResponse objects_list = null;
                    objects_list=response.body();
                    localData.register(objects_list.getId(), "ID_TRIP");
                    presenter.onSuccessTrip(objects_list);
                }else {
                    Log.e("MODEL BIKE CREATE ERROR", "MODEL ERROR");
                    if (response.raw().code()==401){
                        localData.LogOutApp();
                        presenter.codelogin();
                    }
                }
            }

            @Override
            public void onFailure(Call<TripResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getDeliveryPointModel(TakeBiciInterfaces.presenters presenter) {
        Call<PointsResponse> call=homeApiAdapter.getApiService2().getPoint();
        call.enqueue(new Callback<PointsResponse>() {
            @Override
            public void onResponse(Call<PointsResponse> call, Response<PointsResponse> response) {
                if (response.isSuccessful()){
                    PointsResponse objects_list = null;
                    objects_list=response.body();
                    presenter.setDeliveryPoint(objects_list.getResults());
                }else {
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    presenter.onErrorCod(response_user);
                }
            }

            @Override
            public void onFailure(Call<PointsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void setTripModel(TakeBiciInterfaces.presenters presenter, PatchTrip data) {
        DDate date=new DDate();
        final MultipartBody.Builder request = new MultipartBody.Builder().setType(MultipartBody.FORM);
        request.addFormDataPart("time_elapsed", null, RequestBody.create(MediaType.parse("text/plain"),data.getTime_elapsed()));
        request.addFormDataPart("destination", null, RequestBody.create(MediaType.parse("text/plain"),data.getDestination()));
        request.addFormDataPart("delivery_date", null, RequestBody.create(MediaType.parse("text/plain"),date.getDate()));
        request.addFormDataPart("delivery_point", null, RequestBody.create(MediaType.parse("text/plain"),data.getDelivery_point()));
        MultipartBody body=request.build();
        Call<TripResponse> call = homeApiAdapter.getApiService2().updateTrip(localData.getRegister("ID_TRIP"),body);
        call.enqueue(new Callback<TripResponse>() {
            @Override
            public void onResponse(Call<TripResponse> call, Response<TripResponse> response) {
                if (response.isSuccessful()){
                    TripResponse objects_list = null;
                    objects_list=response.body();
                }else {
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorSetTrip(response_user);
                }
            }

            @Override
            public void onFailure(Call<TripResponse> call, Throwable t) {
                Log.e("ONFAIRULE", t.toString());
            }
        });
    }

}
