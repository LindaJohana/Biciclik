package com.colombiagames.biciclick.Trip;

import android.util.Log;

import com.colombiagames.biciclick.Api.HomeApiAdapter;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.TripResponse;
import com.colombiagames.biciclick.objects.TripResponseFinal;
import com.colombiagames.biciclick.utils.CustomErrorResponse;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripModels implements TripInterfaces.models{
    private TripInterfaces.models model;
    private HomeApiAdapter homeApiAdapter;
    private LocalData localData;

    public TripModels() {
        this.model = model;
        this.homeApiAdapter=new HomeApiAdapter();
        this.localData=new LocalData();
    }

    @Override
    public void getInfoTripModel(TripInterfaces.presenters presenter) {
        Log.e("MODEL", "TRIP");
        Call<TripResponseFinal> call=homeApiAdapter.getApiService2().finalTrip(localData.getRegister("ID_TRIP"));
        call.enqueue(new Callback<TripResponseFinal>() {
            @Override
            public void onResponse(Call<TripResponseFinal> call, Response<TripResponseFinal> response) {
                if (response.isSuccessful()){
                    TripResponseFinal objects_list = null;
                    objects_list=response.body();
                    presenter.setInfoTripPresenter(objects_list);
                    localData.registerrRetry(0);
                }else {
                    if (localData.getRegisterRetry()==0 || localData.getRegisterRetry()==1){
                        try {
                            Thread.sleep(500);
                            if (localData.getRegisterRetry()==0){
                                localData.registerrRetry(1);
                                getInfoTripModel(presenter);
                            }else {
                                localData.registerrRetry(2);
                                getInfoTripModel(presenter);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        localData.registerrRetry(0);
                        localData.LogOutApp();
                        Log.e("tripmodels", "getinfologout");
                        localData.register("", "ID_REGISTER_PUSH");
                        presenter.login();
                    }

                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("MODEL ERROR TRIP", response_user);
                }
            }

            @Override
            public void onFailure(Call<TripResponseFinal> call, Throwable t) {
                Log.e("ONFAIRULE", t.toString());
            }
        });
    }

    @Override
    public void sendStatusModel(TripInterfaces.presenters presenter, String  UrlPhoto) {
        final MultipartBody.Builder request = new MultipartBody.Builder().setType(MultipartBody.FORM);
        request.addFormDataPart("status", null, RequestBody.create(MediaType.parse("text/plain"),"FINALIZED"));
        if (!UrlPhoto.equals("")){
            File filePhoto = new File(UrlPhoto);
            request.addFormDataPart("photo",filePhoto.getName(),RequestBody.create(MediaType.parse("image/*"), filePhoto));
        }
        MultipartBody body=request.build();
        Call<TripResponse> call = homeApiAdapter.getApiService2().updateTrip(localData.getRegister("ID_TRIP"),body);
        call.enqueue(new Callback<TripResponse>() {
            @Override
            public void onResponse(Call<TripResponse> call, Response<TripResponse> response) {
                if (response.isSuccessful()){
                    TripResponse objects_list = null;
                    objects_list=response.body();
                    localData.registerrRetry(0);
                    presenter.home();
                }else {
                    if (localData.getRegisterRetry()==0){
                        try {
                            Thread.sleep(500);
                            localData.registerrRetry(1);
                            sendStatusModel(presenter, UrlPhoto);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        localData.registerrRetry(0);
                        localData.LogOutApp();
                        Log.e("tripmodels", "statuslogout");
                        localData.register("", "ID_REGISTER_PUSH");
                        presenter.login();
                    }

                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    presenter.onErrorSetTrip(response_user);
                }
            }

            @Override
            public void onFailure(Call<TripResponse> call, Throwable t) {

            }
        });
    }
}
