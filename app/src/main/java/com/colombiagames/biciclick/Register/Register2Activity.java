package com.colombiagames.biciclick.Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.colombiagames.biciclick.BaseContext.BaseContext;
import com.colombiagames.biciclick.DrawerMain.DrawerActivities;
import com.colombiagames.biciclick.Login.LoginActivities;
import com.colombiagames.biciclick.R;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.Register2Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register2Activity extends Activity implements RegisterInterfaces.activities2{
    TextView TextSelfie, TextCedula, TextViewRegistro, txt33, txt34, txt35, txt36;
    ImageView Imageselfie, Imagecedulafront, Imagencedulaback;
    Button ButtonContinuar;
    TextView Terminos;
    CheckBox checkbox;
    public final int REQUEST_PHOTO1 = 44;
    public final int REQUEST_PHOTO2 = 55;
    public final int REQUEST_PHOTO3 = 66;
    int buttonCapture;
    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA"};
    RegisterPresenters presenter;
    Register2Data register2Data;
    String currentPhotoPath,UrlSelfie,UrlFront,UrlBack;
    File sel=null;
    LocalData localData;
    BaseContext baseContext;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);
        initObjects();
        //Letra verdana
        Typeface fuente = Typeface.createFromAsset(getAssets(),"fonts/verdana.ttf");
        txt33.setTypeface(fuente);
        txt34.setTypeface(fuente);
        txt35.setTypeface(fuente);
        TextSelfie.setTypeface(fuente);
        TextCedula.setTypeface(fuente);
        Terminos.setTypeface(fuente);
        txt36.setTypeface(fuente);
        TextViewRegistro.setTypeface(fuente);

        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        SpannableString texto= new SpannableString("Acepto los t??rminos y condiciones y la pol??tica de privacidad.");
        ButtonContinuar=findViewById(R.id.buttonContinuar);
        ButtonContinuar.setTypeface(fuente);

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
                if (UrlSelfie.isEmpty()){
                    Toast.makeText(getBaseContext(),"Agregar Selfie",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (UrlFront.isEmpty()){
                    Toast.makeText(getBaseContext(),"Agregar foto frontal de la c??dula",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (UrlBack.isEmpty()){
                    Toast.makeText(getBaseContext(),"Agregar foto del respaldo de la c??dula",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!checkbox.isChecked()){
                    Toast.makeText(getBaseContext(),"Aceptar t??rminos y condiciones",Toast.LENGTH_SHORT).show();
                    return;
                }
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
        texto.setSpan(new URLSpan(getString(R.string.terminos)), 11, 33,0);
        texto.setSpan(new ForegroundColorSpan(Color.parseColor("#E74C3C")),11,33,0);
        texto.setSpan(new URLSpan(getString(R.string.cond)), 39, 62,0);
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
        presenter=new RegisterPresenters(null, this, null, null);
        UrlSelfie="";
        UrlFront="";
        UrlBack="";
        localData=new LocalData();
        if (!localData.getRegister("SELFIE").equals("")){
            File fileSelfie = new File(localData.getRegister("SELFIE"));
            File fileFront = new File(localData.getRegister("DOCUMENT_FRONT_PHOTO"));
            File fileBack = new File(localData.getRegister("DOCUMENT_BACK_PHOTO"));

            String filePath1 = fileSelfie.getPath();
            Bitmap bitmap1 = BitmapFactory.decodeFile(filePath1);
            Imageselfie.setImageBitmap(bitmap1);
            UrlSelfie = fileSelfie.getAbsolutePath();
            String filePath2 = fileFront.getPath();
            Bitmap bitmap2 = BitmapFactory.decodeFile(filePath2);
            Imagecedulafront.setImageBitmap(bitmap2);
            UrlFront = fileFront.getAbsolutePath();
            String filePath3 = fileBack.getPath();
            Bitmap bitmap3 = BitmapFactory.decodeFile(filePath3);
            Imagencedulaback.setImageBitmap(bitmap3);
            UrlBack = fileBack.getAbsolutePath();
        }
        baseContext = new BaseContext();
        checkbox=findViewById(R.id.checkbox);
        txt33=findViewById(R.id.txt33);
        txt34=findViewById(R.id.txt34);
        txt35=findViewById(R.id.txt35);
        txt36=findViewById(R.id.txt36);
    }

    public void cargarImagen(int num){
        buttonCapture=num;
        final CharSequence[] opciones={"Tomar foto","Cancelar"};
        final AlertDialog.Builder alertOptiones=new AlertDialog.Builder(this);
        alertOptiones.setTitle("Seleccione una opci??n");
        alertOptiones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (opciones[i].equals("Tomar foto")){
                    abrircamara(num);
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
                            "com.colombiagames.biciclick.provider",
                            photoFile);
//                    i.putExtra("path_image", photoURI.toString());
                    startActivityForResult(i, REQUEST_PHOTO1);
                    return;
                }
            }
            if (num==2){
                File photoFile = null;
                try {
                    photoFile = createImage();
                } catch (IOException ex) {
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.colombiagames.biciclick.provider",
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
                            "com.colombiagames.biciclick.provider",
                            photoFile);
//                    i.putExtra("path_image", photoURI.toString());
                    startActivityForResult(i, REQUEST_PHOTO3);
                    return;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Imageselfie.setImageBitmap(imageBitmap);
            try (FileOutputStream out = new FileOutputStream(currentPhotoPath)){
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UrlSelfie = currentPhotoPath;
            return;
        }
        if (requestCode == REQUEST_PHOTO2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Imagecedulafront.setImageBitmap(imageBitmap);
            try (FileOutputStream out = new FileOutputStream(currentPhotoPath)){
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UrlFront=currentPhotoPath;
            return;
        }
        if (requestCode == REQUEST_PHOTO3 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Imagencedulaback.setImageBitmap(imageBitmap);
            try (FileOutputStream out = new FileOutputStream(currentPhotoPath)){
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UrlBack=currentPhotoPath;
            return;
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
        currentPhotoPath = image.getPath();
        return image;
    }
    public void lanzarRegistro3(View view){
        Toast.makeText(getBaseContext(), "Usuario Creado Correctamente", Toast.LENGTH_LONG).show();
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
        presenter.register2Presenters(register2Data);
    }
    public void setError(String message) {
        Toast.makeText(getBaseContext(), message, 900-0).show();
        finish();
    }
}
