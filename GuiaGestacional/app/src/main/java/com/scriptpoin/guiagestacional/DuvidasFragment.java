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
import com.scriptpoin.guiagestacional.gestacao.trimestre.DuvidasGestacaoActivity;
import com.scriptpoin.guiagestacional.gestacao.trimestre.PrimeiroTrimestreActivity;
import com.scriptpoin.guiagestacional.gestacao.trimestre.SegundoTrimestreActivity;
import com.scriptpoin.guiagestacional.gestacao.trimestre.TerceiroTrimestreActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class DuvidasFragment extends Fragment {


    public DuvidasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Dúvidas");

        View view = inflater.inflate(R.layout.fragment_duvidas, container, false);

        String[] trimestre = {"1º Trimestre", "2º Trimestre","3º Trimestre", "Dúvidas da Gestação"};

        ListView duvidasTrimestre = (ListView) view.findViewById(R.id.duvidasTrimestre);
        TrimestreAdapter trimestreAdapter = new TrimestreAdapter(getActivity(), trimestre);
        duvidasTrimestre.setAdapter(trimestreAdapter);

        duvidasTrimestre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    case 2:
                        Intent vaiParaTerceiroTrimestre = new Intent(getActivity(), TerceiroTrimestreActivity.class);
                        startActivity(vaiParaTerceiroTrimestre);
                        break;
                    case 3:
                        Intent vaiParaDuvidasDaGestacao = new Intent(getActivity(), DuvidasGestacaoActivity.class);
                        startActivity(vaiParaDuvidasDaGestacao);
                        break;
                }
            }
        });

        return view;
    }

}
