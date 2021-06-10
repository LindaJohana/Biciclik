package com.example.biciclik;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class RegistroFotos extends Activity {
    TextView TextSelfie, TextCedula;
    ImageView Imageselfie, Imagecedula;
    Button ButtonContinuar;
    TextView Terminos;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_fotos);
        Terminos=findViewById(R.id.terminos);
        //Terminos.setMovementMethod(LinkMovementMethod.getInstance());
        SpannableString texto= new SpannableString("Acepto los terminos y condiciones y la Politica de privacidad.");
        ButtonContinuar=findViewById(R.id.buttonContinuar);
        TextSelfie=findViewById(R.id.textSelfie);
        TextCedula=findViewById(R.id.textCedula);
        Imageselfie=findViewById(R.id.imageselfie);
        Imagecedula=findViewById(R.id.imagecedula);
        TextSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrircamara1();
            }
        });
        TextCedula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrircamara2();
            }
        });
        ButtonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarRegistro3(null);
            }
        });
        //url
        texto.setSpan(new URLSpan("https://programmerclick.com/article/4125913791/"), 11, 33,0);
        texto.setSpan(new ForegroundColorSpan(Color.parseColor("#E74C3C")),11,33,0);
        texto.setSpan(new URLSpan("https://programmerclick.com/article/4125913791/"), 39, 62,0);
        texto.setSpan(new ForegroundColorSpan(Color.parseColor("#E74C3C")),39,62,0);

        Terminos.setMovementMethod(LinkMovementMethod.getInstance());
        Terminos.setText(texto, TextView.BufferType.SPANNABLE);
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_CAPTURE2 = 2;
    private void abrircamara1(){
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getPackageManager())!=null){
            startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
        }
    }
    private void abrircamara2(){
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getPackageManager())!=null){
            startActivityForResult(i, REQUEST_IMAGE_CAPTURE2);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Imageselfie.setImageBitmap(imageBitmap);
        }else if (requestCode == REQUEST_IMAGE_CAPTURE2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Imagecedula.setImageBitmap(imageBitmap);
        }
    }

    public void lanzarRegistro3(View view){
        Intent i = new Intent(this, Registro3.class );
        startActivity(i);
    }
}
