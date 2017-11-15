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
import com.scriptpoin.guiagestacional.gestacao.outras_duvidas.AtividadeFisicaActivity;
import com.scriptpoin.guiagestacional.gestacao.outras_duvidas.DireitosDaMulherActivity;
import com.scriptpoin.guiagestacional.gestacao.outras_duvidas.GanhoDePesoActivity;
import com.scriptpoin.guiagestacional.gestacao.outras_duvidas.UsoDeSuplementosActivity;
import com.scriptpoin.guiagestacional.gestacao.outras_duvidas.VacinasActivity;
import com.scriptpoin.guiagestacional.gestacao.outras_duvidas.PrincipaisQueixasNaGestacaoActivity;
import com.scriptpoin.guiagestacional.gestacao.trimestre.PrimeiroTrimestreActivity;
import com.scriptpoin.guiagestacional.gestacao.trimestre.SegundoTrimestreActivity;
import com.scriptpoin.guiagestacional.gestacao.trimestre.TerceiroTrimestreActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class DuvidasDoPreNatalFragment extends Fragment {


    public DuvidasDoPreNatalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Dúvidas do Pré-natal");

        View view = inflater.inflate(R.layout.fragment_duvidas, container, false);

        String[] trimestre = {
                "1º Trimestre de Gravidez",
                "2º Trimestre de Gravidez",
                "3º Trimestre de Gravidez",
                "Principais Queixas na Gestação",
                "Uso de Suplementos",
                "Vacinas",
                "Atividade Física",
                "Ganho de Peso",
                "Direitos da Mulher"};

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
                        Intent vaiParaPrincipaisQueixasNaGestacao = new Intent(getActivity(), PrincipaisQueixasNaGestacaoActivity.class);
                        startActivity(vaiParaPrincipaisQueixasNaGestacao);
                        break;
                    case 4:
                        Intent vaiParaUsoDeSuplementos = new Intent(getActivity(), UsoDeSuplementosActivity.class);
                        startActivity(vaiParaUsoDeSuplementos);
                        break;
                    case 5:
                        Intent vaiParaVacinas = new Intent(getActivity(), VacinasActivity.class);
                        startActivity(vaiParaVacinas);
                        break;
                    case 6:
                        Intent vaiParaAtividadeFisica = new Intent(getActivity(), AtividadeFisicaActivity.class);
                        startActivity(vaiParaAtividadeFisica);
                        break;
                    case 7:
                        Intent vaiParaGanhoDePeso = new Intent(getActivity(), GanhoDePesoActivity.class);
                        startActivity(vaiParaGanhoDePeso);
                        break;
                    case 8:
                        Intent vaiParaDireitosDaMulher = new Intent(getActivity(), DireitosDaMulherActivity.class);
                        startActivity(vaiParaDireitosDaMulher);
                        break;
                }
            }
        });

        return view;
    }

}
