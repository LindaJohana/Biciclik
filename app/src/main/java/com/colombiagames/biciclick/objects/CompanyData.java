package com.colombiagames.biciclick.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyData {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("modified_at")
    @Expose
    private String modified_at;

    @SerializedName("created_by")
    @Expose
    private String created_by;

    @SerializedName("modified_by")
    @Expose
    private String modified_by;

    @SerializedName("name")
    @Expose
    private String name;

    public CompanyData(String id, String created_at, String modified_at, String created_by, String modified_by, String name) {
        this.id = id;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
