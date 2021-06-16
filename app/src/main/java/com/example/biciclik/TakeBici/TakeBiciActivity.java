package com.example.biciclik.TakeBici;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class TakeBiciActivity extends Fragment{
    Button ButtonQR;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.take_bici, container, false);
        /*YouTubePlayerView YouTubePlayerView = view.findViewById ( R . id . Youtube_player_view);
        getLifecycle () . addObserver (YouTubePlayerView);*/
        ButtonQR=view.findViewById(R.id.buttonQR);
        ButtonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new IntentIntegrator(getActivity()).initiateScan();
                Toast toast = Toast.makeText(BaseContext.getContext(), "QR", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return view;
    }
/*
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String datos=result.getContents();
        Toast toast = Toast.makeText(BaseContext.getContext(), datos, Toast.LENGTH_SHORT);
        toast.show();
    }*/

}
