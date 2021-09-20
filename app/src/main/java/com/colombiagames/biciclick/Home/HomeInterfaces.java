package com.colombiagames.biciclick.Home;

import com.colombiagames.biciclick.objects.StatisticsData;
import com.colombiagames.biciclick.objects.TravelTopData;

import java.util.ArrayList;

public interface HomeInterfaces {
    interface activities{
        void TopCompanyPersons(ArrayList<TravelTopData> results);
        void setErrorTravelTop(String message);
        void setupBar(ArrayList<Integer> results);
        void setErrorTravelMonth(String message);
        void serErrorTravelStatistics(String message);
        void travelStatisticsResults(StatisticsData result);
        void lanzarLogin();
        void lanzarloginsinT();
    }
    interface presenters{
        void TopCompanyPresenter();
        void onSuccessTopCompany(ArrayList<TravelTopData> results);
        void onErrorTravelTop(String message);
        void TravelMonthPresenter();
        void onSuccessTravelMonth(ArrayList<Integer> results);
        void onErrorTravelMonth(String message);
        void travelStatisticsPresenter();
        void onSuccessTravelStatistics(StatisticsData results);
        void onErrorTravelStatistics(String message);
        void login();
        void sendTokenPushPresenter(String tokenpush);
        void onErrorLogout();
    }
    interface models{
        void TopCompanyModel(presenters presenter);
        void TravelMonthModel(presenters presenter);
        void travelStatisticsModel(presenters presenter);
        void sendtokenpushModel(String tokenpush, presenters presenter);
    }
}
