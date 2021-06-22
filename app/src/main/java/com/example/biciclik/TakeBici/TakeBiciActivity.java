package com.example.biciclik.TakeBici;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.biciclik.R;

public class TakeBiciActivity extends Fragment{
    private static final String TAG = "destino";
    Button ButtonQR;
    FragmentTransaction transaction;
    Fragment fragmentTrip1;

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
                mostrarFragmentT();
            }
        });

        return view;
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
