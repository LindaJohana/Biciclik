package com.colombiagames.biciclick.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatisticsData {
    @SerializedName("economic_savings")
    @Expose
    private String economic_savings;

    @SerializedName("carbon_footprint")
    @Expose
    private String carbon_footprint;

    @SerializedName("trips")
    @Expose
    private String trips;

    public StatisticsData(String economic_savings, String carbon_footprint, String trips) {
        this.economic_savings = economic_savings;
        this.carbon_footprint = carbon_footprint;
        this.trips = trips;
    }

    public String getEconomic_savings() {
        return economic_savings;
    }

    public void setEconomic_savings(String economic_savings) {
        this.economic_savings = economic_savings;
    }

    public String getCarbon_footprint() {
        return carbon_footprint;
    }

    public void setCarbon_footprint(String carbon_footprint) {
        this.carbon_footprint = carbon_footprint;
    }

    public String getTrips() {
        return trips;
    }

    public void setTrips(String trips) {
        this.trips = trips;
    }
}
