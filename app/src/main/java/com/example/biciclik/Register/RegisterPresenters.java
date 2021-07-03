package com.example.biciclik.Register;

import android.util.Log;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.example.biciclik.RegisterSuccess.RegisterSuccessActivity;
import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.objects.Register1Data;
import com.example.biciclik.objects.Register2Data;
import com.example.biciclik.objects.UserData;
import com.example.biciclik.utils.KeyPairBoolDataCustom;

import java.util.ArrayList;
import java.util.List;

public class RegisterPresenters implements RegisterInterfaces.presenters{
    RegisterInterfaces.activities1 view1;
    RegisterInterfaces.activities2 view2;
    RegisterInterfaces.activities3 view3;
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
    public void toRegister2() {
        view1.lanzarRegistroF(null);
    }

    @Override
    public void getCompanyPresenters() {
        model.getCompanyModel(this);
    }

    @Override
    public void setCompaniesPresenters(ArrayList<CompanyData> companies) {
        view1.setcompany(companies);
        List<KeyPairBoolDataCustom> listArray0 = new ArrayList<>();
        for (int i = 0; i < companies.size(); i++) {
            CompanyData company = companies.get(i);
            KeyPairBoolDataCustom h = new KeyPairBoolDataCustom();
            h.setId(company.getId());
            h.setName(company.getName());
            h.setSelected(false);
            listArray0.add(h);
        }
        view1.addItemsOnSpinner(listArray0);
    }

    @Override
    public void onErrorPresenterCompany(String message) {
        view1.setError(message);
    }

    @Override
    public void onErrorPresenterRegister(String message) {
        view2.setError(message);
    }


    @Override
    public void onSuccessRegister() {
        view2.lanzarRegistro3(null);
    }
}
