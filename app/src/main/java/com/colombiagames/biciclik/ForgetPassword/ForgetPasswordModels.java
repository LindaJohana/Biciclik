package com.colombiagames.biciclik.ForgetPassword;

import com.colombiagames.biciclik.Api.ForgetPasswordAdapter;
import com.colombiagames.biciclik.objects.EmailData;
import com.colombiagames.biciclik.objects.MessageResponse;
import com.colombiagames.biciclik.utils.CustomErrorResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordModels implements ForgetPasswordInterfaces.models{
    private ForgetPasswordInterfaces.models model;
    private ForgetPasswordAdapter forgetAdapter;
    //private ForgetPasswordRequests request;

    public ForgetPasswordModels() {
        this.model = model;
        this.forgetAdapter=new ForgetPasswordAdapter();
    }

    @Override
    public void sendEmailModel(EmailData email, ForgetPasswordInterfaces.presenters presenter) {
        Call<MessageResponse> call = forgetAdapter.getApiService().recoverPassword(email.getUsername());
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()){
                    presenter.onSuccessEmail(response.body().getMessage());
                }else {
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorEmail(response_user);
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {

            }
        });
    }
}
