package com.example.biciclik.RegisterSuccess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.DrawerMain.DrawerActivities;
import com.example.biciclik.DrawerMain.DrawerPresenters;
import com.example.biciclik.ForgetPassword.ForgetPasswordActivity;
import com.example.biciclik.Home.HomeActivity;
import com.example.biciclik.R;
import com.example.biciclik.utils.BikeTestActivity;

public class RegisterSuccessActivity extends Activity implements RegisterSuccessInterfaces.activities {
    Button buttonEstoy, buttonVerPuntos;
    String bike=null;
    String point=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success);
        initObjects();
        buttonEstoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bike();
            }
        });
        buttonVerPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points();
            }
        });
    }
    private void initObjects(){
        buttonEstoy=findViewById(R.id.buttonEstoy);
        buttonVerPuntos=findViewById(R.id.buttonVerPuntos);
    }
    public void Bike() {
        Intent i = new Intent(BaseContext.getContext(), DrawerActivities.class );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("bike",bike);
        startActivity(i);
    }
    public void points(){
        Intent i = new Intent(BaseContext.getContext(), DrawerActivities.class );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("points",point);
        startActivity(i);
    }
}
