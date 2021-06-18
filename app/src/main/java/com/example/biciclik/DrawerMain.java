package com.example.biciclik;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.biciclik.Home.HomeActivity;
import com.example.biciclik.Maps.Map1Activity;
import com.example.biciclik.Profile.ProfileActivity;
import com.example.biciclik.TakeBici.TakeBiciActivity;
import com.google.android.material.navigation.NavigationView;

public class DrawerMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    ImageView ImageClose;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        ImageClose=findViewById(R.id.imageClose);


        //onlcick navgation
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new HomeActivity());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.inicio){
            menuItem.setChecked(true);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new HomeActivity());
            fragmentTransaction.commit();
        }if(menuItem.getItemId()==R.id.perfil){
            menuItem.setChecked(true);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new ProfileActivity());
            fragmentTransaction.commit();
        }if(menuItem.getItemId()==R.id.bici){
            menuItem.setChecked(true);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new TakeBiciActivity());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.mapa){
            menuItem.setChecked(true);
            closeOptionsMenu();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new Map1Activity());
            fragmentTransaction.commit();
        }
        return false;
    }
}
