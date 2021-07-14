package com.example.biciclik.Home;

import com.example.biciclik.Login.LoginInterfaces;
import com.example.biciclik.Login.LoginModels;
import com.example.biciclik.objects.ResultsResponse;
import com.example.biciclik.utils.Token;

import java.io.File;
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
    public void onSuccessTopCompany(ArrayList<ResultsResponse> results) {
        view.TopCompanyPersons(results);
    }
}
