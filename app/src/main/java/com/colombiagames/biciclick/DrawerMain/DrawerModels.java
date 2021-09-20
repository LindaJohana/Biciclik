package com.colombiagames.biciclick.DrawerMain;

import android.util.Log;

import com.colombiagames.biciclick.Api.HomeApiAdapter;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.ProfileData;
import com.colombiagames.biciclick.objects.TravelTopData;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerModels implements DrawerInterfaces.models {
    private DrawerInterfaces.models model;
    private LocalData localData;
    private HomeApiAdapter homeApiAdapter;

    public DrawerModels() {
        this.model = model;
        this.localData = new LocalData();
        this.homeApiAdapter = new HomeApiAdapter();
    }

    @Override
    public void logOutModels(DrawerInterfaces.presenters presenter) {
        localData.LogOutApp();
        localData.register("", "ID_REGISTER_PUSH");
    }

    @Override
    public void profileHeaderModel(DrawerInterfaces.presenters presenter) {
        Call<ProfileData> call = homeApiAdapter.getApiService2().profile();
        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful()){
                    localData.register(response.body().getId(),"ID");
                    presenter.onSuccessProfileHeader(response.body());
                }else {
                    if (response.raw().code()==401){
                        localData.LogOutApp();
                        presenter.codelogin();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
            }
        });
    }

    @Override
    public void verifiedModel(DrawerInterfaces.presenters presenter) {
        Call<ProfileData> call = homeApiAdapter.getApiService2().profile();
        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful()){
                    ProfileData objects_list = null;
                    objects_list=response.body();
                    presenter.verifiedSuccess(objects_list.getVerified(), objects_list.getActive());
                }else {
                    if (response.raw().code()==401){
                        localData.LogOutApp();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
            }
        });
    }

    @Override
    public void logoutPushModel(DrawerInterfaces.presenters presenter) {
        Call<ResponseBody> call = homeApiAdapter.getApiService2().logoutPush(localData.getRegister("TOKENPUSH"), false);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    presenter.onSuccessLogoutPush();
                }else {
                    if (response.raw().code()==401){
                        if (localData.getRegisterRetry()==0 || localData.getRegisterRetry()==1){
                            try {
                                Thread.sleep(1000);
                                if (localData.getRegisterRetry()==0){
                                    localData.registerrRetry(1);
                                    logoutPushModel(presenter);
                                }else {
                                    localData.registerrRetry(2);
                                    logoutPushModel(presenter);
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            localData.registerrRetry(0);
                            localData.LogOutApp();
                            presenter.codelogin();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
