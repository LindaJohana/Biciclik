package com.example.biciclik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView TextViewRegistro, TextViewOlvideC;
    public Button ButtonAcceso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextViewRegistro=findViewById(R.id.textViewRegistro);
        TextViewRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarRegistro(null);
            }
        });
        TextViewOlvideC=findViewById(R.id.textViewOlvideC);
        TextViewOlvideC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarOlvideC(null);
            }
        });
        ButtonAcceso=findViewById(R.id.ingresarAcceso);
        ButtonAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarPerfil(null);
            }
        });
    }
    public void lanzarRegistro(View view){
        Intent i = new Intent(this, Registro1.class );
        startActivity(i);
    }
    public void lanzarOlvideC(View view){
        Intent i = new Intent(this, OlvidoContrasenaActivity.class );
        startActivity(i);
    }
    public void lanzarPerfil(View view){
        Intent i = new Intent(this, DrawerMain.class );
        startActivity(i);
    }
}