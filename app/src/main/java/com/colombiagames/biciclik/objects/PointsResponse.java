package com.colombiagames.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PointsResponse {
    @SerializedName("results")
    @Expose
    ArrayList<PointData> results;

    public PointsResponse(ArrayList<PointData> results) {
        this.results = results;
    }

    public ArrayList<PointData> getResults() {
        return results;
    }

    public void setResults(ArrayList<PointData> results) {
        this.results = results;
    }
}
