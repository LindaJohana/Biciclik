package com.example.biciclik.Trip;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.biciclik.Home.HomeActivity;
import com.example.biciclik.R;
import com.example.biciclik.TakeBici.TakeBici2Fragment;
import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.utils.BikeTestActivity;

public class TripActivity extends Fragment implements TripInterfaces.activities {
    Button ButtonOkV, ButtonNo;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TripPresenters presenter;
    TextView txtPuntoIR, txtHoraIR,txtTiempoR, txtDestonoR, txtPuntoER, txtHoraER, txtAhorroR, txtHuellaR;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trip, container, false);
        initObjects(view);
        Log.e("ONCREATE", "TRIP");
        presenter.getTripPresenter();

        ButtonOkV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    }

    @Override
    public void setTrip(TripResponse data) {
        txtPuntoIR.setText(data.getStart_point().getName());
        txtHoraIR.setText(data.getStart_date());
        txtTiempoR.setText(data.getTime_elapsed());
        txtDestonoR.setText(data.getDestination());
        txtPuntoER.setText(data.getDelivery_point().getName());
        txtHoraER.setText(data.getDelivery_date());
        txtHuellaR.setText(String.valueOf(data.getUser().getCarbon_footprint()));
        txtAhorroR.setText(String.valueOf(data.getUser().getEconomic_savings()));
    }
}
