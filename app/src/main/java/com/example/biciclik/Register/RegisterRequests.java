package com.example.biciclik.Register;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.utils.CustomErrorResponse;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
//        final String ruta = BaseContext.getContext().getString(R.string.server) + "/api/entrega/";
//        final Map<String, String> headers = new HashMap();
//        headers.put("Authorization", "Token " + localData.getToken());
//        headers.put("Accept", "application/json");
//        headers.put("Content-Type", "multipart/form-data");
//        RequestQueue mRequestQueue = Volley.newRequestQueue(BaseContext.getContext());
//        SimpleMultiPartRequest stringRequest = new SimpleMultiPartRequest(Request.Method.POST, ruta, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e("Peticiones/ADD DELIVERY", response.toString());
//                if (photo != "") {
//                    File fdelete = new File(photo);
//                    if (fdelete.exists()) {
//                        if (fdelete.delete()) {
//                            Log.e("Delete file", "file Deleted :" + photo);
//                        } else {
//                            Log.e("Delete file", "file Not Deleted :" + photo);
//                        }
//                    }
//                }
//                listener.onSuccess();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                String decodedDataUsingUTF8;
//                Log.d("LOGIN", error.toString());
//                if (error.networkResponse.data != null) {
//                    try {
//                        decodedDataUsingUTF8 = new String(error.networkResponse.data, "UTF-8");
//                        JSONObject answer = new JSONObject(decodedDataUsingUTF8);
//                        if (answer.has("message")) {
//                            String response_user = "";
//                            JSONObject message = answer.getJSONObject("message");
//                            JSONArray key = message.names();
//                            for (int i = 0; i < key.length(); i++) {
//                                String keys = key.getString(i);
//                                String value = message.getString(keys);
//                                if (keys.equals("non_field_errors")) {
//                                    response_user += value.toString();
//                                } else {
//                                    response_user += (keys.toString() + ": " + value.toString());
//                                }
//                            }
//                            listener.onError(response_user);
//                        } else {
//                            JSONArray key = answer.names();
//                            String response_user = "";
//                            for (int i = 0; i < key.length(); i++) {
//                                String keys = key.getString(i);
//                                String value = answer.getString(keys);
//                                if (keys.equals("non_field_errors")) {
//                                    response_user += value.toString();
//                                } else {
//                                    response_user += (keys.toString() + ": " + value.toString());
//                                }
//                            }
//                            listener.onError(response_user);
//                        }
//                    } catch (UnsupportedEncodingException | JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    listener.onError("Revisa tu conexión a internet");
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                return headers;
//            }
//        };
//        stringRequest.addStringParam("edificio", localData.getEdificioID());
//        stringRequest.addStringParam("estado", "ESPERA");
//        stringRequest.addStringParam("apto", apto);
//        stringRequest.addStringParam("remitente", sender);
//        if (photo != "") {
//            stringRequest.addFile("foto", photo);
//        }
//        stringRequest.setShouldCache(false);
//        stringRequest.setRetryPolicy(
//                new RetryPolicy() {
//                    @Override
//                    public int getCurrentTimeout() {
//                        return 50000;
//                    }
//                    @Override
//                    public int getCurrentRetryCount() {
//                        return 50000;
//                    }
//                    @Override
//                    public void retry(VolleyError error) throws VolleyError {
//                    }
//                }
//        );
//        mRequestQueue.add(stringRequest);
    }

    @Override
    public void getCompanyRequest(RegisterInterfaces.presenters presenter) {
        String url = BaseContext.getContext().getString(R.string.server) + "/api/company/";
        final Map<String, String> headers = new HashMap();
        //headers.put("Authorization", "Token " + localData.getToken());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        CompanyResponse objects_list = null;
                        try {
                            objects_list = gson.fromJson(new String(response.toString().getBytes("ISO-8859-1"), "UTF-8"), CompanyResponse.class);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            objects_list = gson.fromJson(response, CompanyResponse.class);
                        }

                        presenter.setCompaniesPresenters(objects_list.getCompanies());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CustomErrorResponse custom_error = new CustomErrorResponse();
                String response_user = custom_error.returnMessageError(error);
                presenter.onErrorPresenter(response_user);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
        stringRequest.setShouldCache(false);
//        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
    }
}
