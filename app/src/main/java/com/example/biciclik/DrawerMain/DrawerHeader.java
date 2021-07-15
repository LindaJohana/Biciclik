package com.example.biciclik.DrawerMain;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biciclik.R;

public class DrawerHeader extends AppCompatActivity implements DrawerInterfaces.activitiesHeader{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_header);
    }
}
