package com.example.biciclik.Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.example.biciclik.Login.LoginActivities;
import com.example.biciclik.R;
import com.example.biciclik.objects.Register2Data;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register2Activity extends Activity implements RegisterInterfaces.activities2{
    TextView TextSelfie, TextCedula, TextViewRegistro;
    ImageView Imageselfie, Imagecedulafront, Imagencedulaback;
    Button ButtonContinuar;
    TextView Terminos;
    public final int REQUEST_IMAGE1 = 11;
    public final int REQUEST_IMAGE2 = 22;
    public final int REQUEST_IMAGE3 = 33;
    public final int REQUEST_PHOTO1 = 44;
    public final int REQUEST_PHOTO2 = 55;
    public final int REQUEST_PHOTO3 = 66;
    int buttonCapture;
    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA"};
    RegisterPresenters presenter;
    Register2Data register2Data;
    String currentPhotoPath,UrlSelfie,UrlFront,UrlBack;
    File sel=null;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);
        initObjects();
        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        //Terminos.setMovementMethod(LinkMovementMethod.getInstance());
        SpannableString texto= new SpannableString("Acepto los terminos y condiciones y la Politica de privacidad.");
        ButtonContinuar=findViewById(R.id.buttonContinuar);

        Imageselfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen(1);
            }
        });
        Imagecedulafront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen(2);
            }
        });
        Imagencedulaback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen(3);
            }
        });
        ButtonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register2();
            }
        });
        TextViewRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarLogin(null);
            }
        });
        //url de los terminos y condiciones
        texto.setSpan(new URLSpan("https://programmerclick.com/article/4125913791/"), 11, 33,0);
        texto.setSpan(new ForegroundColorSpan(Color.parseColor("#E74C3C")),11,33,0);
        texto.setSpan(new URLSpan("https://programmerclick.com/article/4125913791/"), 39, 62,0);
        texto.setSpan(new ForegroundColorSpan(Color.parseColor("#E74C3C")),39,62,0);

        Terminos.setMovementMethod(LinkMovementMethod.getInstance());
        Terminos.setText(texto, TextView.BufferType.SPANNABLE);
    }
    public void initObjects(){
        Terminos=findViewById(R.id.terminos);
        TextSelfie=findViewById(R.id.textSelfie);
        TextCedula=findViewById(R.id.textCedula);
        Imageselfie=findViewById(R.id.imageselfie);
        Imagecedulafront=findViewById(R.id.imagecedulafront);
        Imagencedulaback=findViewById(R.id.imagecedulaback);
        TextViewRegistro=findViewById(R.id.textViewRegistro2);
        presenter=new RegisterPresenters(null, this, null);
    }

    public void cargarImagen(int num){
        buttonCapture=num;
        final CharSequence[] opciones={"Tomar foto", "Cargar foto","Cancelar"};
        final AlertDialog.Builder alertOptiones=new AlertDialog.Builder(this);
        alertOptiones.setTitle("seleccione una opción");
        alertOptiones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (opciones[i].equals("Tomar foto")){
                    abrircamara(num);
                    return;
                }
                if (opciones[i].equals("Cargar foto")){
                    subirFoto(num);
                    return;
                }
                if (opciones[i].equals("Cancelar")){
                    dialog.dismiss();
                    return;
                }
            }
        });
        alertOptiones.show();
    }
    private void abrircamara(int num){
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getPackageManager())!=null){
            if (num==1){
                File photoFile = null;
                try {
                    photoFile = createImage();
                    sel=photoFile;
                } catch (IOException ex) {
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.biciclik.provider",
                            photoFile);
//                    i.putExtra("path_image", photoURI.toString());
                    startActivityForResult(i, REQUEST_PHOTO1);
                    return;
                }
                //startActivityForResult(i, REQUEST_PHOTO1);
                //return;
            }
            if (num==2){
                File photoFile = null;
                try {
                    photoFile = createImage();
                } catch (IOException ex) {
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.biciclik.provider",
                            photoFile);
//                    i.putExtra("path_image", photoURI.toString());
                    startActivityForResult(i, REQUEST_PHOTO2);
                    return;
                }
            }
            if (num==3){
                File photoFile = null;
                try {
                    photoFile = createImage();
                } catch (IOException ex) {
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.biciclik.provider",
                            photoFile);
//                    i.putExtra("path_image", photoURI.toString());
                    startActivityForResult(i, REQUEST_PHOTO3);
                    return;
                }
            }
        }
    }
    public void subirFoto(int num){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        if (num==1){
            startActivityForResult(intent.createChooser(intent, "seleccione"), REQUEST_IMAGE1);
            return;
        }
        if (num==2){
            startActivityForResult(intent.createChooser(intent, "seleccione"), REQUEST_IMAGE2);
            return;
        }
        if (num==3){
            startActivityForResult(intent.createChooser(intent, "seleccione"), REQUEST_IMAGE3);
            return;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Imageselfie.setImageBitmap(imageBitmap);
            UrlSelfie = currentPhotoPath;
            return;
        }
        if (requestCode == REQUEST_PHOTO2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Imagecedulafront.setImageBitmap(imageBitmap);
            UrlFront=currentPhotoPath;
            return;
        }
        if (requestCode == REQUEST_PHOTO3 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Imagencedulaback.setImageBitmap(imageBitmap);
            UrlBack=currentPhotoPath;
            return;
        }
        if (requestCode == REQUEST_IMAGE1 && resultCode == RESULT_OK){
            Uri miPath=data.getData();
            Imageselfie.setImageURI(miPath);
        }
        if (requestCode == REQUEST_IMAGE2 && resultCode == RESULT_OK){
            Uri miPath=data.getData();
            Imagecedulafront.setImageURI(miPath);
        }
        if (requestCode == REQUEST_IMAGE3 && resultCode == RESULT_OK){
            Uri miPath=data.getData();
            Imagencedulaback.setImageURI(miPath);
        }
    }


    public File createImage() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    public void lanzarRegistro3(View view){
        Intent i = new Intent(this, Register3Activity.class );
        startActivity(i);
    }
    public void lanzarLogin(View view){
        Intent i = new Intent(this, LoginActivities.class );
        startActivity(i);
    }

    @Override
    public void register2() {
        register2Data=new Register2Data(UrlSelfie, UrlFront, UrlBack);
        Log.e("Register2", "Register2");
        presenter.register2Presenters(register2Data);
    }
    public void setError(String message) {
        Toast.makeText(getBaseContext(), message, 500-0).show();
    }
}
