package com.colombiagames.biciclick.Login;

import com.colombiagames.biciclick.objects.LoginResponse;

public interface LoginInterfaces {
    interface activities{
        void setLogin();
        void setErrorLogin(String message);
        void lanzarPerfil();
    }
    interface presenters{
        void setLogin(LoginResponse login);
        void onErrorLogin(String message);
        void onSuccessLogin();
        void getToken();
        void sendPushTokenPresenters();
    }
    interface models{
        void setLogin(LoginResponse login, presenters presenter);
        void getTokenModel(presenters presenter);
        void refreshToken(presenters presenter, String refresh);
        void sendPushTokenModels();
    }
    /*interface requests {
        void setLogin(LoginResponse login, presenters presenter);
        void validateToken(presenters presenter, String refresh, String access);
        void refreshToken(presenters presenter, String refresh);
    }*/
}
