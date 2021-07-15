package com.example.biciclik.Home;

import com.example.biciclik.objects.StatisticsData;
import com.example.biciclik.objects.TravelTopData;

import java.util.ArrayList;

public interface HomeInterfaces {
    interface activities{
        void TopCompanyPersons(ArrayList<TravelTopData> results);
        void setErrorTravelTop(String message);
        void setupBar(ArrayList<Integer> results);
        void setErrorTravelMonth(String message);
        void serErrorTravelStatistics(String message);
        void travelStatisticsResults(StatisticsData result);
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
    }
    interface models{
        void TopCompanyModel(presenters presenter);
        void TravelMonthModel(presenters presenter);
        void travelStatisticsModel(presenters presenter);
    }
}
