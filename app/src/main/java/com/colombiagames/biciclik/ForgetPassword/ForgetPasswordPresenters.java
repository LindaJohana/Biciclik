package com.colombiagames.biciclik.ForgetPassword;

import com.colombiagames.biciclik.objects.EmailData;

public class ForgetPasswordPresenters implements ForgetPasswordInterfaces.presenters{
    private ForgetPasswordInterfaces.activities view;
    private ForgetPasswordModels model;

    public ForgetPasswordPresenters(ForgetPasswordInterfaces.activities view) {
        this.view = view;
        this.model=new ForgetPasswordModels();
    }
    @Override
    public void SendEmailPresenter(EmailData email) {
        model.sendEmailModel(email, this);
    }

    @Override
    public void onSuccessEmail(String message) {
        view.lanzarLogin(message);
    }

    @Override
    public void onErrorEmail(String message) {
        view.setErrorEmail(message);
    }
}
