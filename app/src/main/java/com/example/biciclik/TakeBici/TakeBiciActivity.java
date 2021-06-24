package com.example.biciclik.TakeBici;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.google.zxing.qrcode.encoder.QRCode;
import com.omni.support.ble.BleModuleHelper;
import com.omni.support.ble.core.ISessionCall;
import com.omni.support.ble.core.SessionCallback;
import com.omni.support.ble.rover.CommandManager;
import com.omni.support.ble.session.sub.Bike3In1Session;


public class TakeBiciActivity extends Fragment implements TakeBiciInterfaces.activities{

    private static final String TAG = "destino";
    Button ButtonQR;
    FragmentTransaction transaction;
    Fragment fragmentTrip1;
    private static final int REQUEST_CODE_QR=20;
    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA","android.permission.BLUETOOTH","android.permission.BLUETOOTH_ADMIN"};
    private Bike3In1Session session;


    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.take_bici, container, false);
        initObjects(view);
        int requestCode = 200;
        BleModuleHelper.INSTANCE.init(this.getActivity().getApplication());//java
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        fragmentTrip1=new TakeBici2Fragment();
        getChildFragmentManager().beginTransaction().commit();
        session = new Bike3In1Session.Builder()
                .address("88:BF:E4:BC:6C:D8")//Replace your mac address
                .deviceKey("yOTmK50z")
                .deviceType("A1")
                .updateKey("Vgz7")
                .build();
        ButtonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerQR();

            }
        });







        return view;
    }

    public void initObjects(View view){
        ButtonQR=view.findViewById(R.id.buttonQR);

    }

    public void leerQR(){
        /*Intent i=new Intent(getContext(), QrCodeActivity.class);
        startActivityForResult(i, REQUEST_CODE_QR);*/

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode!= Activity.RESULT_OK){
            Toast.makeText(getContext(), "No se pudo obtener una respuesta", Toast.LENGTH_SHORT).show();
            if (data==null){

            }else {
                String resultado = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
                if (resultado != null) {
                    Toast.makeText(getContext(), "No se pudo escanear el código QR", Toast.LENGTH_SHORT).show();
                }
                return;
            }

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
