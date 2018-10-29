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
import com.scriptpoin.gestacaosaudavel.resguardo.CuidadosMaeActivity;
import com.scriptpoin.gestacaosaudavel.resguardo.CuidadosRecemNascidoActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResguardoFragment extends Fragment {


    public ResguardoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Resources res = getResources();
        getActivity().setTitle(res.getString(R.string.resguardo));

        View view = inflater.inflate(R.layout.fragment_resguardo, container, false);

        String[] resguardoMenus = res.getStringArray(R.array.resguardo_menus);

        ListView lvOpcoesPuerperio = view.findViewById(R.id.lvResguardo);
        TrimestreAdapter trimestreAdapter = new TrimestreAdapter(getActivity(), resguardoMenus);
        lvOpcoesPuerperio.setAdapter(trimestreAdapter);

        lvOpcoesPuerperio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                switch (position) {
                    case 0:
                        Intent vaiParaCuidadosMae = new Intent(getActivity(), CuidadosMaeActivity.class);
                        startActivity(vaiParaCuidadosMae);
                        break;
                    case 1:
                        Intent vaiParaCuidadosRecemNascido = new Intent(getActivity(), CuidadosRecemNascidoActivity.class);
                        startActivity(vaiParaCuidadosRecemNascido);
                        break;
                }
            }
        });


        return view;
    }

}
