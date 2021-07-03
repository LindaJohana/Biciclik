package com.example.biciclik.Register;

import android.util.Log;

import com.example.biciclik.Api.RegisterAdapter;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.objects.Register1Data;
import com.example.biciclik.objects.Register2Data;
import com.example.biciclik.objects.TokenResponse;
import com.example.biciclik.objects.UserData;
import com.example.biciclik.utils.CustomErrorResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModels implements RegisterInterfaces.models{
    private RegisterInterfaces.models model;
    //private RegisterRequests request;
    LocalData localData;

    public RegisterModels() {
        this.model = model;
        //this.request = new RegisterRequests();
        this.localData=new LocalData();
    }

    @Override
    public void register1Model(UserData userData, Register1Data register1Data, RegisterInterfaces.presenters presenter) {
        localData.register(userData.getUsername(), "USER");
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
        //request.register1Request(presenter);

    }

    @Override
    public void getCompanyModel(RegisterInterfaces.presenters presenter) {
        Call<CompanyResponse> call= RegisterAdapter.getApiService().companies();
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

}
