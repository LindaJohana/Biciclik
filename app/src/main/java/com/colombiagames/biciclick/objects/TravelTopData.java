package com.colombiagames.biciclick.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelTopData {
    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("user__user__first_name")
    @Expose
    private String user__user__first_name;

    @SerializedName("user__user__last_name")
    @Expose
    private String user__user__last_name;

    @SerializedName("user__selfie")
    @Expose
    private String user__selfie;

    @SerializedName("trips")
    @Expose
    private int trips;

    public TravelTopData(String user, String user__user__first_name, String user__user__last_name, String user__selfie, int trips) {
        this.user = user;
        this.user__user__first_name = user__user__first_name;
        this.user__user__last_name = user__user__last_name;
        this.user__selfie = user__selfie;
        this.trips = trips;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser__user__first_name() {
        return user__user__first_name;
    }

    public void setUser__user__first_name(String user__user__first_name) {
        this.user__user__first_name = user__user__first_name;
    }

    public String getUser__user__last_name() {
        return user__user__last_name;
    }

    public void setUser__user__last_name(String user__user__last_name) {
        this.user__user__last_name = user__user__last_name;
    }

    public String getUser__selfie() {
        return user__selfie;
    }

    public void setUser__selfie(String user__selfie) {
        this.user__selfie = user__selfie;
    }

    public int getTrips() {
        return trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }
}
