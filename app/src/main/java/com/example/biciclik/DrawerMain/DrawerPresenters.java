package com.example.biciclik.DrawerMain;

public class DrawerPresenters implements DrawerInterfaces.presenters {
    private DrawerInterfaces.activities view;
    private DrawerModels model;

    public DrawerPresenters(DrawerInterfaces.activities view) {
        this.view = view;
        this.model = new DrawerModels();
    }

    @Override
    public void logOutPresenters() {
        model.logOutModels(this);
    }
}
