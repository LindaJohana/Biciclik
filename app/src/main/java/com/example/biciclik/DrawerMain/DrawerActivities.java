package com.example.biciclik.DrawerMain;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.biciclik.Home.HomeActivity;
import com.example.biciclik.Login.LoginActivities;
import com.example.biciclik.Maps.MapsActivity;
import com.example.biciclik.Profile.ProfileActivity;
import com.example.biciclik.R;
import com.example.biciclik.TakeBici.TakeBiciActivity;
import com.google.android.material.navigation.NavigationView;

public class DrawerActivities extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerInterfaces.activities {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MenuItem mPreviousMenuItem;
    DrawerPresenters presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);
        initObjects();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

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
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
        if (mPreviousMenuItem != null) {
            mPreviousMenuItem.setChecked(false);
        }
        mPreviousMenuItem = menuItem;
        if(menuItem.getItemId()==R.id.inicio){
            //menuItem.setChecked(true);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new HomeActivity());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.perfil){
            //menuItem.setChecked(true);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new ProfileActivity());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.bici){
            //menuItem.setChecked(true);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new TakeBiciActivity());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.mapa){
            //menuItem.setChecked(true);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new MapsActivity());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.cerrar){
            logOut();
            Intent i = new Intent(this, LoginActivities.class );
            startActivity(i);
        }
        drawerLayout.closeDrawers();
        return false;
    }
    private void initObjects(){
        presenter=new DrawerPresenters(this);
    }

    @Override
    public void logOut() {
        presenter.logOutPresenters();
    }
}
