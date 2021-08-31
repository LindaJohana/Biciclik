package com.colombiagames.biciclick.local_data;

import android.content.SharedPreferences;

import com.colombiagames.biciclick.BaseContext.BaseContext;


public class LocalData implements LocalDataInterface {
    @Override
    public void LogOutApp() {
        String token = "";
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        preferences.edit().putString("REFRESH", "").apply();
        preferences.edit().putString("ACCESS", "").apply();
    }

    @Override
    public void CreateUser() {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        preferences.edit().putString("SELFIE", "").apply();
        preferences.edit().putString("DOCUMENT_FROT_PHOTO", "").apply();
        preferences.edit().putString("DOCUMENT_BACK_PHOTO", "").apply();
    }

    @Override
    public void CreateBike() {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        preferences.edit().putString("IDBIKE", "").apply();
        preferences.edit().putString("POINTBIKE", "").apply();
        preferences.edit().putString("INITIATED", "").apply();
    }
    @Override
    public void CreateTrip() {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        preferences.edit().putString("START_DATE", "").apply();
        preferences.edit().putString("START_POINT", "").apply();
        preferences.edit().putString("CHRONOMETER_S", "").apply();
    }

    @Override
    public void SaveToken(String refresh, String access) {
        String token = "Some token From Server";
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        preferences.edit().putString("REFRESH", refresh).apply();
        preferences.edit().putString("ACCESS", access).apply();
    }

    @Override
    public String getToken() {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        final String token = preferences.getString("TOKEN", null);
        if (token != null) {
            return token;
        }
        return "";
    }

    @Override
    public String getRefresh() {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        final String refresh = preferences.getString("REFRESH", null);
        if (refresh != null) {
            return refresh;
        }
        return "";
    }

    @Override
    public String getAccess() {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        final String access = preferences.getString("ACCESS", null);
        if (access != null) {
            return access;
        }
        return "";
    }

    @Override
    public void register(String data, String key) {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        preferences.edit().putString(key, data).apply();
    }

    @Override
    public void registerrRetry(int data) {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        preferences.edit().putInt("RETRY", data).apply();
    }

    public int getRegisterRetry() {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        final int data = preferences.getInt("RETRY", 0);
        return data;
    }

    public String getRegister(String key) {
        SharedPreferences preferences = BaseContext.getContext().getSharedPreferences("Biciclick", BaseContext.getContext().MODE_PRIVATE);
        final String data = preferences.getString(key, null);
        if (data != null) {
            return data;
        }
        return "";
    }
}
