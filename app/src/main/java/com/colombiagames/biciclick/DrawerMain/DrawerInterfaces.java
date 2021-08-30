package com.colombiagames.biciclick.DrawerMain;

import com.colombiagames.biciclick.objects.ProfileData;

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
