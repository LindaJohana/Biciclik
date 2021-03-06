package com.colombiagames.biciclick.Home;

import com.colombiagames.biciclick.objects.StatisticsData;
import com.colombiagames.biciclick.objects.TravelTopData;
import com.colombiagames.biciclick.utils.Token;

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

    @Override
    public void TravelMonthPresenter() {
        model.TravelMonthModel(this);
    }

    @Override
    public void onSuccessTravelMonth(ArrayList<Integer> results) {
        view.setupBar(results);
    }

    @Override
    public void onErrorTravelMonth(String message) {
        view.setErrorTravelMonth(message);
    }

    @Override
    public void travelStatisticsPresenter() {
        model.travelStatisticsModel(this);
    }

    @Override
    public void onSuccessTravelStatistics(StatisticsData results) {
        view.travelStatisticsResults(results);
    }

    @Override
    public void onErrorTravelStatistics(String message) {
        view.serErrorTravelStatistics(message);
    }

    @Override
    public void login() {
        view.lanzarLogin();
    }

    @Override
    public void sendTokenPushPresenter(String tokenpush) {
        model.sendtokenpushModel(tokenpush, this);
    }

    @Override
    public void onErrorLogout() {

    }
}
