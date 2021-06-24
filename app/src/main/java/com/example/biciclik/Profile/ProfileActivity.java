package com.example.biciclik.Profile;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class ProfileActivity extends Fragment implements ProfileInterfaces.activities{
    CircleImageView FotoPerfil;
    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA"};
    String carpeta_principal="misImagenesApp";
    String carpeta_imagen="biciclick";
    String directorio_imagen=carpeta_principal+carpeta_imagen;
    String path;
    File fileImage;

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);

        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        FotoPerfil=view.findViewById(R.id.fotoPerfil);
        FotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });
        return view;
    }
    public void cargarImagen(){
        final CharSequence[] opciones={"Tomar foto", "Cargar foto","Cancelar"};
        final AlertDialog.Builder alertOptiones=new AlertDialog.Builder(getContext());
        alertOptiones.setTitle("seleccione una opción");
        alertOptiones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (opciones[i].equals("Tomar foto")){
                    capturePhoto();
                }else {
                    if (opciones[i].equals("Cargar foto")){
                        subirFoto();
                    }else {
                        dialog.dismiss();
                    }
                }
            }
        });
        alertOptiones.show();
    }

    public final int REQUEST_IMAGE_CAPTURE = 1;
    public final int REQUEST_IMAGE = 2;
    public void capturePhoto() {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getActivity().getPackageManager())!=null){
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }
    Bitmap imageBitmap;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            FotoPerfil.setImageBitmap(imageBitmap);
            //saveImage(imageBitmap);
        }
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK){
            Uri miPath=data.getData();
            FotoPerfil.setImageURI(miPath);
        }

    }
    public void subirFoto(){
        Intent intent=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "seleccione"), REQUEST_IMAGE);
    }

}
