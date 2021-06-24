package com.example.biciclik.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.DrawerMain.DrawerActivities;
import com.example.biciclik.ForgetPassword.ForgetPasswordActivity;
import com.example.biciclik.R;
import com.example.biciclik.Register.Register1Activity;
import com.example.biciclik.objects.LoginData;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivities extends AppCompatActivity implements LoginInterfaces.activities {
    public TextView TextViewRegistro, TextViewOlvideC;
    public Button ButtonAcceso;
    TextInputEditText InputTextEmail,InputTextCont;
    LoginPresenters presenter;
    LoginData login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initObjects();
        verifyToken();

//        TextViewRegistro=findViewById(R.id.textViewRegistro);
        TextViewRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarRegistro();
            }
        });
//        TextViewOlvideC=findViewById(R.id.textViewOlvideC);
        TextViewOlvideC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarOlvideC(null);
            }
        });
//        ButtonAcceso=findViewById(R.id.ingresarAcceso);
        ButtonAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(InputTextEmail.getText().toString().equals("") || InputTextCont.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Campo Vacio", Toast.LENGTH_SHORT).show();
                    InputTextEmail.requestFocus();
                }
                if(ValidarAlfanumerico(InputTextCont.getText().toString())==false){
                    Toast.makeText(getBaseContext(), "Campo especial", Toast.LENGTH_SHORT).show();
                    InputTextCont.requestFocus();
                }else
                setLogin();
            }
        });
        /*InputTextEmail=findViewById(R.id.inputTextEmail);
        InputTextCont=findViewById(R.id.inputTextCont);*/
    }

    private void initObjects(){
        presenter= new LoginPresenters(this);
        TextViewRegistro=findViewById(R.id.textViewRegistro);
        TextViewOlvideC=findViewById(R.id.textViewOlvideC);
        ButtonAcceso=findViewById(R.id.ingresarAcceso);
        InputTextEmail=findViewById(R.id.inputTextEmail);
        InputTextCont=findViewById(R.id.inputTextCont);

    }
    public boolean ValidarAlfanumerico(String password){
        return password.matches("[a-zA-Z0-9 *]+$");
    }

    public void lanzarRegistro(){
        Intent i = new Intent(BaseContext.getContext(), Register1Activity.class );
        startActivity(i);
    }
    public void lanzarOlvideC(View view){
        Intent i = new Intent(BaseContext.getContext(), ForgetPasswordActivity.class );
        startActivity(i);
    }


    public void setLogin(){
        login = new LoginData(InputTextEmail.getText().toString(),InputTextCont.getText().toString() );
        presenter.setLogin(login);
    }

    @SuppressLint("WrongConstant")
    public void setErrorLogin(String message) {
        Toast.makeText(getBaseContext(), message, 5000).show();
    }

    @Override
    public void lanzarPerfil() {
        Intent i = new Intent(BaseContext.getContext(), DrawerActivities.class );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void verifyToken(){
        presenter.getToken();
    }

}