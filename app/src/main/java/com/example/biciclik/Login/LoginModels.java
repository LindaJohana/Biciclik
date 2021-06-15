package com.example.biciclik.Login;

import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.LoginData;

public class LoginModels implements LoginInterfaces.models {
    private LoginInterfaces.models model;
    private LoginRequests request;
    private LocalData localData;

    public LoginModels() {
        this.model = model;
        this.request = new LoginRequests();
        this.localData= new LocalData();
    }

    @Override
    public void setLogin(LoginData login, LoginInterfaces.presenters presenter) {
        request.setLogin(login,presenter);
    }

    @Override
    public void getTokenModel(LoginInterfaces.presenters presenter) {
        if(localData.getAccess().equals("") == false){
            request.validateToken(presenter, localData.getRefresh(),localData.getAccess());
        }
    }
}
