package com.example.biciclik.Profile;

import android.widget.Toast;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.Home.HomeModels;
import com.example.biciclik.objects.ProfileData;
import com.example.biciclik.objects.UserData;
import com.example.biciclik.utils.Token;

import java.util.ArrayList;

public class ProfilePresenters implements ProfileInterfaces.presenters{
    private ProfileInterfaces.activities view;
    private ProfileModels model;
    BaseContext context;
    Token token;

    public ProfilePresenters(ProfileInterfaces.activities view) {
        this.view = view;
        this.model=new ProfileModels();
        this.token=new Token();
        this.context=new BaseContext();
    }

    @Override
    public void getProfilePresenter() {
        model.getProfileModel(this);
    }

    @Override
    public void onSuccessProfile(ProfileData data) {
        view.setProfile(data);
    }

    @Override
    public void updatePresenter(UserData changedUser, ProfileData changedData) {
        model.updateModel(this, changedUser, changedData);
    }

    @Override
    public void onSuccessUpdate(ProfileData data) {
        Toast.makeText(BaseContext.getContext(), "Se modifico con exito su información", Toast.LENGTH_SHORT).show();
        view.setProfile(data);
    }

    @Override
    public void onErrorMessage(String message) {
        view.setErrorMessage(message);
    }

    @Override
    public void codelogin() {
        view.lanzarLogin();
    }
}
