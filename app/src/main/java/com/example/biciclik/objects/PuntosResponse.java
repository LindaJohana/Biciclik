package com.example.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PuntosResponse {
    @SerializedName("punto")
    @Expose
    private String punto;

    @SerializedName("distancia")
    @Expose
    private int distancia;

    public PuntosResponse(String punto, int distancia) {
        this.punto = punto;
        this.distancia = distancia;
    }

    public String getPunto() {
        return punto;
    }

    public void setPunto(String punto) {
        this.punto = punto;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}
