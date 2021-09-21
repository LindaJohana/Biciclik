package com.colombiagames.biciclick.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileData {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("user")
    @Expose
    private UserData user;

    @SerializedName("phone_number")
    @Expose
    private String phone_number;

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

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("company_detail")
    @Expose
    private CompanyData company_detail;

    @SerializedName("active")
    @Expose
    private String active;

    public ProfileData(UserData user, String address, String selfie) {
        this.user = user;
        this.address = address;
        this.selfie = selfie;
    }

    public ProfileData(String id, UserData user, String phone_number, String address, String selfie, String document_front_photo, String document_back_photo, int economic_savings, int carbon_footprint, String verified, String token, String company, CompanyData company_detail) {
        this.id = id;
        this.user = user;
        this.phone_number = phone_number;
        this.address = address;
        this.selfie = selfie;
        this.document_front_photo = document_front_photo;
        this.document_back_photo = document_back_photo;
        this.economic_savings = economic_savings;
        this.carbon_footprint = carbon_footprint;
        this.verified = verified;
        this.token = token;
        this.company = company;
        this.company_detail = company_detail;
    }

    public ProfileData(String id, UserData user, String phone_number, String address, String selfie, String document_front_photo, String document_back_photo, int economic_savings, int carbon_footprint, String verified, String token, String company, CompanyData company_detail, String active) {
        this.id = id;
        this.user = user;
        this.phone_number = phone_number;
        this.address = address;
        this.selfie = selfie;
        this.document_front_photo = document_front_photo;
        this.document_back_photo = document_back_photo;
        this.economic_savings = economic_savings;
        this.carbon_footprint = carbon_footprint;
        this.verified = verified;
        this.token = token;
        this.company = company;
        this.company_detail = company_detail;
        this.active = active;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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
        if (verified == null){
            verified=" ";
        }
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public CompanyData getCompany_detail() {
        return company_detail;
    }

    public void setCompany_detail(CompanyData company_detail) {
        this.company_detail = company_detail;
    }
}
