package com.example.biciclik.DrawerMain;

import com.example.biciclik.local_data.LocalData;

public class DrawerModels implements DrawerInterfaces.models {
    private DrawerInterfaces.models model;
    private LocalData localData;

    public DrawerModels() {
        this.model = model;
        this.localData = new LocalData();
    }

    @Override
    public void logOutModels(DrawerInterfaces.presenters presenter) {
        localData.LogOutApp();
    }
}
