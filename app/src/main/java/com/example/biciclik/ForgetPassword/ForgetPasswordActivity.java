package com.example.biciclik.ForgetPassword;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.biciclik.Login.LoginActivities;
import com.example.biciclik.R;
import com.example.biciclik.objects.EmailData;

import java.util.regex.Pattern;

public class ForgetPasswordActivity extends Activity implements ForgetPasswordInterfaces.activities{
    public Button ButtonRecordar;
    public EditText InputTextEmail;
    ForgetPasswordPresenters presenter;
    EmailData email;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        initObjects();
        ButtonRecordar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(InputTextEmail.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Campo Vacio", Toast.LENGTH_SHORT).show();
                    InputTextEmail.requestFocus();
                }
                if (!validarEmail(InputTextEmail.getText().toString())){
                    InputTextEmail.setError("Email no válido");
                }else sendEmail();

            }
        });
    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    private void initObjects(){
        ButtonRecordar=findViewById(R.id.buttonRecordar);
        InputTextEmail=findViewById(R.id.inputTextEmail);
        presenter=new ForgetPasswordPresenters(this);
    }

    @Override
    public void sendEmail() {
        email=new EmailData(InputTextEmail.getText().toString());
        presenter.SendEmailPresenter(email);
    }

    @Override
    public void lanzarLogin(String message) {
        Toast.makeText(getBaseContext(),message, 500-0).show();
        Intent i = new Intent(this, LoginActivities.class );
        startActivity(i);
    }

    @SuppressLint("WrongConstant")
    public void setErrorEmail(String message) {
        Toast.makeText(getBaseContext(), message, 5000).show();
    }
}
