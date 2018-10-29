package com.scriptpoin.gestacaosaudavel;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scriptpoin.gestacaosaudavel.adapter.TrimestreAdapter;
import com.scriptpoin.gestacaosaudavel.aleitamento.BeneficiosAleitamentoBebeActivity;
import com.scriptpoin.gestacaosaudavel.aleitamento.BeneficiosAleitamentoMaeActivity;
import com.scriptpoin.gestacaosaudavel.aleitamento.DuvidasSobreAleitamentoActivity;


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

        Resources res = getResources();
        getActivity().setTitle(res.getString(R.string.aleitamento_materno));

        View view = inflater.inflate(R.layout.fragment_aleitamento_materno, container, false);

        String[] aleitamentoMaternoMenu = res.getStringArray(R.array.aleitamento_materno_menus);

        ListView lvOpcoesAleitamento = view.findViewById(R.id.opcoesAleitamento);
        TrimestreAdapter trimestreAdapter = new TrimestreAdapter(getActivity(), aleitamentoMaternoMenu);
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
