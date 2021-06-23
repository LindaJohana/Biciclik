package com.example.biciclik.Login;

import com.example.biciclik.objects.LoginData;

public interface LoginInterfaces {
    interface activities{
        void setLogin();
        void setErrorLogin(String message);
        void lanzarPerfil();
    }
    interface presenters{
        void setLogin(LoginData login);
        void onErrorLogin(String message);
        void onSuccessLogin();
        void getToken();

    }
    interface models{
        void setLogin(LoginData login, presenters presenter);
        void getTokenModel(presenters presenter);
    }
    interface requests {
        void setLogin(LoginData login, presenters presenter);
        void validateToken(presenters presenter, String refresh, String access);
        void refreshToken(presenters presenter, String refresh);
    }
}
