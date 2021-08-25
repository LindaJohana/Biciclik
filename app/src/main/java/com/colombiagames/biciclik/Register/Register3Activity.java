package com.colombiagames.biciclik.Register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.colombiagames.biciclik.R;
import com.colombiagames.biciclik.RegisterSuccess.RegisterSuccessActivity;

public class Register3Activity extends Activity implements RegisterInterfaces.activities3 {
    EditText D1, D2, D3, D4;
    RegisterPresenters presenter;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register3);
        initObjects();

        D1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(D1.getText().toString()).equals("")){
                    D2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        D2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(D2.getText().toString()).isEmpty()){
                    D3.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        D3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(D3.getText().toString()).isEmpty()){
                    D4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        D4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(D4.getText().toString()).isEmpty()){
                    verify();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        watcher();

    }
    public void initObjects(){
        D1=findViewById(R.id.digito1);
        D2=findViewById(R.id.digito2);
        D3=findViewById(R.id.digito3);
        D4=findViewById(R.id.digito4);
        presenter=new RegisterPresenters(null, null, this);
    }
    public void watcher(){

    }
    public void verify(){
        String texto;
        texto=D1.getText().toString()+D2.getText().toString()+D3.getText().toString()+D4.getText().toString();
        presenter.verifyPresenter(texto);
    }

    @Override
    public void setError(String message) {
        Toast.makeText(getBaseContext(), message, 600-0).show();
        D1.setText("");
        D2.setText("");
        D3.setText("");
        D4.setText("");
        D1.requestFocus();
    }

    public void lanzarExitoso(View view){
        Intent i = new Intent(this, RegisterSuccessActivity.class );
        startActivity(i);
    }

}
