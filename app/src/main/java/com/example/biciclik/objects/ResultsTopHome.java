package com.example.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultsTopHome {
    @SerializedName("results")
    @Expose
    ArrayList<ResultsResponse> results;

    public ResultsTopHome(ArrayList<ResultsResponse> results) {
        this.results = results;
    }

    public ArrayList<ResultsResponse> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsResponse> results) {
        this.results = results;
    }
}
