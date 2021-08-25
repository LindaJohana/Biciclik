package com.colombiagames.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register1Data {

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("address")
    @Expose
    private String address;
    public Register1Data(String phone, String company, String address) {
        this.phone = phone;
        this.company = company;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
