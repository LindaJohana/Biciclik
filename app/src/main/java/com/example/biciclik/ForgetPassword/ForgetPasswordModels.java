package com.example.biciclik.ForgetPassword;

import com.example.biciclik.objects.EmailData;

public class ForgetPasswordModels implements ForgetPasswordInterfaces.models{
    private ForgetPasswordInterfaces.models model;
    private ForgetPasswordRequests request;

    public ForgetPasswordModels() {
        this.model = model;
        this.request = new ForgetPasswordRequests();
    }

    @Override
    public void sendEmailModel(EmailData email, ForgetPasswordInterfaces.presenters presenter) {
        request.sendEmailRequest(email, presenter);
    }
}
