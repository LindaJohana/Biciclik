package com.example.biciclik.Home;

import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.ResultsResponse;
import com.example.biciclik.objects.ResultsTopHome;

import java.io.File;
import java.util.ArrayList;

public interface HomeInterfaces {
    interface activities{
        void TopCompanyPersons(ArrayList<ResultsResponse> results);
    }
    interface presenters{
        void TopCompanyPresenter();
        void onSuccessTopCompany(ArrayList<ResultsResponse> results);
    }
    interface models{
        void TopCompanyModel(presenters presenter);
    }
}
