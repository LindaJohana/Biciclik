package com.colombiagames.biciclik.Maps;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.colombiagames.biciclik.R;
import com.colombiagames.biciclik.objects.PointData;
import com.colombiagames.biciclik.utils.GpsTracker;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.ViewHolder> {
    private ArrayList<PointData> list;
    LayoutInflater inflater;
    Context my_context;
    private GpsTracker gpsTracker;
    Location origen;
    private double latorigen, lonorigen;

    public MapsAdapter(Context context, ArrayList<PointData> list) {
        this.inflater=LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.inicio_adapter,parent, false);
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.map1_adapter, null,false);
        ViewHolder viewHolders = new ViewHolder(view);
        my_context = parent.getContext();
        getLocation();
        return viewHolders;
    }

    public void getLocation(){
        gpsTracker = new GpsTracker(my_context);
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
    public void onBindViewHolder(@NonNull MapsAdapter.ViewHolder holder, int position) {
        PointData puntos=list.get(position);
        holder.TxtPuntoM.setText(puntos.getName());
        String[] parts = puntos.getLocation().split(",");
        String lat = parts[0];
        String lon = parts[1];
        Location location = new Location("GPS_PROVIDER");
        location.setLatitude(Double.parseDouble(lat));
        location.setLongitude(Double.parseDouble(lon));
        Location origen = new Location("GPS_PROVIDER");
        origen.setLatitude(latorigen);
        origen.setLongitude(lonorigen);
        DecimalFormat formato = new DecimalFormat("#.00");
        double distance=origen.distanceTo(location);
        holder.TxtPuntoIM.setText(formato.format(distance/1000)+" KM");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView TxtPuntoM, TxtPuntoIM;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            TxtPuntoM=itemView.findViewById(R.id.txtPuntoM);
            TxtPuntoIM=itemView.findViewById(R.id.txtPuntoIM);
        }
    }
}
