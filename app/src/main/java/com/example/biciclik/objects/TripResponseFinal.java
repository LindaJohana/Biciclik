package com.example.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripResponseFinal {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("modified_at")
    @Expose
    private String modified_at;

    @SerializedName("created_by")
    @Expose
    private String created_by;

    @SerializedName("modified_by")
    @Expose
    private String modified_by;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("user_detail")
    @Expose
    private ProfileData user_detail;

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

    @SerializedName("delivery_point")
    @Expose
    private String delivery_point;

    @SerializedName("time_elapsed")
    @Expose
    private String time_elapsed;

    @SerializedName("destination")
    @Expose
    private String destination;

    @SerializedName("delivery_date")
    @Expose
    private String delivery_date;

    @SerializedName("economic_savings")
    @Expose
    private String economic_savings;

    @SerializedName("carbon_footprint")
    @Expose
    private String carbon_footprint;

    @SerializedName("start_detail")
    @Expose
    private PointData start_detail;

    @SerializedName("delivery_detail")
    @Expose
    private PointData delivery_detail;

    public TripResponseFinal(String id, String created_at, String modified_at, String created_by, String modified_by, String user, ProfileData user_detail, String bike, String start_point, String start_date, String status, String delivery_point, String time_elapsed, String destination, String delivery_date, String economic_savings, String carbon_footprint, PointData start_detail, PointData delivery_detail) {
        this.id = id;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.user = user;
        this.user_detail = user_detail;
        this.bike = bike;
        this.start_point = start_point;
        this.start_date = start_date;
        this.status = status;
        this.delivery_point = delivery_point;
        this.time_elapsed = time_elapsed;
        this.destination = destination;
        this.delivery_date = delivery_date;
        this.economic_savings = economic_savings;
        this.carbon_footprint = carbon_footprint;
        this.start_detail = start_detail;
        this.delivery_detail = delivery_detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ProfileData getUser_detail() {
        return user_detail;
    }

    public void setUser_detail(ProfileData user_detail) {
        this.user_detail = user_detail;
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

    public String getDelivery_point() {
        return delivery_point;
    }

    public void setDelivery_point(String delivery_point) {
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

    public PointData getStart_detail() {
        return start_detail;
    }

    public void setStart_detail(PointData start_detail) {
        this.start_detail = start_detail;
    }

    public PointData getDelivery_detail() {
        return delivery_detail;
    }

    public void setDelivery_detail(PointData delivery_detail) {
        this.delivery_detail = delivery_detail;
    }
}
