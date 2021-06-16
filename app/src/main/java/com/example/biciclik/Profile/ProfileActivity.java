package com.example.biciclik.Profile;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;


import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends Fragment {
    CircleImageView FotoPerfil;
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
        final AlertDialog.Builder alertOptiones=new AlertDialog.Builder(getContext());
        alertOptiones.setTitle("seleccione una opción");
        alertOptiones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (opciones[i].equals("Tomar foto")){
//                    tomarFoto();
                    Toast toast = Toast.makeText(BaseContext.getContext(), "tomarfoto", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    if (opciones[i].equals("Cargar foto")){
//                        subirFoto();
                        Toast toast = Toast.makeText(BaseContext.getContext(), "cargarfoto", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        dialog.dismiss();
                    }
                }
            }
        });
        alertOptiones.show();
    }
}
