package com.colombiagames.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BikeData {
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

    @SerializedName("qr_code")
    @Expose
    private String qr_code;

    @SerializedName("qr_code_value")
    @Expose
    private String qr_code_value;

    @SerializedName("mac_lock")
    @Expose
    private String mac_lock;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("travels")
    @Expose
    private int travels;

    @SerializedName("actual_point")
    @Expose
    private PointData actual_point;

    public BikeData(String id, String created_at, String modified_at, String created_by, String modified_by, String qr_code, String qr_code_value, String mac_lock, String status, String location, int travels, PointData actual_point) {
        this.id = id;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.qr_code = qr_code;
        this.qr_code_value = qr_code_value;
        this.mac_lock = mac_lock;
        this.status = status;
        this.location = location;
        this.travels = travels;
        this.actual_point = actual_point;
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

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getQr_code_value() {
        return qr_code_value;
    }

    public void setQr_code_value(String qr_code_value) {
        this.qr_code_value = qr_code_value;
    }

    public String getMac_lock() {
        return mac_lock;
    }

    public void setMac_lock(String mac_lock) {
        this.mac_lock = mac_lock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTravels() {
        return travels;
    }

    public void setTravels(int travels) {
        this.travels = travels;
    }

    public PointData getActual_point() {
        return actual_point;
    }

    public void setActual_point(PointData actual_point) {
        this.actual_point = actual_point;
    }
}
