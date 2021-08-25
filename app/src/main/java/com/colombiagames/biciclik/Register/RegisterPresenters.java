package com.colombiagames.biciclik.Register;

import com.colombiagames.biciclik.objects.CompanyData;
import com.colombiagames.biciclik.objects.Register1Data;
import com.colombiagames.biciclik.objects.Register2Data;
import com.colombiagames.biciclik.objects.UserData;
import com.colombiagames.biciclik.utils.KeyPairBoolDataCustom;

import java.util.ArrayList;
import java.util.List;

public class RegisterPresenters implements RegisterInterfaces.presenters{
    RegisterInterfaces.activities1 view1;
    RegisterInterfaces.activities2 view2;
    RegisterInterfaces.activities3 view3;
    RegisterModels model;

    public RegisterPresenters(RegisterInterfaces.activities1 view1,
                              RegisterInterfaces.activities2 view2, RegisterInterfaces.activities3 view3) {
        this.view1 = view1;
        this.view2= view2;
        this.view3=view3;
        this.model=new RegisterModels();
    }

    @Override
    public void register1Presenters(UserData userData, Register1Data register1Data) {
        model.register1Model(userData, register1Data, this);
    }

    @Override
    public void register2Presenters(Register2Data register2Data) {
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
            h.setExtra("lo que sea");
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

    @Override
    public void verifyPresenter(String token) {
        model.verifyModel(token, this);
    }

    @Override
    public void onErrorPresenterCod(String message) {
        view3.setError(message);
    }

    @Override
    public void onSuccessCod() {
        view3.lanzarExitoso(null);
    }
}
