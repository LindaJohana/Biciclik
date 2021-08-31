package com.colombiagames.biciclick.utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomErrorResponse {

    public String returnMessageError(String error){

        try {
            if (error != null){
                JSONObject answer = new JSONObject(error);
                if (answer.has("message")) {
                    String response_user = "";
                    try{
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
                    }catch (Exception e){
                        String message = answer.getString("message");
                        response_user = message;
                    }
                    response_user = response_user.replace("['", "");
                    response_user=response_user.replace("']", "");
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
                    response_user=response_user.replace("['", "");
                    response_user=response_user.replace("']", "");
                    return response_user;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return "Ocurrio un error";
    }
}
