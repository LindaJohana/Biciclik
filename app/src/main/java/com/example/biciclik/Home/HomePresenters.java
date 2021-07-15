package com.example.biciclik.Home;

import com.example.biciclik.objects.ResultsResponse;
import com.example.biciclik.objects.TravelTopData;
import com.example.biciclik.utils.Token;

import java.util.ArrayList;

public class HomePresenters implements HomeInterfaces.presenters {
    private HomeInterfaces.activities view;
    private HomeModels model;
    Token token;

    public HomePresenters(HomeInterfaces.activities view) {
        this.view=view;
        this.model=new HomeModels();
        this.token=new Token();
    }

    @Override
    public void TopCompanyPresenter() {
        model.TopCompanyModel(this);
    }

    @Override
    public void onSuccessTopCompany(ArrayList<TravelTopData> results) {
        view.TopCompanyPersons(results);
    }

    @Override
    public void onErrorTravelTop(String message) {
        view.setErrorTravelTop(message);
    }
}
