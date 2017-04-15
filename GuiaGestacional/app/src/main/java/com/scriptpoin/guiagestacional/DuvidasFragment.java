package com.scriptpoin.guiagestacional;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DuvidasFragment extends Fragment {


    public DuvidasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Dúvidas");

        View view = inflater.inflate(R.layout.fragment_duvidas, container, false);

        ListView duvidasTrimestre = (ListView) view.findViewById(R.id.duvidasTrimestre);

        String[] trimestre = {"1º Trimestre", "2º Trimestre","3º Trimestre"};

        AdapterListViewTrimestre adapterListViewTrimestre = new AdapterListViewTrimestre(getActivity(), trimestre);

//        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                trimestre
//        );
        duvidasTrimestre.setAdapter(adapterListViewTrimestre);

        // Inflate the layout for this fragment
        return view;
    }

}
