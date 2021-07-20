package com.example.biciclik.Profile;

import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.ProfileData;
import com.example.biciclik.objects.UserData;

import java.util.ArrayList;

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
