package com.example.biciclik.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biciclik.R;
import com.example.biciclik.objects.PersonResponse;
import com.example.biciclik.objects.ResultsResponse;


import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private ArrayList<ResultsResponse> list;
    LayoutInflater inflater;
    Context my_context;

    public HomeAdapter(Context context, ArrayList<ResultsResponse> results){
        this.inflater=LayoutInflater.from(context);
        this.list = results;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.inicio_adapter,parent, false);
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.home_adapter, null,false);
        ViewHolder viewHolders = new ViewHolder(view);
        my_context = parent.getContext();
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultsResponse person = list.get(position);
        holder.TxtNombre.setText(person.getUser_detail().getUser().getFirst_name() + " " + person.getUser_detail().getUser().getLast_name());
        holder.TxtViajes.setText("5"+" Viajes");
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
