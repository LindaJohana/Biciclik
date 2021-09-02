package com.colombiagames.biciclick.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.colombiagames.biciclick.BaseContext.BaseContext;
import com.colombiagames.biciclick.DrawerMain.DrawerActivities;
import com.colombiagames.biciclick.ForgetPassword.ForgetPasswordActivity;
import com.colombiagames.biciclick.R;
import com.colombiagames.biciclick.Register.Register1Activity;
import com.colombiagames.biciclick.objects.LoginResponse;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivities extends AppCompatActivity implements LoginInterfaces.activities {
    public TextView TextViewRegistro, TextViewOlvideC, txt10, txt11, txt12, txt13;
    public Button ButtonAcceso;
    TextInputEditText InputTextEmail,InputTextCont;
    LoginPresenters presenter;
    LoginResponse login;
    String home=null;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initObjects();
        verifyToken();
        extras = getIntent().getExtras();


        //fuente verdana
        Typeface fuente = Typeface.createFromAsset(getAssets(),"fonts/verdana.ttf");
        TextViewRegistro.setTypeface(fuente);
        TextViewOlvideC.setTypeface(fuente);
        txt10.setTypeface(fuente);
        txt11.setTypeface(fuente);
        txt12.setTypeface(fuente);
        InputTextEmail.setTypeface(fuente);
        txt13.setTypeface(fuente);
        InputTextCont.setTypeface(fuente);
        ButtonAcceso.setTypeface(fuente);

        TextViewRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarRegistro();
            }
        });
        TextViewOlvideC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarOlvideC(null);
            }
        });
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
    }

    private void initObjects(){
        presenter= new LoginPresenters(this);
        TextViewRegistro=findViewById(R.id.textViewRegistro);
        TextViewOlvideC=findViewById(R.id.textViewOlvideC);
        ButtonAcceso=findViewById(R.id.ingresarAcceso);
        InputTextEmail=findViewById(R.id.inputTextEmail);
        InputTextCont=findViewById(R.id.inputTextCont);
        txt10=findViewById(R.id.txt10);
        txt11=findViewById(R.id.txt11);
        txt12=findViewById(R.id.txt12);
        txt13=findViewById(R.id.txt13);
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
        login = new LoginResponse(InputTextEmail.getText().toString(),InputTextCont.getText().toString());
        presenter.setLogin(login);
    }

    @SuppressLint("WrongConstant")
    public void setErrorLogin(String message) {
        Toast.makeText(getBaseContext(), message, 5000).show();
    }

    @Override
    public void lanzarPerfil() {
//        presenter.sendPushTokenPresenters();
        Intent i = new Intent(BaseContext.getContext(), DrawerActivities.class );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("home",home);
        startActivity(i);
        if (extras!=null&&extras.containsKey("bikepush")) {
            Intent intent = new Intent(BaseContext.getContext(), DrawerActivities.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("bikepush","bikepush");
            startActivity(intent);
        }
    }

    public void verifyToken(){
        presenter.getToken();
    }

}