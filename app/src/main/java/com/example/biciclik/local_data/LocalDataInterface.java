package com.example.biciclik.local_data;

public interface LocalDataInterface {
    void LogOutApp();
    void SaveToken(String refresh, String Access);
    String getRefresh();
    String getAccess();
}
