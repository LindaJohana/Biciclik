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

import androidx.fragment.app.FragmentTransaction;


import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.omni.support.ble.BleModuleHelper;
import com.omni.support.ble.core.ICommand;
import com.omni.support.ble.core.IResp;
import com.omni.support.ble.core.ISessionCall;
import com.omni.support.ble.core.NotifyCallback;
import com.omni.support.ble.core.SessionCallback;
import com.omni.support.ble.protocol.bike.model.BLLockResult;
import com.omni.support.ble.rover.CommandManager;
import com.omni.support.ble.session.ISessionListener;
import com.omni.support.ble.session.SimpleSessionListener;
import com.omni.support.ble.session.sub.Bike3In1Session;

import org.jetbrains.annotations.NotNull;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
        mv = {1, 5, 1},
        k = 1,
        d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\b"},
        d2 = {"Lcom/example/biciclik/utils/BikeTestActivity;", "", "()V", "session", "Lcom/omni/support/ble/session/sub/Bike3In1Session;", "init", "", "unlock", "Biciclik.app"}
)

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        fragmentTrip1=new TakeBici2Fragment();
        getChildFragmentManager().beginTransaction().commit();
        BleModuleHelper.INSTANCE.init(this.getActivity().getApplication());//java




        this.session = (Bike3In1Session)(new Bike3In1Session.Builder()).address("E3:1D:F1:0F:33:5B").deviceKey("yOTmK50z").deviceType("A1").updateKey("Vgz7").build();
        Bike3In1Session var10000 = this.session;

        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("session");
        }

        var10000.setListener((ISessionListener)(new SimpleSessionListener() {
            public void onConnecting() {
            }

            public void onConnected() {
            }

            public void onDisconnected() {
            }

            public void onDeviceNoSupport() {
            }

            public void onReady() {
                TakeBiciActivity.access$getSession$p(TakeBiciActivity.this).call((ICommand) CommandManager.INSTANCE.getBlCommand().lock()).subscribe((NotifyCallback)(new NotifyCallback() {
                    public void onSuccess(@NotNull ISessionCall call, @NotNull IResp data) {
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(data, "data");
                        BLLockResult result = (BLLockResult)data.getResult();
                        if (result != null) {
                            Log.d("=====", result.toString());
                        }

                        TakeBiciActivity.access$getSession$p(TakeBiciActivity.this).call((ICommand)CommandManager.INSTANCE.getBlCommand().lockReply()).execute();
                    }
                }));
            }
        }));

        ButtonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                leerQR();
                unlock();


            }
        });


        return view;
    }

    public final void unlock() {
        Bike3In1Session var10000 = this.session;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("session");
        }

        var10000.call((ICommand)CommandManager.INSTANCE.getBlCommand().unlock(0, System.currentTimeMillis() / (long)1000, 0)).timeout(3000L).enqueue((SessionCallback)(new SessionCallback() {
            public void onSuccess(@NotNull ISessionCall call, @NotNull IResp data) {
                Boolean var10000 = (Boolean)data.getResult();
                boolean isSuccess = var10000 != null ? var10000 : false;
                Toast.makeText(BaseContext.getContext(), (CharSequence)(isSuccess ? "Successfully unlocked" : "Failed to unlock"), Toast.LENGTH_LONG).show();
                TakeBiciActivity.access$getSession$p(TakeBiciActivity.this).call((ICommand)CommandManager.INSTANCE.getBlCommand().unlockReply()).execute();
            }

            public void onFailure(@NotNull ISessionCall call, @NotNull Throwable e) {
                Toast.makeText(BaseContext.getContext(), "fallo", Toast.LENGTH_LONG).show();
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
            }
        }));
    }

    // $FF: synthetic method
    public static final Bike3In1Session access$getSession$p(TakeBiciActivity $this) {
        Bike3In1Session var10000 = $this.session;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("session");
        }

        return var10000;
    }

    public static final void access$setSession$p(TakeBiciActivity $this, Bike3In1Session var1) {
        $this.session = var1;
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
