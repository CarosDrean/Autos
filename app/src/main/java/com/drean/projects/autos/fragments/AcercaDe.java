package com.drean.projects.autos.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.drean.projects.autos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcercaDe extends Fragment {


    public AcercaDe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_acerca_de, container, false);
        ImageView footer = v.findViewById(R.id.footer);
        ImageView ic = v.findViewById(R.id.ic_descripcion);
        Glide.with(getContext()).load(R.drawable.auto).into(footer);
        Glide.with(getContext()).load(R.drawable.auto).into(ic);
        return v;
    }

}
