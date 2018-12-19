package com.drean.projects.autos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drean.projects.autos.R;
import com.drean.projects.autos.adapters.AdapterAuto;
import com.drean.projects.autos.adapters.AdapterPedido;
import com.drean.projects.autos.pojo.Auto;
import com.drean.projects.autos.pojo.Pedido;
import com.drean.projects.autos.presenter.RegistroPedidos;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pedidos extends Fragment {

    ArrayList<Pedido> pedidos;
    private RecyclerView listapedidos;


    public Pedidos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pedidos, container, false);
        listapedidos = v.findViewById(R.id.lista_pedidos);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listapedidos.setLayoutManager(llm);

        inicializar();
        inicializarAdaptador();
        return v;
    }

    public void inicializarAdaptador(){
        AdapterPedido adaptadorPedido = new AdapterPedido(pedidos, getActivity());
        listapedidos.setAdapter(adaptadorPedido);
    }

    public void inicializar(){
        pedidos = new ArrayList<>();
        RegistroPedidos registro = new RegistroPedidos(getActivity());
        pedidos = registro.getPedidos();
    }

}
