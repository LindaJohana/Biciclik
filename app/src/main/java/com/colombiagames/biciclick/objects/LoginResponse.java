package com.colombiagames.biciclick.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("password")
    @Expose
    private String password;

    public LoginResponse(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
