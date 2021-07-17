package com.example.biciclik.TakeBici;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.biciclik.Login.LoginActivities;
import com.example.biciclik.R;
import com.example.biciclik.Register.Register1Activity;
import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.utils.BikeTestActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TakeBici2Fragment extends Fragment implements TakeBiciInterfaces.fragment {
    SingleSpinnerSearch singleSpinnerSearch;
    private static final String TAG = "TakeBici2";
    TextView txtPuntoIR;
    TextView txtHoraIR;
    TextView txtTiempoR;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.take_bici2, container, false);
        initObjects(view);
        Bundle bundle=this.getArguments();
        String point=bundle.getString("start_point");
        String date=bundle.getString("start_date");
        setData(point, date);
        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.planets_array));
        final List<KeyPairBoolData> listArray0 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list.get(i));
            h.setSelected(false);
            listArray0.add(h);
        }
        singleSpinnerSearch = view.findViewById(R.id.singleItemSelectionSpinner);
        singleSpinnerSearch.setSearchEnabled(true);
        singleSpinnerSearch.setSearchHint("Selecciona la ubicacion");
        singleSpinnerSearch.setItems(listArray0, new SingleSpinnerListener() {
            @Override
            public void onItemsSelected(KeyPairBoolData selectedItem) {
                Log.i(TAG, "Selected Item : " + selectedItem.getName());
            }
            @Override
            public void onClear() {
                Toast.makeText(getContext(), "Limpiar item", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void initObjects(View view){
        txtPuntoIR = (TextView) view.findViewById(R.id.txtPuntoInicio);
        txtHoraIR = (TextView) view.findViewById(R.id.txtHoraInicio);
        txtTiempoR = (TextView)view.findViewById(R.id.txtTiempoTrans);
    }

    @Override
    public void setData(String point, String date) {
//        Chronometer cronometro = new Chronometer();
//        cronometro.setBase(SystemClock.elapsedRealtime());
//        cronometro.start();
//        String registroActual=cronometro.getText().toString();
        txtPuntoIR.setText(point);
        txtHoraIR.setText(date);
        txtTiempoR.setText("registroActual");
    }

    @Override
    public void lanzarLogin() {
        Toast.makeText(BaseContext.getContext(), getString(R.string.expiroToken), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
        startActivity(i);
    }
}
