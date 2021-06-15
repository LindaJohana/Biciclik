package com.example.biciclik.Login;

import com.example.biciclik.objects.LoginData;

public class LoginPresenters implements LoginInterfaces.presenters {
    private LoginInterfaces.activities view;
    private LoginModels model;

    public LoginPresenters(LoginInterfaces.activities view) {
        this.view=view;
        this.model=new LoginModels();
    }

    @Override
    public void setLogin(LoginData login) {
        model.setLogin(login, this);
    }

    @Override
    public void onErrorLogin(String message) {
        view.setErrorLogin(message);
    }

    @Override
    public void onSuccessLogin() {
        view.lanzarPerfil();
    }

    @Override
    public void getToken() {
        model.getTokenModel(this);
    }
}