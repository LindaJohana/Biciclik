package com.example.biciclik.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Register2Data {
    @SerializedName("selfie")
    @Expose
    private String selfie;

    @SerializedName("documentFrontPhoto")
    @Expose
    private String documentFrontPhoto;

    @SerializedName("documentBackPhoto")
    @Expose
    private String  documentBackPhoto;

    public Register2Data(String selfie, String documentFrontPhoto, String documentBackPhoto) {
        this.selfie = selfie;
        this.documentFrontPhoto = documentFrontPhoto;
        this.documentBackPhoto = documentBackPhoto;
    }

    public String getSelfie() {
        return selfie;
    }

    public void setSelfie(String selfie) {
        this.selfie = selfie;
    }

    public String getDocumentFrontPhoto() {
        return documentFrontPhoto;
    }

    public void setDocumentFrontPhoto(String documentFrontPhoto) {
        this.documentFrontPhoto = documentFrontPhoto;
    }

    public String getDocumentBackPhoto() {
        return documentBackPhoto;
    }

    public void setDocumentBackPhoto(String documentBackPhoto) {
        this.documentBackPhoto = documentBackPhoto;
    }
}
