package com.example.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTripData {
    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("bike")
    @Expose
    private String bike;

    @SerializedName("start_point")
    @Expose
    private String start_point;

    @SerializedName("start_date")
    @Expose
    private String start_date;

    @SerializedName("status")
    @Expose
    private String status;

    public CreateTripData(String user, String bike, String start_point, String start_date, String status) {
        this.user = user;
        this.bike = bike;
        this.start_point = start_point;
        this.start_date = start_date;
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBike() {
        return bike;
    }

    public void setBike(String bike) {
        this.bike = bike;
    }

    public String getStart_point() {
        return start_point;
    }

    public void setStart_point(String start_point) {
        this.start_point = start_point;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
