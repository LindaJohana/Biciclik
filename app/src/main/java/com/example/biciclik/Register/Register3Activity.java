package com.example.biciclik.Register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.biciclik.R;
import com.example.biciclik.RegisterSuccess.RegisterSuccessActivity;

public class Register3Activity extends Activity {
    EditText D1, D2, D3, D4;
    TextView Resul;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register3);
        D1=findViewById(R.id.digito1);
        D2=findViewById(R.id.digito2);
        D3=findViewById(R.id.digito3);
        D4=findViewById(R.id.digito4);
        D4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Verificar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void Verificar(){
        String texto;
        texto=D1.getText().toString()+D2.getText().toString()+D3.getText().toString()+D4.getText().toString();
        String t="1234";
        if (texto.equals(t)){
            lanzarExitoso(null);
        }
    }
    public void lanzarExitoso(View view){
        Intent i = new Intent(this, RegisterSuccessActivity.class );
        startActivity(i);
    }
}
