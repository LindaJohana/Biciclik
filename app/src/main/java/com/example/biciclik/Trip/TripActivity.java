package com.example.biciclik.Trip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.DrawerMain.DrawerActivities;
import com.example.biciclik.Login.LoginActivities;
import com.example.biciclik.R;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.objects.TripResponseFinal;
import com.example.biciclik.utils.BikeTestActivity;

public class TripActivity extends Fragment implements TripInterfaces.activities {
    Button ButtonOkV, ButtonNo;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TripPresenters presenter;
    LocalData localData;
    String home=null;
    TextView txtPuntoIR, txtHoraIR,txtTiempoR, txtDestonoR, txtPuntoER, txtHoraER, txtAhorroR, txtHuellaR;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trip, container, false);
        initObjects(view);
        Log.e("ONCREATE", "TRIP");
        presenter.getInfoTripPresenter();

        ButtonOkV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendStatusPresenter();
                localData.CreateTrip();
                Intent i = new Intent(BaseContext.getContext(), DrawerActivities.class );
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("home",home);
                startActivity(i);
            }
        });
        ButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,new BikeTestActivity());
//                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    private void initObjects(View view){
        txtPuntoIR = view.findViewById(R.id.txtPuntoIR);
        txtHoraIR = view.findViewById(R.id.txtHoraIR);
        txtTiempoR = view.findViewById(R.id.txtTiempoR);
        txtDestonoR = view.findViewById(R.id.txtDestonoR);
        txtPuntoER = view.findViewById(R.id.txtPuntoER);
        txtHoraER = view.findViewById(R.id.txtHoraER);
        txtHuellaR = view.findViewById(R.id.txtHuellaR);
        txtAhorroR = view.findViewById(R.id.txtAhorroR);
        ButtonOkV=view.findViewById(R.id.ButtonOkV);
        ButtonNo=view.findViewById(R.id.ButtonNo);
        presenter = new TripPresenters(this);
        localData = new LocalData();
    }

    @Override
    public void setTrip(TripResponseFinal data) {
        txtPuntoIR.setText(data.getStart_detail().getName());
        txtHoraIR.setText(data.getStart_date());
        txtTiempoR.setText(data.getTime_elapsed());
        txtDestonoR.setText(data.getDestination());
        txtPuntoER.setText(data.getDelivery_detail().getName());
        txtHoraER.setText(data.getDelivery_date());
        txtHuellaR.setText(String.valueOf(data.getCarbon_footprint()));
        txtAhorroR.setText(String.valueOf(data.getEconomic_savings()));
    }

    @Override
    public void lanzarLogin() {
        Toast.makeText(BaseContext.getContext(), getString(R.string.expiroToken), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
        startActivity(i);
    }
}
