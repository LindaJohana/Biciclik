package com.example.biciclik.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.example.biciclik.objects.PersonResponse;
import com.example.biciclik.objects.ResultsResponse;
import com.example.biciclik.objects.TravelTopData;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private ArrayList<TravelTopData> list;
    LayoutInflater inflater;
    Context my_context;

    public HomeAdapter(Context context, ArrayList<TravelTopData> results){
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
        TravelTopData person = list.get(position);
        Picasso.with(BaseContext.getContext()).load(person.getUser__selfie()).into(holder.imageSelfieT);
        holder.TxtNombre.setText(person.getUser__user__first_name() + " " + person.getUser__user__last_name());
        holder.TxtViajes.setText(person.getTrips()+" Viajes");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView TxtNombre, TxtViajes;
        CircleImageView imageSelfieT;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            TxtNombre=itemView.findViewById(R.id.txtNombre);
            TxtViajes=itemView.findViewById(R.id.txtViajes);
            imageSelfieT=itemView.findViewById(R.id.imageSelfieT);
        }
    }
}
