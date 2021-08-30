package com.colombiagames.biciclick.local_data;

public interface LocalDataInterface {
    void LogOutApp();
    void CreateUser();
    void CreateBike();
    void CreateTrip();
    void SaveToken(String refresh, String Access);
    String getToken();
    String getRefresh();
    String getAccess();
    void register(String data, String key);
    void registerrRetry(int data);
    int getRegisterRetry();
}
