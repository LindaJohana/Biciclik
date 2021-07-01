package com.example.biciclik.Register;

import android.util.Log;

import com.example.biciclik.RegisterSuccess.RegisterSuccessActivity;
import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.objects.Register1Data;
import com.example.biciclik.objects.Register2Data;
import com.example.biciclik.objects.UserData;

import java.util.ArrayList;

public class RegisterPresenters implements RegisterInterfaces.presenters{
    RegisterInterfaces.activities1 view1;
    RegisterInterfaces.activities2 view2;
    RegisterModels model;

    public RegisterPresenters(RegisterInterfaces.activities1 view1, RegisterInterfaces.activities2 view2) {
        this.view1 = view1;
        this.view2= view2;
        this.model=new RegisterModels();
    }

    @Override
    public void register1Presenters(UserData userData, Register1Data register1Data) {
        Log.e("Presenter1", "Presenter");
        model.register1Model(userData, register1Data, this);
    }

    @Override
    public void register2Presenters(Register2Data register2Data) {
        Log.e("Presenter2", "Presenter2");
        model.register2Model(register2Data, this);
    }

    @Override
    public void devuelvisP() {
        view1.lanzarRegistroF(null);
    }

    @Override
    public void getCompanyPresenters() {
        model.getCompanyModel(this);
    }

    @Override
    public void setCompaniesPresenters(ArrayList<CompanyData> companies) {
        view1.setcompany(companies);
        String[] stringArray = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            CompanyData company = companies.get(i);
            stringArray[i] = company.getName();
        }
        view1.addItemsOnSpinner(stringArray);
    }

    @Override
    public void onErrorPresenter(String message) {
        view1.setError(message);
    }
}
