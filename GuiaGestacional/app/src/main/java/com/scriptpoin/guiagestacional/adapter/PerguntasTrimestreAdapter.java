package com.scriptpoin.guiagestacional.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

import java.util.HashSet;

/**
 * Created by Willi on 29-Jul-17.
 */

public class PerguntasTrimestreAdapter extends BaseAdapter {

    private final String[] perguntas;
    private Context context;

    public PerguntasTrimestreAdapter(Context context, String[] perguntas) {
        this.context = context;
        this.perguntas = perguntas;
    }


    @Override
    public int getCount() {
        return perguntas.length;
    }

    @Override
    public Object getItem(int position) {
        return perguntas[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_perguntas_trimestre, viewGroup, false);

        TextView pergunta = (TextView) view.findViewById(R.id.trimestrePergunta);
        pergunta.setText(perguntas[position]);

        return view;
    }
}
