package com.colombiagames.biciclick.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripResponse {
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
    private ProfileData user;

    @SerializedName("bike")
    @Expose
    private BikeData bike;

    @SerializedName("start_point")
    @Expose
    private PointData start_point;

    @SerializedName("start_date")
    @Expose
    private String start_date;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("delivery_point")
    @Expose
    private DeliveryPoint delivery_point;

    @SerializedName("time_elapsed")
    @Expose
    private String time_elapsed;

    @SerializedName("destination")
    @Expose
    private String destination;

    @SerializedName("delivery_date")
    @Expose
    private String delivery_date;

    public TripResponse(String id, String created_at, String modified_at, String created_by, String modified_by, ProfileData user, BikeData bike, PointData start_point, String start_date) {
        this.id = id;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.user = user;
        this.bike = bike;
        this.start_point = start_point;
        this.start_date = start_date;
    }

    public TripResponse(String id, String created_at, String modified_at, String created_by, String modified_by, ProfileData user, BikeData bike, PointData start_point, String start_date, String status, DeliveryPoint delivery_point, String time_elapsed, String destination, String delivery_date) {
        this.id = id;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.user = user;
        this.bike = bike;
        this.start_point = start_point;
        this.start_date = start_date;
        this.status = status;
        this.delivery_point = delivery_point;
        this.time_elapsed = time_elapsed;
        this.destination = destination;
        this.delivery_date = delivery_date;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTime_elapsed() {
        return time_elapsed;
    }

    public void setTime_elapsed(String time_elapsed) {
        this.time_elapsed = time_elapsed;
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

    public ProfileData getUser() {
        return user;
    }

    public void setUser(ProfileData user) {
        this.user = user;
    }

    public BikeData getBike() {
        return bike;
    }

    public void setBike(BikeData bike) {
        this.bike = bike;
    }

    public PointData getStart_point() {
        return start_point;
    }

    public void setStart_point(PointData start_point) {
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

    public DeliveryPoint getDelivery_point() {
        return delivery_point;
    }

    public void setDelivery_point(DeliveryPoint delivery_point) {
        this.delivery_point = delivery_point;
    }
}
