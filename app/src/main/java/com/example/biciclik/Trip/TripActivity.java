package com.example.biciclik.Trip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.biciclik.Home.HomeActivity;
import com.example.biciclik.R;
import com.example.biciclik.utils.BikeTestActivity;

public class TripActivity extends Fragment implements TripInterfaces.activities {
    Button ButtonOkV, ButtonNo;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trip, container, false);
        initObjects(view);
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
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    private void initObjects(View view){
        ButtonOkV=view.findViewById(R.id.ButtonOkV);
        ButtonNo=view.findViewById(R.id.ButtonNo);
    }
}
