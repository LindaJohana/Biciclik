package com.example.biciclik.Home;

import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.ResultsResponse;
import com.example.biciclik.objects.ResultsTopHome;
import com.example.biciclik.objects.TravelTopData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface HomeInterfaces {
    interface activities{
        void TopCompanyPersons(ArrayList<TravelTopData> results);
        void setErrorTravelTop(String message);
    }
    interface presenters{
        void TopCompanyPresenter();
        void onSuccessTopCompany(ArrayList<TravelTopData> results);
        void onErrorTravelTop(String message);
    }
    interface models{
        void TopCompanyModel(presenters presenter);
    }
}
