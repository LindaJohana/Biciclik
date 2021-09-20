package com.colombiagames.biciclick.DrawerMain;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.colombiagames.biciclick.BaseContext.BaseContext;
import com.colombiagames.biciclick.Home.HomeActivity;
import com.colombiagames.biciclick.Login.LoginActivities;
import com.colombiagames.biciclick.Maps.MapsActivity;
import com.colombiagames.biciclick.Profile.ProfileActivity;
import com.colombiagames.biciclick.R;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.ProfileData;
import com.colombiagames.biciclick.utils.BikeTestActivity;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.colombiagames.biciclick.BaseContext.BaseContext.getContext;

public class DrawerActivities extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerInterfaces.activities {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MenuItem mPreviousMenuItem;
    DrawerPresenters presenter;
    CircleImageView drawerSelfie;
    TextView textViewUsuario, textViewEmailD;
    LocalData localData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);
        initObjects();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        LayoutInflater.from(getContext()).inflate(R.layout.drawer_header, navigationView);
        drawerSelfie=findViewById(R.id.drawerSelfie);
        textViewUsuario=findViewById(R.id.textViewUsuarioD);
        textViewEmailD=findViewById(R.id.textViewEmailD);

        //Letra verdana
        Typeface fuente = Typeface.createFromAsset(getAssets(),"fonts/verdana.ttf");
        textViewEmailD.setTypeface(fuente);
        textViewUsuario.setTypeface(fuente);
        //onlcick navgation
        navigationView.setNavigationItemSelectedListener(this);
        presenter.verifiedPresenter();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        //bh
    }

    @Override
    protected void onResume() {
        presenter.profileHeaderPresenter();
        super.onResume();
    }

    private void points() {
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MapsActivity());
        fragmentTransaction.commit();

    }

    public void inicio(){
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new HomeActivity());
        fragmentTransaction.commit();
    }
    public void Bici(){
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new BikeTestActivity());
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
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new HomeActivity());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.perfil){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new ProfileActivity());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.bici){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new BikeTestActivity());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.mapa){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new MapsActivity());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId()==R.id.llamar){
            Uri uri = Uri.parse(getString(R.string.phone)+"&"+getString(R.string.phone2));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        if(menuItem.getItemId()==R.id.cerrar){
            presenter.logoutPushPresenter();
        }
        drawerLayout.closeDrawers();
        return false;
    }
    private void initObjects(){
        presenter=new DrawerPresenters(this);
        localData=new LocalData();
    }

    @Override
    public void logOut() {
        presenter.logOutPresenters();
        Intent i = new Intent(this, LoginActivities.class );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    public void setProfileHeader(ProfileData data) {
        textViewUsuario.setText(data.getUser().getFirst_name()+" "+data.getUser().getLast_name());
        if (data.getSelfie().startsWith("http")){
            Picasso.with(BaseContext.getContext()).load(data.getSelfie()).into(drawerSelfie);
        }else {
            Picasso.with(BaseContext.getContext()).load(getString(R.string.server)+data.getSelfie()).into(drawerSelfie);
        }
        textViewEmailD.setText(data.getUser().getEmail());
    }

    @Override
    public void lanzarLogin() {
        Toast.makeText(BaseContext.getContext(), getString(R.string.expiroToken), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
        startActivity(i);
    }

    @Override
    public void verified(String verified, String active) {
        if (verified.equals("true") && active.equals("true")){
            Bundle extras = getIntent().getExtras();
            if (extras!=null) {
                if (extras.containsKey("bikepush")) {
                    Bici();
                }
                if (extras.containsKey("home")) {
                    inicio();
                }
                if (extras.containsKey("points")){
                    points();
                }
            }
        }else{
            Toast.makeText(getContext(), "Aun no se encuentra verificado", Toast.LENGTH_SHORT).show();
            localData.LogOutApp();
            Log.e("DRAWERACTIVITIES", "noverificado");
            localData.register("", "ID_REGISTER_PUSH");
            Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
            startActivity(i);
        }
    }
}
