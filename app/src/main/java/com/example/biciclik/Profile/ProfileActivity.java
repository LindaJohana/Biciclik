package com.example.biciclik.Profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;

import java.io.File;

public class ProfileActivity extends Fragment {
    private final String CARPETA_RAIZ="FotosBiciclick/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";
    String path="";
    ImageView FotoPerfil;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
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
        final AlertDialog.Builder alertOptiones=new AlertDialog.Builder(BaseContext.getContext());
        alertOptiones.setTitle("seleccione una opción");
        alertOptiones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (opciones[i].equals("Tomar foto")){
//                    tomarFoto();
                    System.out.println("1");
                }else {
                    if (opciones[i].equals("Cargar foto")){
//                        subirFoto();
                        System.out.println("1");
                    }else {
                        dialog.dismiss();
                    }
                }
            }
        });
    }
    /*private void tomarFoto(){
        File fileImage=new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean creada=fileImage.exists();
        String nameImage="";
        if(creada==false){
            creada=fileImage.mkdirs();
        }if(creada==true){
            nameImage=(System.currentTimeMillis()/100)+"jpg";
        }
        path=Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nameImage;
        File  imagen=new File(path);
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        getActivity().startActivityForResult(i, REQUEST_IMAGE_CAPTURE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK){
            Uri path=data.getData();
            imagen.setImageURI(path);
        }
    }*/
    /*static final int REQUEST_IMAGE_CAPTURE = 1;
    private void tomarFoto(){
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getActivity().getPackageManager())!=null){
            startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
        }
    }*/
    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            FotoPerfil.setImageBitmap(imageBitmap);
        }
    }*/
}
