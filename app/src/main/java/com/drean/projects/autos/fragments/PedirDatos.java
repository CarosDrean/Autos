package com.drean.projects.autos.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.drean.projects.autos.MainActivity;
import com.drean.projects.autos.R;
import com.drean.projects.autos.pojo.Pedido;
import com.drean.projects.autos.presenter.Auxiliar;
import com.drean.projects.autos.presenter.RegistroPedidos;
import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedirDatos extends BottomSheetDialogFragment {

    private boolean habilitar = false;
    private boolean actualizar = false;

    EditText nombre;
    EditText dni;
    EditText celular;
    EditText email;
    TextView cantidad;
    TextView igv;
    TextView precio;
    TextView total;
    Button mas;
    Button menos;
    FloatingActionButton fab;


    public PedirDatos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pedir_datos, container, false);
        Toolbar toolbar = v.findViewById(R.id.toolbar_datos);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Pedido: " + getArguments().getString("auto"));
        inicializar(v);
        reemplazar(v);
        if (getArguments().getString("nombre") != null) {
            recibirData();
        }
        clicks();
        return v;
    }

    public void irInicio() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.putExtra("desde", "pedir");
        startActivity(i);
    }

    private void clicks(){
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cant = cantidad.getText().toString();
                if(Integer.parseInt(cant) > 1) {
                    cantidad.setText("" + (Integer.parseInt(cant) - 1));
                    calcular();
                }
            }
        });

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cant = cantidad.getText().toString();
                cantidad.setText("" + (Integer.parseInt(cant) + 1));
                habilitar = true;
                calcular();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(habilitar) {
                    if(!actualizar) {
                        createPedido();
                    } else {
                        updatePedido();
                    }
                    irInicio();
                } else {
                    Toast.makeText(getActivity(), "¡Asigne una cantidad diferente a 0!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Pedido pedido() {
        Auxiliar aux = new Auxiliar();
        return new Pedido(
                (int) System.currentTimeMillis(),
                getArguments().getString("auto"),
                Integer.toString(getArguments().getInt("portada", R.drawable.auto)),
                nombre.getText().toString(),
                Integer.parseInt(dni.getText().toString()),
                Integer.parseInt(celular.getText().toString()),
                email.getText().toString(),
                Integer.parseInt(cantidad.getText().toString()),
                Double.parseDouble(precio.getText().toString()),
                Double.parseDouble(igv.getText().toString()),
                aux.obtenerFecha(),
                Double.parseDouble(total.getText().toString())
        );
    }

    private void createPedido(){
        RegistroPedidos registro = new RegistroPedidos(getActivity());
        registro.createPedido(pedido());
    }

    private void updatePedido() {
        Pedido pedido = pedido();
        pedido.setId(getArguments().getInt("id", 0));
        RegistroPedidos registro = new RegistroPedidos(getActivity());
        registro.updatePedido(pedido);
    }

    public void calcular() {
        double precio = getArguments().getDouble("precio", 00.00);

        String cant = cantidad.getText().toString();
        int cantidad = Integer.parseInt(cant);
        double subtotal = precio * cantidad;
        double igv = subtotal * 0.18;
        double total = (precio * cantidad) + igv;

        this.igv.setText("" + igv);
        this.total.setText("" + total);
    }

    public void inicializar(View v) {
        nombre = v.findViewById(R.id.edt_nombre);
        dni = v.findViewById(R.id.edt_dni);
        celular = v.findViewById(R.id.edt_celular);
        email = v.findViewById(R.id.edt_email);
        igv = v.findViewById(R.id.tv_igv);
        precio = v.findViewById(R.id.tv_precio);
        total = v.findViewById(R.id.tv_total);
        mas = v.findViewById(R.id.btn_mas);
        menos = v.findViewById(R.id.btn_menos);
        cantidad = v.findViewById(R.id.cantidad_c);
        fab = v.findViewById(R.id.fab_pedir);
    }

    public void recibirData() {
        actualizar = true;
        nombre.setText(getArguments().getString("nombre"));
        dni.setText("" + getArguments().getInt("dni", 0));
        celular.setText("" + getArguments().getInt("celular", 0));
        email.setText(getArguments().getString("email"));
        cantidad.setText("" + getArguments().getInt("cantidad", 0));
        igv.setText("" + getArguments().getDouble("igv", 0.0));
        precio.setText("" + getArguments().getDouble("precio", 0.0));
        total.setText("" + getArguments().getDouble("total", 0.0));
    }

    public void reemplazar(View v) {
        TextView precio = v.findViewById(R.id.tv_precio);
        precio.setText("" + getArguments().getDouble("precio", 00.00));
        CircleImageView portada = v.findViewById(R.id.iv_portada);
        Glide.with(this).load(getArguments().getInt("portada", R.drawable.auto)).into(portada);
    }

}
