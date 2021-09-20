package com.colombiagames.biciclick.DrawerMain;

import com.colombiagames.biciclick.objects.ProfileData;

public class DrawerPresenters implements DrawerInterfaces.presenters {
    private DrawerInterfaces.activities view1;
    private DrawerModels model;

    public DrawerPresenters(DrawerInterfaces.activities view1) {
        this.view1 = view1;
        this.model = new DrawerModels();
    }

    @Override
    public void logOutPresenters() {
        model.logOutModels(this);
    }

    @Override
    public void profileHeaderPresenter() {
        model.profileHeaderModel(this);
    }

    @Override
    public void onSuccessProfileHeader(ProfileData data) {
        view1.setProfileHeader(data);
    }

    @Override
    public void codelogin() {
        view1.lanzarLogin();
    }

    @Override
    public void verifiedPresenter() {
        model.verifiedModel(this);
    }

    @Override
    public void verifiedSuccess(String verified, String active) {
        view1.verified(verified, active);
    }

    @Override
    public void logoutPushPresenter() {
        model.logoutPushModel(this);
    }

    @Override
    public void onSuccessLogoutPush() {
        view1.logOut();
    }
}
