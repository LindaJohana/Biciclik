package com.example.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointData {
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
    private String maximun_bicis_quota;

    @SerializedName("available_bicis")
    @Expose
    private String available_bicis;

    public PointData(String id, String location, String created_at, String modified_at, String created_by, String modified_by, String name, String address) {
        this.id = id;
        this.location=location;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.name = name;
        this.address = address;
    }

    public PointData(String id, String created_at, String modified_at, String created_by, String modified_by, String name, String address, String city, String location, String started_travels, String finished_travels, String maximun_bicis_quota, String available_bicis) {
        this.id = id;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.name = name;
        this.address = address;
        this.city = city;
        this.location = location;
        this.started_travels = started_travels;
        this.finished_travels = finished_travels;
        this.maximun_bicis_quota = maximun_bicis_quota;
        this.available_bicis = available_bicis;
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

    public String getMaximun_bicis_quota() {
        return maximun_bicis_quota;
    }

    public void setMaximun_bicis_quota(String maximun_bicis_quota) {
        this.maximun_bicis_quota = maximun_bicis_quota;
    }

    public String getAvailable_bicis() {
        return available_bicis;
    }

    public void setAvailable_bicis(String available_bicis) {
        this.available_bicis = available_bicis;
    }
}
