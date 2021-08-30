package com.colombiagames.biciclick.TakeBici;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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

import com.colombiagames.biciclick.BaseContext.BaseContext;
import com.colombiagames.biciclick.Login.LoginActivities;
import com.colombiagames.biciclick.R;
import com.colombiagames.biciclick.Trip.TripActivity;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.PatchTrip;
import com.colombiagames.biciclick.utils.KeyPairBoolDataCustom;
import com.colombiagames.biciclick.utils.SpinnerCustom;
import com.colombiagames.biciclick.utils.SpinnerListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TakeBici2Fragment extends Fragment implements TakeBiciInterfaces.fragment {
    SpinnerCustom singleSpinnerSearch;
    private static final String TAG = "TakeBici2";
    TextView txtPuntoIR;
    TextView txtHoraIR;
    TextView destinoT;
    Chronometer txtTiempoR;
    TakeBiciPresenters presenters;
    private String pointName;
    private String pointName2;
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
        presenters.getDeliveryPoint();
        singleSpinnerSearch = (SpinnerCustom) view.findViewById(R.id.singleItemSelectionSpinner);
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
                if(destinoT.getText().toString().isEmpty()){
                    destinoT.setError("Campo Vacio");
                    destinoT.requestFocus();
                    return;
                }
                if(pointName.isEmpty()){
                    Toast.makeText(getContext(), "Punto de Entrega Vacio", Toast.LENGTH_SHORT).show();
                    singleSpinnerSearch.requestFocus();
                    return;
                }
                txtTiempoR.stop();
                pauseoffset = SystemClock.elapsedRealtime() - txtTiempoR.getBase();
                localData.register(txtTiempoR.getText().toString(),"CHRONOMETER");
//                chronStateSave= SystemClock.elapsedRealtime();
//                localData.register(chronStateSave.toString(), "CHRONOMETER_S");
                localData.register(String.valueOf(pauseoffset), "CHRONOMETER_S");
//                Log.e("LOCALDATA", localData.getRegister("CHRONOMETER"));
                Log.e("PAUSEOFFF", String.valueOf(pauseoffset));
                localData.register(destinoT.getText().toString(), "DESTINO_TXT");
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                String dateString = formatter.format(new  Date (pauseoffset));
//                String dateString = formatter.format(new Date(pauseoffset));
//                String dateString=String.format("%1$tH:%1$tM:%1$tS.%1$tL", pauseoffset);
                PatchTrip data=new PatchTrip(dateString, destinoT.getText().toString(), pointName);
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
            destinoT.setText(localData.getRegister("DESTINO_TXT"));
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
                pointName2=selectedItem.getName();
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
