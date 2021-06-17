package com.example.biciclik.Maps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biciclik.Home.HomeAdapter;
import com.example.biciclik.R;
import com.example.biciclik.objects.PersonResponse;
import com.example.biciclik.objects.PuntosResponse;

import java.util.ArrayList;

public class Maps1Adapter extends RecyclerView.Adapter<Maps1Adapter.ViewHolder> {
    private ArrayList<PuntosResponse> list;
    LayoutInflater inflater;
    Context my_context;

    public Maps1Adapter(Context context, ArrayList<PuntosResponse> list) {
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
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull Maps1Adapter.ViewHolder holder, int position) {
        PuntosResponse puntos=list.get(position);
        holder.TxtPuntoM.setText(puntos.getPunto());
        holder.TxtPuntoIM.setText(puntos.getDistancia()+" KM");
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
