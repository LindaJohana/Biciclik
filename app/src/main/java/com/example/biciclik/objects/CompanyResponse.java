package com.example.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CompanyResponse {
    @SerializedName("results")
    @Expose
    ArrayList<CompanyData> companies;

    public CompanyResponse(ArrayList<CompanyData> companies) {
        this.companies = companies;
    }

    public ArrayList<CompanyData> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<CompanyData> companies) {
        this.companies = companies;
    }
}
