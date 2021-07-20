package com.example.biciclik.TakeBici;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.SingleSpinnerListener;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.Home.HomeActivity;
import com.example.biciclik.Login.LoginActivities;
import com.example.biciclik.R;
import com.example.biciclik.Register.Register1Activity;
import com.example.biciclik.Trip.TripActivity;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.CreateTripData;
import com.example.biciclik.objects.PatchTrip;
import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.utils.BikeTestActivity;
import com.example.biciclik.utils.KeyPairBoolDataCustom;
import com.example.biciclik.utils.SpinnerCustom;
import com.example.biciclik.utils.SpinnerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TakeBici2Fragment extends Fragment implements TakeBiciInterfaces.fragment {
    SpinnerCustom singleSpinnerSearch;
    private static final String TAG = "TakeBici2";
    TextView txtPuntoIR;
    TextView txtHoraIR;
    TextView destinoT;
    Chronometer txtTiempoR;
    TakeBiciPresenters presenters;
    private String pointName;
    Button buttonOkV;
    LocalData localData;
    private Long chronStateSave;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    long pauseoffset;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.take_bici2, container, false);
        initObjects(view);
        Bundle bundle=this.getArguments();
        String point=bundle.getString("start_point");
        String date=bundle.getString("start_date");
        String chrono=bundle.getString("chronometer");
        if (chrono.equals("")){
            Log.e("ONCREATE IF TAKE2", localData.getRegister("CHRONOMETER_S"));
            setData(point, date, "");
        }else {
            Log.e("ONCREATE else TAKE2", localData.getRegister("CHRONOMETER_S"));
            setData(point, date, chrono);
        }
        singleSpinnerSearch = (SpinnerCustom) view.findViewById(R.id.singleItemSelectionSpinner);
        presenters.getDeliveryPoint();
//        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.planets_array));
//        final List<KeyPairBoolData> listArray0 = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            KeyPairBoolData h = new KeyPairBoolData();
//            h.setId(i + 1);
//            h.setName(list.get(i));
//            h.setSelected(false);
//            listArray0.add(h);
//        }
//        singleSpinnerSearch.setSearchEnabled(true);
//        singleSpinnerSearch.setSearchHint("Selecciona la ubicacion");
//        singleSpinnerSearch.setItems(listArray0, new SingleSpinnerListener() {
//            @Override
//            public void onItemsSelected(KeyPairBoolData selectedItem) {
//                Log.i(TAG, "Selected Item : " + selectedItem.getName());
//            }
//            @Override
//            public void onClear() {
//                Toast.makeText(getContext(), "Limpiar item", Toast.LENGTH_SHORT).show();
//            }
//        });
        buttonOkV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTiempoR.stop();
                pauseoffset = SystemClock.elapsedRealtime() - txtTiempoR.getBase();
                localData.register(txtTiempoR.getText().toString(),"CHRONOMETER");
//                chronStateSave= SystemClock.elapsedRealtime();
//                localData.register(chronStateSave.toString(), "CHRONOMETER_S");
                localData.register(String.valueOf(pauseoffset), "CHRONOMETER_S");
//                Log.e("LOCALDATA", localData.getRegister("CHRONOMETER"));
                Log.e("PAUSEOFFF", String.valueOf(pauseoffset));
                localData.register(destinoT.getText().toString(), "DESTINO_TXT");
                PatchTrip data=new PatchTrip(String.valueOf(pauseoffset), destinoT.getText().toString(), pointName);
                presenters.setTripPresenter(data);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,new TripActivity());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    private void initObjects(View view){
        txtPuntoIR = (TextView) view.findViewById(R.id.txtPuntoInicio);
        txtHoraIR = (TextView) view.findViewById(R.id.txtHoraInicio);
        txtTiempoR = (Chronometer) view.findViewById(R.id.txtTiempoTrans);
        destinoT = (TextView) view.findViewById(R.id.destinoT);
        presenters=new TakeBiciPresenters(null, this);
        pointName="";
        buttonOkV = view.findViewById(R.id.ButtonOkV);
        localData = new LocalData();
        chronStateSave = 0L;
        pauseoffset = 0L;
    }

    @Override
    public void setData(String point, String date, String time) {
        txtPuntoIR.setText(point);
        txtHoraIR.setText(date);
        if (time.equals("")){
            Log.e("IF CHRONOMETER2", time);
//            long systemCurrTime = SystemClock.elapsedRealtime();
            txtTiempoR.setBase(SystemClock.elapsedRealtime()-pauseoffset);
            txtTiempoR.start();
        }else {
            Log.e("ELSE CHRONOMETER2", time);
            long intervalOnPause = (SystemClock.elapsedRealtime() - Long.parseLong(time));
//            txtTiempoR.setBase( txtTiempoR.getBase() + intervalOnPause );
            txtTiempoR.setBase(SystemClock.elapsedRealtime() - Long.parseLong(time));
            txtTiempoR.start();
        }
    }

    @Override
    public void lanzarLogin() {
        Toast.makeText(BaseContext.getContext(), getString(R.string.expiroToken), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
        startActivity(i);
    }

    @Override
    public void showpoint(List<KeyPairBoolDataCustom> names) {
        singleSpinnerSearch.setSearchEnabled(true);
        singleSpinnerSearch.setSearchHint("Selecciona la ubicacion");
        singleSpinnerSearch.setItems(names, new SpinnerListener() {

            @Override
            public void onItemsSelected(KeyPairBoolDataCustom selectedItem) {
                pointName=selectedItem.getId();
            }
            @Override
            public void onClear() {

            }
        });
    }

    @Override
    public void setErrorSetTrip(String message) {
        Toast.makeText(BaseContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
