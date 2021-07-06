package com.example.biciclik.ForgetPassword;

import com.example.biciclik.Api.ForgetPasswordAdapter;
import com.example.biciclik.objects.EmailData;
import com.example.biciclik.objects.MessageResponse;
import com.example.biciclik.objects.TokenResponse;
import com.example.biciclik.utils.CustomErrorResponse;

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
        Call<MessageResponse> call = forgetAdapter.getApiService().sendEmail(email.getUser());
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
        //request.sendEmailRequest(email, presenter);
    }
}
