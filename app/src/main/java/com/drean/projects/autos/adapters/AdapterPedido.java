package com.drean.projects.autos.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.drean.projects.autos.R;
import com.drean.projects.autos.fragments.PedirDatos;
import com.drean.projects.autos.pojo.Pedido;
import com.drean.projects.autos.presenter.RegistroPedidos;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterPedido extends RecyclerView.Adapter<AdapterPedido.PedidoViewHolder> {

    private ArrayList<Pedido> pedidos;
    private Activity activity;

    public AdapterPedido(ArrayList<Pedido> pedidos, Activity activity) {
        this.pedidos = pedidos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder pedidoViewHolder, final int position) {
        final Pedido pedido = pedidos.get(position);
        pedidoViewHolder.titulo.setText(pedido.getNombre());
        pedidoViewHolder.subTitulo.setText(pedido.getNombreAuto());
        Glide.with(activity).load(Integer.parseInt(pedido.getImgAuto())).into(pedidoViewHolder.perfil);

        pedidoViewHolder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogFragment pedir = new PedirDatos();

                Bundle bundle = new Bundle();
                bundle.putInt("id", pedido.getId());
                bundle.putString("portada", pedido.getImgAuto());
                bundle.putString("auto", pedido.getNombreAuto());
                bundle.putDouble("total", pedido.getTotal());
                bundle.putDouble("precio", pedido.getPrecioUnidad());
                bundle.putString("nombre", pedido.getNombre());
                bundle.putDouble("igv", pedido.getIgv());
                bundle.putString("email", pedido.getEmail());
                bundle.putInt("dni", pedido.getDni());
                bundle.putInt("celular", pedido.getCelular());
                bundle.putInt("cantidad", pedido.getCantidad());

                pedir.setArguments(bundle);

                pedir.show(((FragmentActivity)activity).getSupportFragmentManager(), "Pedir");
            }
        });

        pedidoViewHolder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroPedidos registro = new RegistroPedidos(activity);
                registro.deletePedido(pedido.getId());
                pedidos.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(activity, "¡Pedido Eliminado!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private TextView subTitulo;
        private CircleImageView perfil;
        private ImageView editar;
        private ImageView eliminar;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.title_pedido);
            subTitulo = itemView.findViewById(R.id.descrip_pedido);
            perfil = itemView.findViewById(R.id.ic_pedido);
            editar = itemView.findViewById(R.id.editar);
            eliminar = itemView.findViewById(R.id.eliminar);
        }
    }
}
