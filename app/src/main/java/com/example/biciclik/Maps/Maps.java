package com.example.biciclik.Maps;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.biciclik.objects.PointData;
import com.example.biciclik.utils.GpsTracker;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.biciclik.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class Maps extends SupportMapFragment implements OnMapReadyCallback {

    private ActivityMapsBinding binding;
    private List<PointData> point;
    private List<String> locations;
    private GpsTracker gpsTracker;
    private double latorigen, lonorigen;

    public Maps(List<PointData> point) {
        this.point = point;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        getLocation();
        getMapAsync(this);
        return rootView;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
//    public void puntos(ArrayList<PointData> puntos){
//        locations=new ArrayList<>();
//        for (int i=0;i< puntos.size();i++){
//            locations.add(puntos.get(i).getLocation());
//            Log.e("FOR", locations.get(i));
//        }
//    }
    public void getLocation(){
        gpsTracker = new GpsTracker(getContext());
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            latorigen = latitude;
            lonorigen = longitude;
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        locations=new ArrayList<>();
        for (int i=0;i< point.size();i++){
            locations.add(point.get(i).getLocation());
            Log.e("FOR", locations.get(i));
        }
        Location origen = new Location("GPS_PROVIDER");
        origen.setLatitude(latorigen);
        origen.setLongitude(lonorigen);
        LatLng x=new LatLng(origen.getLatitude(), origen.getLongitude());
        float zoom = 15;
//        Zoom en el mapa
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(x, zoom));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(x, zoom));
        for (int i=0;i< locations.size();i++){
            String[] parts = locations.get(i).split(",");
            String lat = parts[0];
            String lon = parts[1];
            LatLng latLon = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));
            map.addMarker(new MarkerOptions().position(latLon));
        }
        LocationServices.getFusedLocationProviderClient(getContext()).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                map.setMyLocationEnabled(true);
            }
        });
    }
    /*public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
}