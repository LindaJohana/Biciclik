package com.example.biciclik.Register;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
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
