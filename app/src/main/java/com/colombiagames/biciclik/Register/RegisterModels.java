package com.colombiagames.biciclik.Register;

import android.util.Log;

import com.colombiagames.biciclik.Api.RegisterAdapter;
import com.colombiagames.biciclik.local_data.LocalData;
import com.colombiagames.biciclik.objects.CompanyResponse;
import com.colombiagames.biciclik.objects.MessageResponse;
import com.colombiagames.biciclik.objects.Register1Data;
import com.colombiagames.biciclik.objects.Register2Data;
import com.colombiagames.biciclik.objects.UserData;
import com.colombiagames.biciclik.utils.CustomErrorResponse;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModels implements RegisterInterfaces.models{
    private RegisterInterfaces.models model;
    LocalData localData;
    RegisterAdapter registerAdapter;

    public RegisterModels() {
        this.model = model;
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

        localData.register(register2Data.getSelfie(),"SELFIE");
        localData.register(register2Data.getDocumentFrontPhoto(), "DOCUMENT_FRONT_PHOTO");
        localData.register(register2Data.getDocumentBackPhoto(), "DOCUMENT_BACK_PHOTO");

        File fileSelfie = new File(localData.getRegister("SELFIE"));
        File fileFront = new File(localData.getRegister("DOCUMENT_FRONT_PHOTO"));
        File fileBack = new File(localData.getRegister("DOCUMENT_BACK_PHOTO"));

        final MultipartBody.Builder request = new MultipartBody.Builder().setType(MultipartBody.FORM);
        request.addFormDataPart("user.username", null, RequestBody.create(MediaType.parse("text/plain"),localData.getRegister("USER")));
        request.addFormDataPart("user.first_name", null, RequestBody.create(MediaType.parse("text/plain"),localData.getRegister("FIRTS_NAME")));
        request.addFormDataPart("user.last_name", null, RequestBody.create(MediaType.parse("text/plain"),localData.getRegister("LAST_NAME")));
        request.addFormDataPart("user.password", null, RequestBody.create(MediaType.parse("text/plain"),localData.getRegister("PASSWORD")));
        request.addFormDataPart("user.email", null, RequestBody.create(MediaType.parse("text/plain"),localData.getRegister("EMAIL")));
        request.addFormDataPart("phone_number", null, RequestBody.create(MediaType.parse("text/plain"),localData.getRegister("PHONE")));
        request.addFormDataPart("address", null, RequestBody.create(MediaType.parse("text/plain"),localData.getRegister("ADDRESS")));
        request.addFormDataPart("company", null, RequestBody.create(MediaType.parse("text/plain"),localData.getRegister("COMPANY")));
        request.addFormDataPart("selfie",fileSelfie.getName(),RequestBody.create(MediaType.parse("image/*"), fileSelfie));
        request.addFormDataPart("document_front_photo",fileFront.getName(),RequestBody.create(MediaType.parse("image/*"), fileFront));
        request.addFormDataPart("document_back_photo",fileBack.getName(),RequestBody.create(MediaType.parse("image/*"), fileBack));
//        // Single Image
//        builder.addFormDataPart("files",file1.getName(),RequestBody.create(MediaType.parse("image/*"), file1));
//        // Multiple Images
//        for (int i = 0; i <filePaths.size() ; i++) {
//            File file = new File(filePaths.get(i));
//            RequestBody requestImage = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            builder.addFormDataPart("event_images[]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
//        }

        MultipartBody body=request.build();

        Call<ResponseBody> call = RegisterAdapter.getApiService().sendInfo(body);
        try {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("tag", "onResponse: " + response.message().toString());
                    if (response.isSuccessful()){
                        ResponseBody objects_list = null;
                        objects_list=response.body();
                        localData.CreateUser();
                        presenter.onSuccessRegister();
                    }else {
                        CustomErrorResponse custom_error = new CustomErrorResponse();
                        String response_user = "Intentalo nuevamente";
                        try {
                            response_user = custom_error.returnMessageError(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        presenter.onErrorPresenterRegister(response_user);
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("tag", "onResponse: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("tag", "onCreate: " + e.getMessage());
        }
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
            }
        });
    }

}
