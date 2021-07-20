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

    @SerializedName("time_elapsed")
    @Expose
    private String time_elapsed;

    @SerializedName("destination")
    @Expose
    private String destination;

    @SerializedName("delivery_date")
    @Expose
    private String delivery_date;

    @SerializedName("delivery_point")
    @Expose
    private String delivery_point;

    public CreateTripData(String user, String bike, String start_point, String start_date, String status) {
        this.user = user;
        this.bike = bike;
        this.start_point = start_point;
        this.start_date = start_date;
        this.status = status;
    }

    public CreateTripData(String user, String bike, String start_point, String start_date, String status, String time_elapsed, String destination, String delivery_date, String delivery_point) {
        this.user = user;
        this.bike = bike;
        this.start_point = start_point;
        this.start_date = start_date;
        this.status = status;
        this.time_elapsed = time_elapsed;
        this.destination = destination;
        this.delivery_date = delivery_date;
        this.delivery_point = delivery_point;
    }

    public String getTime_elapsed() {
        return time_elapsed;
    }

    public void setTime_elapsed(String time_elapsed) {
        this.time_elapsed = time_elapsed;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelivery_point() {
        return delivery_point;
    }

    public void setDelivery_point(String delivery_point) {
        this.delivery_point = delivery_point;
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
