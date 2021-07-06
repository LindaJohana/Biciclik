package com.example.biciclik.objects;

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
}
