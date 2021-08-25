package com.colombiagames.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("user")
    @Expose
    private UserData user;

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

    @SerializedName("company")
    @Expose
    private CompanyData company;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("selfie")
    @Expose
    private String selfie;

    @SerializedName("document_front_photo")
    @Expose
    private String document_front_photo;

    @SerializedName("document_back_photo")
    @Expose
    private String document_back_photo;

    @SerializedName("economic_savings")
    @Expose
    private int economic_savings;

    @SerializedName("carbon_footprint")
    @Expose
    private int carbon_footprint;

    @SerializedName("verified")
    @Expose
    private String verified;


    public UserResponse(String id, UserData user, String phone_number, CompanyData company, String address, String selfie, String document_front_photo, String document_back_photo, int economic_savings, int carbon_footprint, String verified) {
        this.id = id;
        this.user = user;
        this.phone_number = phone_number;
        this.company = company;
        this.address = address;
        this.selfie = selfie;
        this.document_front_photo = document_front_photo;
        this.document_back_photo = document_back_photo;
        this.economic_savings = economic_savings;
        this.carbon_footprint = carbon_footprint;
        this.verified = verified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public CompanyData getCompany() {
        return company;
    }

    public void setCompany(CompanyData company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSelfie() {
        return selfie;
    }

    public void setSelfie(String selfie) {
        this.selfie = selfie;
    }

    public String getDocument_front_photo() {
        return document_front_photo;
    }

    public void setDocument_front_photo(String document_front_photo) {
        this.document_front_photo = document_front_photo;
    }

    public String getDocument_back_photo() {
        return document_back_photo;
    }

    public void setDocument_back_photo(String document_back_photo) {
        this.document_back_photo = document_back_photo;
    }

    public int getEconomic_savings() {
        return economic_savings;
    }

    public void setEconomic_savings(int economic_savings) {
        this.economic_savings = economic_savings;
    }

    public int getCarbon_footprint() {
        return carbon_footprint;
    }

    public void setCarbon_footprint(int carbon_footprint) {
        this.carbon_footprint = carbon_footprint;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }
}
