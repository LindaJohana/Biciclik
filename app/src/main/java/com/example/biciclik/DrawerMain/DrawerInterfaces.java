package com.example.biciclik.DrawerMain;

import com.example.biciclik.objects.ProfileData;

public interface DrawerInterfaces {
    interface activities{
        void logOut();
        void setProfileHeader(ProfileData data);
        void lanzarLogin();
    }
    interface presenters{
        void logOutPresenters();
        void profileHeaderPresenter();
        void onSuccessProfileHeader(ProfileData data);
        void codelogin();
    }
    interface models{
        void logOutModels(presenters presenter);
        void profileHeaderModel(presenters presenter);
    }
}
