package com.example.biciclik.Register;

import android.util.Log;

import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.Register1Data;
import com.example.biciclik.objects.Register2Data;
import com.example.biciclik.objects.UserData;

public class RegisterModels implements RegisterInterfaces.models{
    private RegisterInterfaces.models model;
    private RegisterRequests request;
    LocalData localData;

    public RegisterModels() {
        this.model = model;
        this.request = new RegisterRequests();
        this.localData=new LocalData();
    }

    @Override
    public void register1Model(UserData userData, Register1Data register1Data, RegisterInterfaces.presenters presenter) {
        localData.register(userData.getUsername(), "USER");
        localData.register(userData.getEmail(), "EMAIL");
        localData.register(userData.getPassword(), "PASSWORD");
        localData.register(register1Data.getPhone().toString(), "PHONE");
        localData.register(register1Data.getCompany().toString(), "COMPANY");
        localData.register(register1Data.getAddress().toString(), "ADDRESS");
        Log.e("Model1", "Model1");
        presenter.toRegister2();

    }

    @Override
    public void register2Model(Register2Data register2Data, RegisterInterfaces.presenters presenter) {
        localData.register(register2Data.getSelfie().toString(), "SELFIE");
        localData.register(register2Data.getDocumentFrontPhoto().toString(), "DOCUMENT_FRONT_PHOTO");
        localData.register(register2Data.getDocumentBackPhoto().toString(), "DOCUMENT_BACK_PHOTO");
        Log.e("Model2", "Model2");
        request.register1Request(presenter);
    }

    @Override
    public void getCompanyModel(RegisterInterfaces.presenters presenter) {
        request.getCompanyRequest(presenter);
    }
}
