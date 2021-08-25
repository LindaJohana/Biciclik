package com.colombiagames.biciclik.Profile;

import com.colombiagames.biciclik.objects.ProfileData;
import com.colombiagames.biciclik.objects.UserData;

public interface ProfileInterfaces {
    interface activities{
        void setProfile(ProfileData data);
        void setErrorMessage(String message);
        void lanzarLogin();
    }
    interface presenters{
        void getProfilePresenter();
        void onSuccessProfile(ProfileData data);
        void updatePresenter(UserData changedUser, ProfileData changedData);
        void onSuccessUpdate(ProfileData data);
        void onErrorMessage(String message);
        void codelogin();
    }
    interface models{
        void getProfileModel(presenters presenter);
        void updateModel(presenters presenter, UserData changedUser, ProfileData changedData);
    }
}
