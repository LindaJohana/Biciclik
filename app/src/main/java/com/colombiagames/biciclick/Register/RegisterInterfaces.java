package com.colombiagames.biciclick.Register;

import android.view.View;

import com.colombiagames.biciclick.objects.CompanyData;
import com.colombiagames.biciclick.objects.Register1Data;
import com.colombiagames.biciclick.objects.Register2Data;
import com.colombiagames.biciclick.objects.UserData;
import com.colombiagames.biciclick.utils.KeyPairBoolDataCustom;

import java.util.ArrayList;
import java.util.List;

public interface RegisterInterfaces {
    interface activities1{
        void addItemsOnSpinner(List<KeyPairBoolDataCustom> names);

        void register1();
        void lanzarRegistroF(View view);
        void setError(String message);
        void setcompany(ArrayList<CompanyData> company);
    }

    interface activities2{
        void register2();
        void setError(String message);
        void lanzarRegistro3(View view);
    }
    interface activities3{
        void lanzarExitoso(View view);
        void verify();
        void setError(String message);
    }
    interface activities4{
        void lanzarlogin();
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
        void verifyPresenter(String token);
        void onErrorPresenterCod(String message);
        void onSuccessCod();
        void renewPhotosPresenter(Register2Data photos);
        void onErrorPhoto(String message);
        void onSuccessPhoto();
    }
    interface models{
        void register1Model(UserData userData, Register1Data register1Data, presenters presenter);
        void register2Model(Register2Data register2Data, presenters presenter);
        void getCompanyModel(presenters presenter);
        void verifyModel(String token, presenters presenters);
        void renewPhotosModel(presenters presenter, Register2Data photos);
    }
}
