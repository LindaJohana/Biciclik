package com.example.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatisticsData {
    @SerializedName("economic_savings")
    @Expose
    private int economic_savings;

    @SerializedName("carbon_footprint")
    @Expose
    private int carbon_footprint;

    @SerializedName("trips")
    @Expose
    private int trips;

    public StatisticsData(int economic_savings, int carbon_footprint, int trips) {
        this.economic_savings = economic_savings;
        this.carbon_footprint = carbon_footprint;
        this.trips = trips;
    }

    public int getEconomic_savings() {
        return economic_savings;
    }

    public void setEconomic_savings(int economic_savings) {
        this.economic_savings = economic_savings;
    }

    public int getCarbon_footprint() {
        return carbon_footprint;
    }

    public void setCarbon_footprint(int carbon_footprint) {
        this.carbon_footprint = carbon_footprint;
    }

    public int getTrips() {
        return trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }
}
