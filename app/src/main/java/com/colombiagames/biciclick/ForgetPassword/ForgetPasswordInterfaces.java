package com.colombiagames.biciclick.ForgetPassword;

import com.colombiagames.biciclick.objects.EmailData;

public interface ForgetPasswordInterfaces {
    interface activities{
        void sendEmail();
        void lanzarLogin(String message);
        void setErrorEmail(String message);
    }
    interface presenters{
        void SendEmailPresenter(EmailData email);
        void onSuccessEmail(String message);
        void onErrorEmail(String message);
    }
    interface models{
        void sendEmailModel(EmailData email, presenters presenter);
    }
    interface requests{
        void sendEmailRequest(EmailData email, presenters presenter);
    }
}
