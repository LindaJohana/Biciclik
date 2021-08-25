package com.colombiagames.biciclik.objects;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class TravelTopResponse {
    @Expose
    ArrayList<TravelTopData> results;

    public TravelTopResponse(ArrayList<TravelTopData> results) {
        this.results = results;
    }

    public ArrayList<TravelTopData> getResults() {
        return results;
    }

    public void setResults(ArrayList<TravelTopData> results) {
        this.results = results;
    }
}
