package com.example.biciclik.Register;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.Register1Data;
import com.google.gson.Gson;

public class RegisterRequests implements RegisterInterfaces.requests{
    private RequestQueue queue;
    private Gson gson;
    LocalData localData;
    public RegisterRequests() {
        this.queue = Volley.newRequestQueue(BaseContext.getContext());
        this.gson = new Gson();
        this.localData= new LocalData();
    }

    @Override
    public void register1Request(RegisterInterfaces.presenters presenter) {
        Log.e("HHHHHH", localData.getRegister("USER"));
        Log.e("HHHHHH", localData.getRegister("EMAIL"));
        Log.e("HHHHHH", localData.getRegister("PASSWORD"));
        Log.e("HHHHHH", localData.getRegister("PHONE"));
        Log.e("HHHHHH", localData.getRegister("COMPANY"));
        Log.e("HHHHHH", localData.getRegister("ADDRESS"));
        Log.e("HHHHHH", localData.getRegister("SELFIE"));
        Log.e("HHHHHH", localData.getRegister("DOCUMENT_FRONT_PHOTO"));
        Log.e("HHHHHH", localData.getRegister("DOCUMENT_BACK_PHOTO"));
        Log.e("HHHHHH", "LLEGO");
    }
}
