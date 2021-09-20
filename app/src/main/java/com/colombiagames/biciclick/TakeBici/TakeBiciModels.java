package com.colombiagames.biciclick.TakeBici;

import android.util.Log;

import com.colombiagames.biciclick.Api.HomeApiAdapter;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.BikeData;
import com.colombiagames.biciclick.objects.CreateTripData;
import com.colombiagames.biciclick.objects.PatchTrip;
import com.colombiagames.biciclick.objects.PointsResponse;
import com.colombiagames.biciclick.objects.TripResponse;
import com.colombiagames.biciclick.utils.CustomErrorResponse;
import com.colombiagames.biciclick.utils.DDate;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
                    localData.registerrRetry(0);
                }else {

                    if (localData.getRegisterRetry()==0 || localData.getRegisterRetry()==1){
                        try {
                            Thread.sleep(500);
                            if (localData.getRegisterRetry()==0){
                                localData.registerrRetry(1);
                                sendCodModel(presenter, cod);
                            }else {
                                localData.registerrRetry(2);
                                sendCodModel(presenter, cod);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        localData.registerrRetry(0);
                        Log.e("takebicimodels", "sendlogout");
                        localData.LogOutApp();
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
                    localData.registerrRetry(0);
                }else {
                    Log.e("MODEL BIKE CREATE ERROR", "MODEL ERROR");
                    if (localData.getRegisterRetry()==0){
                        Log.e("primer if","RETRY=0");
                        try {
                            Thread.sleep(500);
                            localData.registerrRetry(1);
                            createTripModel(presenter);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Log.e("else","RETRY=1");
                        localData.registerrRetry(0);
                        localData.LogOutApp();
                        Log.e("takebicimodels", "createlogout");
                        localData.register("", "ID_REGISTER_PUSH");
                        presenter.login();
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
                    localData.registerrRetry(0);
                }else {
                    if (localData.getRegisterRetry()==0){
                        Log.e("primer if","RETRY=0");
                        try {
                            Thread.sleep(500);
                            localData.registerrRetry(1);
                            getDeliveryPointModel(presenter);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Log.e("else","RETRY=1");
                        localData.registerrRetry(0);
                        localData.LogOutApp();
                        Log.e("takebicimodels", "deliverylogout");
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
                    localData.registerrRetry(0);
                }else {

                    if (localData.getRegisterRetry()==0){
                        Log.e("primer if","RETRY=0");
                        try {
                            Thread.sleep(500);
                            localData.registerrRetry(1);
                            setTripModel(presenter, data);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        Log.e("else","RETRY=1");
                        localData.registerrRetry(0);
                        Log.e("takebicimodels", "settriplogout");
                        localData.LogOutApp();
                        presenter.login();
                    }
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
