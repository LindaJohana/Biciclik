package com.example.biciclik.Login;


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
import com.example.biciclik.local_data.LocalDataInterface;
import com.example.biciclik.objects.LoginData;
import com.example.biciclik.objects.TokenResponse;
import com.example.biciclik.utils.CustomErrorResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class LoginRequests implements LoginInterfaces.requests {
    private RequestQueue queue;
    private Gson gson;
    LocalDataInterface localData;
//    CustomStrings customStrings;


    public LoginRequests() {
        this.queue = Volley.newRequestQueue(BaseContext.getContext());
        this.gson = new Gson();
        this.localData = new LocalData();
    }

    public void setLogin(LoginData login, LoginInterfaces.presenters presenter) {
        String url = BaseContext.getContext().getString(R.string.server) + "/api/token/";

        HashMap params = new HashMap();
        params.put("username", login.getUser());
        params.put("password", login.getPassword());

        JSONObject jsonBody = new JSONObject(params);
        Log.d("login", jsonBody.toString());
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response Longin", response.toString());
                        TokenResponse objects = null;
                        try {
                            objects = gson.fromJson(new String(response.toString().getBytes("UTF-8")),
                                    TokenResponse.class);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            objects = gson.fromJson(response.toString(), TokenResponse.class);
                        }
                        localData.SaveToken(objects.getRefresh(), objects.getAccess());
                        presenter.onSuccessLogin();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CustomErrorResponse custom_error = new CustomErrorResponse();
                String response_user = custom_error.returnMessageError(error);
                presenter.onErrorLogin(response_user);
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

    @Override
    public void validateToken(LoginInterfaces.presenters presenter, String refresh, String access) {
        String url = BaseContext.getContext().getString(R.string.server) + "/api/token/verify/";

        HashMap params = new HashMap();
        params.put("token", access);

        JSONObject jsonBody = new JSONObject(params);
        Log.e("validate access", jsonBody.toString());
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response Login", response.toString());
                        presenter.onSuccessLogin();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                refreshToken(presenter, refresh);
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

    @Override
    public void refreshToken(LoginInterfaces.presenters presenter, String refresh) {
        String url = BaseContext.getContext().getString(R.string.server) + "/api/token/refresh/";

        HashMap params = new HashMap();
        params.put("refresh", refresh);

        JSONObject jsonBody = new JSONObject(params);
        Log.e("refreshToken", jsonBody.toString());
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response Longin", response.toString());
                        TokenResponse objects = null;
                        try {
                            objects = gson.fromJson(new String(response.toString().getBytes("UTF-8")),
                                    TokenResponse.class);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            objects = gson.fromJson(response.toString(), TokenResponse.class);
                        }
                        localData.SaveToken(refresh, objects.getAccess());
                        presenter.onSuccessLogin();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                localData.LogOutApp();
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