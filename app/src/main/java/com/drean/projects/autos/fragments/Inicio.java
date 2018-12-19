package com.drean.projects.autos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.drean.projects.autos.R;
import com.drean.projects.autos.adapters.AdapterAuto;
import com.drean.projects.autos.pojo.Auto;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {

    ArrayList<Auto> autos;
    private RecyclerView listaAutos;


    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inicio, container, false);
        setHasOptionsMenu(true);

        Toolbar toolbar = v.findViewById(R.id.toolbar_inicio);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name));

        listaAutos = v.findViewById(R.id.lista_autos);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaAutos.setLayoutManager(llm);

        inicializar();
        inicializarAdaptador();
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_inicio, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint(getText(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Auto> newAutos = new ArrayList<>();
                inicializar();
                for (Auto item: autos) {
                    int i = item.getNombre().toLowerCase().indexOf(query);
                    if(i != -1){
                        newAutos.add(item);
                    }
                }
                autos = newAutos;
                inicializarAdaptador();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                /*ArrayList<Auto> newAutos = new ArrayList<>();
                inicializar();
                for (Auto item: autos) {
                    int i = item.getNombre().toLowerCase().indexOf(newText);
                    if(i != -1){
                        newAutos.add(item);
                    }
                }
                autos = newAutos;
                inicializarAdaptador();*/
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void inicializarAdaptador(){
        AdapterAuto adaptadorAuto = new AdapterAuto(autos, getActivity());
        listaAutos.setAdapter(adaptadorAuto);
    }

    public void inicializar(){
        autos = new ArrayList<>();

        autos.add(new Auto(getString(R.string.auto_uno), R.drawable.autouno, 33000.00, getString(R.string.descrip_uno)));
        autos.add(new Auto(getString(R.string.auto_dos), R.drawable.autodos, 44000.00, getString(R.string.descrip_dos)));
        autos.add(new Auto(getString(R.string.auto_tres), R.drawable.autotres, 50000.00, getString(R.string.descrip_tres)));
        autos.add(new Auto(getString(R.string.auto_cuatro), R.drawable.autocuatro, 60000.00, getString(R.string.descrip_cuatro)));
        autos.add(new Auto(getString(R.string.auto_cinco), R.drawable.autocinco, 70000.00, getString(R.string.descrip_cinco)));
    }
}
