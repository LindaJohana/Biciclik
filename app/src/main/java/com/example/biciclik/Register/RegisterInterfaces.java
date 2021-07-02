package com.example.biciclik.Register;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.example.biciclik.R;
import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.Register1Data;
import com.example.biciclik.objects.Register2Data;
import com.example.biciclik.objects.UserData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface RegisterInterfaces {
    interface activities1{
        void register1();
        void lanzarRegistroF(View view);
        void setError(String message);
        void setcompany(ArrayList<CompanyData> company);
        void addItemsOnSpinner(String[] names);
    }

    interface activities2{
        void register2();
        void setError(String message);
        void lanzarRegistro3(View view);
    }
    interface activities3{
        void lanzarExitoso(View view);
    }
    interface presenters{
        void register1Presenters(UserData userData, Register1Data register1Data);
        void register2Presenters(Register2Data register2Data);
        void toRegister2();
        void getCompanyPresenters();
        void setCompaniesPresenters(ArrayList<CompanyData> companies);
        void onErrorPresenterCompany(String message);
        void onErrorPresenterRegister(String message);
        void onSuccessRegister();
    }
    interface models{
        void register1Model(UserData userData, Register1Data register1Data, presenters presenter);
        void register2Model(Register2Data register2Data, presenters presenter);
        void getCompanyModel(presenters presenter);
    }
    interface requests{
        void register1Request(presenters presenter);
        void getCompanyRequest(presenters presenter);
    }
}
