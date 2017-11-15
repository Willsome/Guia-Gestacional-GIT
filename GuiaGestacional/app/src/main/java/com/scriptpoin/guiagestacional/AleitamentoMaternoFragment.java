package com.scriptpoin.guiagestacional;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scriptpoin.guiagestacional.adapter.TrimestreAdapter;
import com.scriptpoin.guiagestacional.aleitamento.BeneficiosAleitamentoBebeActivity;
import com.scriptpoin.guiagestacional.aleitamento.BeneficiosAleitamentoMaeActivity;
import com.scriptpoin.guiagestacional.aleitamento.DuvidasSobreAleitamentoActivity;


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
                "Benefícios do Aleitamento materno para a Mãe",
                "Benefícios do Aleitamento Materno para o Bebê",
                "Dúvidas Sobre o Aleitamento Materno"};

        ListView lvOpcoesAleitamento = (ListView) view.findViewById(R.id.opcoesAleitamento);
        TrimestreAdapter trimestreAdapter = new TrimestreAdapter(getActivity(), opcoesAleitamento);
        lvOpcoesAleitamento.setAdapter(trimestreAdapter);

        lvOpcoesAleitamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                switch (position) {
                    case 0:
                        Intent vaiParaAleitamentoMae = new Intent(getActivity(), BeneficiosAleitamentoMaeActivity.class);
                        startActivity(vaiParaAleitamentoMae);
                        break;
                    case 1:
                        Intent vaiParaAleitamentoBebe = new Intent(getActivity(), BeneficiosAleitamentoBebeActivity.class);
                        startActivity(vaiParaAleitamentoBebe);
                        break;
                    case 2:
                        Intent vaiParaDuvidasAleitamento = new Intent(getActivity(), DuvidasSobreAleitamentoActivity.class);
                        startActivity(vaiParaDuvidasAleitamento);
                        break;
                }
            }
        });

        return view;
    }

}
