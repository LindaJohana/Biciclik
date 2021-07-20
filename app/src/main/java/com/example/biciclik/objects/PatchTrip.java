package com.example.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatchTrip {
    @SerializedName("time_elapsed")
    @Expose
    private String time_elapsed;

    @SerializedName("destination")
    @Expose
    private String destination;

    @SerializedName("delivery_point")
    @Expose
    private String delivery_point;

    public PatchTrip(String time_elapsed, String destination, String delivery_point) {
        this.time_elapsed = time_elapsed;
        this.destination = destination;
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

    public String getDelivery_point() {
        return delivery_point;
    }

    public void setDelivery_point(String delivery_point) {
        this.delivery_point = delivery_point;
    }
}
