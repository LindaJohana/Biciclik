package com.example.biciclik.TakeBici;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.biciclik.R;
import com.google.zxing.qrcode.encoder.QRCode;

public class TakeBiciActivity extends Fragment implements TakeBiciInterfaces.activities{
    private static final String TAG = "destino";
    Button ButtonQR;
    FragmentTransaction transaction;
    Fragment fragmentTrip1;
    private static final int REQUEST_CODE_QR=20;

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.take_bici, container, false);
        /*YouTubePlayerView YouTubePlayerView = view.findViewById ( R . id . Youtube_player_view);
        getLifecycle () . addObserver (YouTubePlayerView);*/
        fragmentTrip1=new TakeBici2Fragment();
        ButtonQR=view.findViewById(R.id.buttonQR);
        getChildFragmentManager().beginTransaction().commit();
        ButtonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerQR();

            }
        });

        return view;
    }
    public void leerQR(){
        Intent i=new Intent(getContext(), QrCodeActivity.class);
        startActivityForResult(i, REQUEST_CODE_QR);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode!= Activity.RESULT_OK){
            Toast.makeText(getContext(), "No se pudo obtener una respuesta", Toast.LENGTH_SHORT).show();
            String resultado = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (resultado != null) {
                Toast.makeText(getContext(), "No se pudo escanear el código QR", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == REQUEST_CODE_QR) {
            if (data != null) {
                String lectura = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
                Toast.makeText(getContext(), "Leído: " + lectura, Toast.LENGTH_SHORT).show();
                mostrarFragmentT();
            }
        }
    }

    public void mostrarFragmentT(){
        try {
            transaction=getChildFragmentManager().beginTransaction();
            transaction.add(R.id.contenedorFragmentTrip, fragmentTrip1);
            transaction.addToBackStack(null);
            transaction.commit();
        }catch (Exception excepcion){
            Log.e(TAG, "error");
        }
    }

}
