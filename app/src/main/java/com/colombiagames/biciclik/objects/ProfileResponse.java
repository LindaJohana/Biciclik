package com.colombiagames.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfileResponse {
    @SerializedName("results")
    @Expose
    ArrayList<ProfileData> results;

    public ProfileResponse(ArrayList<ProfileData> results) {
        this.results = results;
    }

    public ArrayList<ProfileData> getResults() {
        return results;
    }

    public void setResults(ArrayList<ProfileData> results) {
        this.results = results;
    }
}
