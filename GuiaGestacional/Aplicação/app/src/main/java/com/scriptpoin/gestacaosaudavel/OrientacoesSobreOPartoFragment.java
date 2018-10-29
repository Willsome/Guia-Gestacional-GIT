package com.scriptpoin.gestacaosaudavel;


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

        getActivity().setTitle(getResources().getString(R.string.orientacoes_sobre_parto));

        View view = inflater.inflate(R.layout.fragment_orientacoes_sobre_o_parto, container, false);

        TextView orientacoesSobreOParto = view.findViewById(R.id.orientacoesSobreOParto);
        orientacoesSobreOParto.setText(Html.fromHtml(getString(R.string.descricao_parto)));

        TextView orientacoesSobreOPartoBoasPraticas = view.findViewById(R.id.orientacoesSobreOPartoBoasPraticas);
        orientacoesSobreOPartoBoasPraticas.setText(Html.fromHtml(getString(R.string.descricao_boas_praticas_parto)));

        return view;
    }

}
