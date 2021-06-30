package com.example.biciclik.ForgetPassword;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.EmailData;
import com.example.biciclik.objects.MessageResponse;
import com.example.biciclik.utils.CustomErrorResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordRequests implements ForgetPasswordInterfaces.requests{
    private RequestQueue queue;
    private Gson gson;
    LocalData localData;
    public ForgetPasswordRequests() {
        this.queue = Volley.newRequestQueue(BaseContext.getContext());
        this.gson = new Gson();
        this.localData= new LocalData();
    }
    @Override
    public void sendEmailRequest(EmailData email, ForgetPasswordInterfaces.presenters presenter) {
        String url = BaseContext.getContext().getString(R.string.server) + "/api/recover_password/";

        HashMap params = new HashMap();
        params.put("username", email.getUser());

        JSONObject jsonBody = new JSONObject(params);
        Log.d("forgetPassword", jsonBody.toString());
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Response forgetpassword", response.toString());
                        MessageResponse objects = null;
                        try {
                            objects = gson.fromJson(new String(response.toString().getBytes("UTF-8")),
                                    MessageResponse.class);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            objects = gson.fromJson(response.toString(), MessageResponse.class);
                        }
                        presenter.onSuccessEmail(objects.getMessage());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CustomErrorResponse custom_error = new CustomErrorResponse();
                String response_user = custom_error.returnMessageError(error);
                presenter.onErrorEmail(response_user);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap();
//                headers.put("Authorization", "Token " + localData.getToken());
                return headers;
            }
        };
        stringRequest.setShouldCache(false);
//        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
    }
}
