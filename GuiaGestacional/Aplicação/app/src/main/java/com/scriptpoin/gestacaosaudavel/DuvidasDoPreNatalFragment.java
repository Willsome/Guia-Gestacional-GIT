package com.scriptpoin.gestacaosaudavel;


import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;

import com.scriptpoin.gestacaosaudavel.adapter.TrimestreAdapter;
import com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas.AtividadeFisicaActivity;
import com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas.DireitosDaMulherActivity;
import com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas.GanhoDePesoActivity;
import com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas.PrincipaisQueixasNaGestacaoActivity;
import com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas.UsoDeSuplementosActivity;
import com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas.VacinasActivity;
import com.scriptpoin.gestacaosaudavel.gestacao.trimestre.PrimeiroTrimestreActivity;
import com.scriptpoin.gestacaosaudavel.gestacao.trimestre.SegundoTrimestreActivity;
import com.scriptpoin.gestacaosaudavel.gestacao.trimestre.TerceiroTrimestreActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class DuvidasDoPreNatalFragment extends Fragment {


    public DuvidasDoPreNatalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Resources res = getResources();
        getActivity().setTitle(res.getString(R.string.duvidas_prenatal));

        View view = inflater.inflate(R.layout.fragment_duvidas_prenatal, container, false);

        String[] duvidasPrenatalMenu = res.getStringArray(R.array.duvidas_prenatal_menus);

        ListView duvidasTrimestre = view.findViewById(R.id.lvDuvidasPrenatal);
        TrimestreAdapter trimestreAdapter = new TrimestreAdapter(getActivity(), duvidasPrenatalMenu);
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
