package com.example.biciclik.utils;

import android.util.Log;

import com.android.volley.error.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class CustomErrorResponse {

    public String returnMessageError(VolleyError error){
        String decodedDataUsingUTF8;
        try {
            if (error.networkResponse.data != null){
                Log.d("LOGIN", error.networkResponse.data.toString());
                decodedDataUsingUTF8 = new String(error.networkResponse.data, "UTF-8");
                Log.d("LOGIN", decodedDataUsingUTF8);
                JSONObject answer = new JSONObject(decodedDataUsingUTF8);
                if (answer.has("message")) {
                    String response_user = "";
                    JSONObject message = answer.getJSONObject("message");
                    JSONArray key = message.names();
                    for (int i = 0; i < key.length(); i++) {
                        String keys = key.getString(i);
                        String value = message.getString(keys);
                        if (keys.equals("non_field_errors")) {
                            response_user += value.toString();
                        } else {
                            response_user += (keys.toString() + ": " + value.toString());
                        }
                    }
                    return response_user;
                } else {
                    JSONArray key = answer.names();
                    String response_user = "";
                    for (int i = 0; i < key.length(); i++) {
                        String keys = key.getString(i);
                        String value = answer.getString(keys);
                        if (keys.equals("non_field_errors")) {
                            response_user += value.toString();
                        } else {
                            response_user += (keys.toString() + ": " + value.toString());
                        }
                    }
                    return response_user;
                }
            }
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return "Ocurrio un error";
    }
}