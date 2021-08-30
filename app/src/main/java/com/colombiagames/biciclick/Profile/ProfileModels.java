package com.colombiagames.biciclick.Profile;

import android.util.Log;

import com.colombiagames.biciclick.Api.HomeApiAdapter;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.ProfileData;
import com.colombiagames.biciclick.objects.UserData;
import com.colombiagames.biciclick.utils.CustomErrorResponse;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileModels implements ProfileInterfaces.models{
    private ProfileInterfaces.models model;
    private LocalData localData;
    private HomeApiAdapter homeApiAdapter;

    public ProfileModels() {
        this.model = model;
        this.localData = new LocalData();
        this.homeApiAdapter=new HomeApiAdapter();
    }

    @Override
    public void getProfileModel(ProfileInterfaces.presenters presenter) {
        Call<ProfileData> call = homeApiAdapter.getApiService2().profile();
        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful()){
                    localData.register(response.body().getId(), "ID");
                    presenter.onSuccessProfile(response.body());
                    localData.registerrRetry(0);
                }else {
                    Log.e("GetProfile","ERROR");
                    if (response.raw().code()==401){
                        if (localData.getRegisterRetry()==0){
                            Log.e("primer if","RETRY=0");
                            try {
                                Thread.sleep(500);
                                localData.registerrRetry(1);
                                getProfileModel(presenter);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Log.e("else","RETRY=1");
                            localData.registerrRetry(0);
                            localData.LogOutApp();
                            presenter.codelogin();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                Log.e("GetProfile","FAILURE");
            }
        });
    }

    @Override
    public void updateModel(ProfileInterfaces.presenters presenter, UserData changedUser, ProfileData changedData) {


        final MultipartBody.Builder request = new MultipartBody.Builder().setType(MultipartBody.FORM);
        request.addFormDataPart("user.first_name", null, RequestBody.create(MediaType.parse("text/plain"),changedUser.getFirst_name()));
        request.addFormDataPart("user.last_name", null, RequestBody.create(MediaType.parse("text/plain"),changedUser.getLast_name()));
        request.addFormDataPart("user.email", null, RequestBody.create(MediaType.parse("text/plain"),changedUser.getEmail()));
//        request.addFormDataPart("user.username", null, RequestBody.create(MediaType.parse("text/plain"),changedUser.getEmail()));
        request.addFormDataPart("phone_number", null, RequestBody.create(MediaType.parse("text/plain"),changedData.getPhone_number()));
        request.addFormDataPart("address", null, RequestBody.create(MediaType.parse("text/plain"),changedData.getAddress()));
        if (!changedData.getSelfie().equals("")){
            File fileSelfie = new File(changedData.getSelfie());
            request.addFormDataPart("selfie",fileSelfie.getName(),RequestBody.create(MediaType.parse("image/*"), fileSelfie));
        }
        MultipartBody body=request.build();
        Call<ProfileData> call = homeApiAdapter.getApiService2().update(localData.getRegister("ID"), body);
        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful()){
                    presenter.onSuccessUpdate(response.body());
                    localData.registerrRetry(0);
                }else {
                    Log.e("errorsito", response.errorBody().toString());

                    if (response.raw().code()==401){
                        if (localData.getRegisterRetry()==0){
                            Log.e("primer if","RETRY=0");
                            try {
                                Thread.sleep(500);
                                localData.registerrRetry(1);
                                updateModel(presenter, changedUser, changedData);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Log.e("else","RETRY=1");
                            localData.registerrRetry(0);
                            localData.LogOutApp();
                            presenter.codelogin();
                        }
                    }
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorMessage(response_user);
                }
            }
            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                Log.e("FAILURE", t.toString());
            }
        });
    }
}
