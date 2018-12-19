package com.drean.projects.autos.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.drean.projects.autos.DetalleAuto;
import com.drean.projects.autos.R;
import com.drean.projects.autos.pojo.Auto;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterAuto extends RecyclerView.Adapter<AdapterAuto.AutoViewHolder> {

    private ArrayList<Auto> autos;
    private Activity activity;

    public AdapterAuto(ArrayList<Auto> autos, Activity activity) {
        this.autos = autos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auto, parent, false);
        return new AutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AutoViewHolder autoViewHolder, int position) {
        final Auto autoItem = autos.get(position);
        autoViewHolder.titulo.setText(autoItem.getNombre());
        autoViewHolder.descripcion.setText(autoItem.getDescripcion());
        autoViewHolder.precio.setText("" + autoItem.getPrecio());
        Glide.with(activity).load(autoItem.getImagen()).into(autoViewHolder.ic);

        autoViewHolder.fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, DetalleAuto.class);
                i.putExtra("auto", autoItem.getNombre());
                i.putExtra("descripcion", autoItem.getDescripcion());
                i.putExtra("precio", autoItem.getPrecio());
                i.putExtra("portada", autoItem.getImagen());
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return autos.size();
    }

    public static class AutoViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private TextView descripcion;
        private TextView precio;
        private CircleImageView ic;
        private CardView fondo;

        public AutoViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.title_auto);
            descripcion = itemView.findViewById(R.id.descrip_auto);
            precio = itemView.findViewById(R.id.precio_auto);
            ic = itemView.findViewById(R.id.ic_auto);
            fondo = itemView.findViewById(R.id.card_item);
        }
    }
}
