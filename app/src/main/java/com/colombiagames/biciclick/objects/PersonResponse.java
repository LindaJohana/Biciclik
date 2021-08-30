package com.colombiagames.biciclick.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonResponse {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("last_name")
    @Expose
    private String last_name;

    @SerializedName("viajes")
    @Expose
    private int viajes;

    public PersonResponse(String name, String last_name, int viajes) {
        this.name = name;
        this.last_name = last_name;
        this.viajes = viajes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getViajes() {
        return viajes;
    }

    public void setViajes(int viajes) {
        this.viajes = viajes;
    }
}
