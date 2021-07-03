//package com.example.biciclik.Register;
//
//import android.os.Build;
//import android.util.Log;
//
//import androidx.annotation.RequiresApi;
//
//import com.example.biciclik.BaseContext.BaseContext;
//import com.example.biciclik.R;
//import com.example.biciclik.local_data.LocalData;
//import com.example.biciclik.objects.CompanyResponse;
//import com.example.biciclik.utils.CustomErrorResponse;
//import com.google.gson.Gson;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class RegisterRequests implements RegisterInterfaces.requests{
//    private RequestQueue queue;
//    private Gson gson;
//    LocalData localData;
//    public RegisterRequests() {
//        this.queue = Volley.newRequestQueue(BaseContext.getContext());
//        this.gson = new Gson();
//        this.localData= new LocalData();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    public void register1Request(RegisterInterfaces.presenters presenter) {
//        Log.e("HHHHHH", localData.getRegister("USER"));
//        Log.e("HHHHHH", localData.getRegister("EMAIL"));
//        Log.e("HHHHHH", localData.getRegister("PASSWORD"));
//        Log.e("HHHHHH", localData.getRegister("PHONE"));
//        Log.e("HHHHHH", localData.getRegister("COMPANY"));
//        Log.e("HHHHHH", localData.getRegister("ADDRESS"));
//        Log.e("HHHHHH", localData.getRegister("SELFIE"));
//        Log.e("HHHHHH", localData.getRegister("DOCUMENT_FRONT_PHOTO"));
//        Log.e("HHHHHH", localData.getRegister("DOCUMENT_BACK_PHOTO"));
//        Log.e("HHHHHH", "LLEGO");
//        final String ruta = BaseContext.getContext().getString(R.string.server) + "/api/user/";
//        final Map<String, String> headers = new HashMap();
//        headers.put("Authorization", "Token " + localData.getToken());
//        headers.put("Accept", "application/json");
//        headers.put("Content-Type", "multipart/form-data");
//
//        Map user = new HashMap();
//        user.put("username", localData.getRegister("USER"));
//        user.put("password", localData.getRegister("PASSWORD"));
//        user.put("email", localData.getRegister("EMAIL"));
//        Map params = new HashMap();
//        params.put("user",user);
//        params.put("phone_number", localData.getRegister("PHONE"));
//        params.put("company", localData.getRegister("COMPANY"));
//        params.put("address", localData.getRegister("ADDRESS"));
//        JSONObject jsonBody = new JSONObject(params);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, ruta,
//                new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            CompanyResponse objects_list = null;
//                            try {
//                                objects_list = gson.fromJson(new String(response.toString().getBytes("ISO-8859-1"), "UTF-8"), CompanyResponse.class);
//                            } catch (UnsupportedEncodingException e) {
//                                e.printStackTrace();
//                                objects_list = gson.fromJson(response, CompanyResponse.class);
//                            }
//
//                            presenter.setCompaniesPresenters(objects_list.getCompanies());
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            CustomErrorResponse custom_error = new CustomErrorResponse();
//                            String response_user = custom_error.returnMessageError(error);
//                            presenter.onErrorPresenterCompany(response_user);
//                        }
//                    }) {
//                        @Override
//                        public Map<String, String> getHeaders() throws AuthFailureError {
//                            return headers;
//                        }
//
//            @Override
//            protected Map getParams() throws AuthFailureError {
//                params.put("selfie", localData.getRegister("SELFIE"));
//                params.put("document_front_photo", localData.getRegister("DOCUMENT_FRONT_PHOTO"));
//                params.put("document_back_photo", localData.getRegister("DOCUMENT_BACK_PHOTO"));
//
//                return params;
//            }
//        };
//        stringRequest.setShouldCache(false);
////        stringRequest.addFile("selfie", localData.getRegister("SELFIE"));
////        stringRequest.addFile("document_front_photo", localData.getRegister("DOCUMENT_FRONT_PHOTO"));
////        stringRequest.addFile("document_back_photo", localData.getRegister("DOCUMENT_BACK_PHOTO"));
//////        stringRequest.setRetryPolicy(policy);
//        queue.add(stringRequest);
//
//
//    }
//
//    @Override
//    public void getCompanyRequest(RegisterInterfaces.presenters presenter) {
//        String url = BaseContext.getContext().getString(R.string.server) + "/api/company/";
//        final Map<String, String> headers = new HashMap();
//        //headers.put("Authorization", "Token " + localData.getToken());
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        CompanyResponse objects_list = null;
//                        try {
//                            objects_list = gson.fromJson(new String(response.toString().getBytes("ISO-8859-1"), "UTF-8"), CompanyResponse.class);
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                            objects_list = gson.fromJson(response, CompanyResponse.class);
//                        }
//
//                        presenter.setCompaniesPresenters(objects_list.getCompanies());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                CustomErrorResponse custom_error = new CustomErrorResponse();
//                String response_user = custom_error.returnMessageError(error);
//                presenter.onErrorPresenterCompany(response_user);
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                return headers;
//            }
//        };
//        stringRequest.setShouldCache(false);
////        stringRequest.setRetryPolicy(policy);
//        queue.add(stringRequest);
//    }
//}
