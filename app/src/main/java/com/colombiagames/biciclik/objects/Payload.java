package com.colombiagames.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {
    @SerializedName("token_type")
    @Expose
    private String token_type;

    @SerializedName("exp")
    @Expose
    private String exp;

    @SerializedName("jti")
    @Expose
    private String jti;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    public Payload(String token_type, String exp, String jti, String user_id) {
        this.token_type = token_type;
        this.exp = exp;
        this.jti = jti;
        this.user_id = user_id;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
