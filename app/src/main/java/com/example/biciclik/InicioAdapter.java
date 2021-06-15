package com.example.biciclik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biciclik.objects.PersonResponse;


import java.util.ArrayList;

public class InicioAdapter extends RecyclerView.Adapter<InicioAdapter.ViewHolder> {
    private ArrayList<PersonResponse> list;
    LayoutInflater inflater;
    Context my_context;

    public InicioAdapter(Context context, ArrayList<PersonResponse> list){
        this.inflater=LayoutInflater.from(context);
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.inicio_adapter,parent, false);
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.inicio_adapter, null,false);
        ViewHolder viewHolders = new ViewHolder(view);
        my_context = parent.getContext();
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonResponse person = list.get(position);
        holder.TxtNombre.setText(person.getName() + " " + person.getLast_name());
        holder.TxtViajes.setText(person.getViajes()+" Viajes");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView TxtNombre, TxtViajes;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            TxtNombre=itemView.findViewById(R.id.txtNombre);
            TxtViajes=itemView.findViewById(R.id.txtViajes);
        }
    }
}
