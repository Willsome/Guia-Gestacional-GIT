package com.scriptpoin.guiagestacional;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.adapter.TrimestreAdapter;
import com.scriptpoin.guiagestacional.gestacao.trimestre.PrimeiroTrimestreActivity;
import com.scriptpoin.guiagestacional.gestacao.trimestre.SegundoTrimestreActivity;
import com.scriptpoin.guiagestacional.gestacao.trimestre.TerceiroTrimestreActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class AleitamentoMaternoFragment extends Fragment {


    public AleitamentoMaternoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Aleitamento Materno");

        View view = inflater.inflate(R.layout.fragment_aleitamento_materno, container, false);

        String[] opcoesAleitamento = {
                "Benefícios do Aleitamento para a Mãe e o Bebê",
                "Mitos e Verdades do Aleitamento Materno"};

        ListView lvOpcoesAleitamento = (ListView) view.findViewById(R.id.opcoesAleitamento);
        TrimestreAdapter trimestreAdapter = new TrimestreAdapter(getActivity(), opcoesAleitamento);
        lvOpcoesAleitamento.setAdapter(trimestreAdapter);

        lvOpcoesAleitamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                switch (position) {
                    case 0:
                        Intent vaiParaPrimeiroTrimestre = new Intent(getActivity(), PrimeiroTrimestreActivity.class);
                        startActivity(vaiParaPrimeiroTrimestre);
                        break;
                    case 1:
                        Intent vaiParaSegundoTrimestre = new Intent(getActivity(), SegundoTrimestreActivity.class);
                        startActivity(vaiParaSegundoTrimestre);
                        break;
                }
            }
        });

        return view;
    }

}
