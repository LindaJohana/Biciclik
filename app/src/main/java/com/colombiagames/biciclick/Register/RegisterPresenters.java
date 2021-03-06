package com.colombiagames.biciclick.Register;

import android.widget.Toast;

import com.colombiagames.biciclick.BaseContext.BaseContext;
import com.colombiagames.biciclick.objects.CompanyData;
import com.colombiagames.biciclick.objects.Register1Data;
import com.colombiagames.biciclick.objects.Register2Data;
import com.colombiagames.biciclick.objects.UserData;
import com.colombiagames.biciclick.utils.KeyPairBoolDataCustom;

import java.util.ArrayList;
import java.util.List;

public class RegisterPresenters implements RegisterInterfaces.presenters{
    RegisterInterfaces.activities1 view1;
    RegisterInterfaces.activities2 view2;
    RegisterInterfaces.activities3 view3;
    RegisterInterfaces.activities4 view4;
    RegisterModels model;

    public RegisterPresenters(RegisterInterfaces.activities1 view1,
                              RegisterInterfaces.activities2 view2, RegisterInterfaces.activities3 view3,
                              RenewPhotos view4) {
        this.view1 = view1;
        this.view2= view2;
        this.view3=view3;
        this.view4=view4;
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

    @Override
    public void renewPhotosPresenter(Register2Data photos) {
        model.renewPhotosModel(this, photos);
    }

    @Override
    public void onErrorPhoto(String message) {
        Toast.makeText(BaseContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessPhoto() {
        Toast.makeText(BaseContext.getContext(), "Tu informaci??n se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
        view4.lanzarlogin();
    }


}
