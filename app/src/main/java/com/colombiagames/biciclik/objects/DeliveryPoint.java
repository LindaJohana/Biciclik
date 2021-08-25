package com.colombiagames.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryPoint {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("started_travels")
    @Expose
    private String started_travels;

    @SerializedName("finished_travels")
    @Expose
    private String finished_travels;

    @SerializedName("maximum_bicis_quota")
    @Expose
    private String maximum_bicis_quota;

    @SerializedName("available_bicis")
    @Expose
    private String available_bicis;

    public DeliveryPoint(String name, String address, String city, String location, String started_travels, String finished_travels, String maximum_bicis_quota, String available_bicis) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.location = location;
        this.started_travels = started_travels;
        this.finished_travels = finished_travels;
        this.maximum_bicis_quota = maximum_bicis_quota;
        this.available_bicis = available_bicis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStarted_travels() {
        return started_travels;
    }

    public void setStarted_travels(String started_travels) {
        this.started_travels = started_travels;
    }

    public String getFinished_travels() {
        return finished_travels;
    }

    public void setFinished_travels(String finished_travels) {
        this.finished_travels = finished_travels;
    }

    public String getMaximum_bicis_quota() {
        return maximum_bicis_quota;
    }

    public void setMaximum_bicis_quota(String maximum_bicis_quota) {
        this.maximum_bicis_quota = maximum_bicis_quota;
    }

    public String getAvailable_bicis() {
        return available_bicis;
    }

    public void setAvailable_bicis(String available_bicis) {
        this.available_bicis = available_bicis;
    }
}
