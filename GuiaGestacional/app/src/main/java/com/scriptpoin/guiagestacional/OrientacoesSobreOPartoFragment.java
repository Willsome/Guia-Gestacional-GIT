package com.scriptpoin.guiagestacional;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrientacoesSobreOPartoFragment extends Fragment {


    public OrientacoesSobreOPartoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Orientações Sobre o Parto");

        View view = inflater.inflate(R.layout.fragment_orientacoes_sobre_o_parto, container, false);

        TextView orientacoesSobreOParto = (TextView) view.findViewById(R.id.orientacoesSobreOParto);
        orientacoesSobreOParto.setText(Html.fromHtml(getString(R.string.parto)));

        TextView orientacoesSobreOPartoBoasPraticas = (TextView) view.findViewById(R.id.orientacoesSobreOPartoBoasPraticas);
        orientacoesSobreOPartoBoasPraticas.setText(Html.fromHtml(getString(R.string.boasPraticasParto)));

        return view;
    }

}
