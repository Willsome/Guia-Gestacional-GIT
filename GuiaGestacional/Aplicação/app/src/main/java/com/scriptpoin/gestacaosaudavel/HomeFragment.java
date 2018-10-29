package com.scriptpoin.gestacaosaudavel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        final DuvidasDoPreNatalFragment duvidasDoPreNatalFragment = new DuvidasDoPreNatalFragment();
        ImageButton ibMenuDuvidasDoPreNatal = (ImageButton) view.findViewById(R.id.ibMenuDuvidasDoPreNatal);
        ibMenuDuvidasDoPreNatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .addToBackStack("Duvidas do Pre natal Fragment")
                        .replace(R.id.mainLayout, duvidasDoPreNatalFragment).commit();
            }
        });

        final OrientacoesSobreOPartoFragment orientacoesSobreOPartoFragment = new OrientacoesSobreOPartoFragment();
        ImageButton ibMenuOrientacoesSobreOParto = (ImageButton) view.findViewById(R.id.ibMenuOrientacoesSobreOParto);
        ibMenuOrientacoesSobreOParto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .addToBackStack("Orientações Sobre O Parto Fragment")
                        .replace(R.id.mainLayout, orientacoesSobreOPartoFragment).commit();
            }
        });

        final AleitamentoMaternoFragment aleitamentoMaternoFragment = new AleitamentoMaternoFragment();
        ImageButton ibMenuAleitamento = (ImageButton) view.findViewById(R.id.ibMenuAleitamento);
        ibMenuAleitamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .addToBackStack("Aleitamento Materno Fragment")
                        .replace(R.id.mainLayout, aleitamentoMaternoFragment).commit();
            }
        });

        final ResguardoFragment resguardoFragment = new ResguardoFragment();
        ImageButton ibMenuPuerperio = (ImageButton) view.findViewById(R.id.ibMenuPuerperio);
        ibMenuPuerperio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .addToBackStack("Puerpério Fragment")
                        .replace(R.id.mainLayout, resguardoFragment).commit();
            }
        });

        final DataDasConsultasFragment dataDasConsultasFragment = new DataDasConsultasFragment();
        ImageButton ibMenuDataConsultas = (ImageButton) view.findViewById(R.id.ibMenuDataConsultas);
        ibMenuDataConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .addToBackStack("Data Das Consultas Fragment")
                        .replace(R.id.mainLayout, dataDasConsultasFragment).commit();
            }
        });

        final CadernetaFragment cadernetaFragment = new CadernetaFragment();
        ImageButton ibMenuCaderneta = (ImageButton) view.findViewById(R.id.ibMenuCaderneta);
        ibMenuCaderneta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .addToBackStack("Caderneta Fragment")
                        .replace(R.id.mainLayout, cadernetaFragment).commit();
            }
        });

        return view;
    }

}
