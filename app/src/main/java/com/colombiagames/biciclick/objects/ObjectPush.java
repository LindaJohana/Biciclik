package com.colombiagames.biciclick.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectPush {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("registration_id")
    @Expose
    private String registration_id;

    @SerializedName("device_id")
    @Expose
    private int device_id;

    @SerializedName("active")
    @Expose
    private boolean active;

    @SerializedName("cloud_message_type")
    @Expose
    private String cloud_message_type;

    @SerializedName("application_id")
    @Expose
    private String application_id;

    public ObjectPush(int id, String name, String registration_id, int device_id, boolean active, String cloud_message_type, String application_id) {
        this.id = id;
        this.name = name;
        this.registration_id = registration_id;
        this.device_id = device_id;
        this.active = active;
        this.cloud_message_type = cloud_message_type;
        this.application_id = application_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCloud_message_type() {
        return cloud_message_type;
    }

    public void setCloud_message_type(String cloud_message_type) {
        this.cloud_message_type = cloud_message_type;
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }
}
