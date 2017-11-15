package com.scriptpoin.guiagestacional.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

/**
 * Created by Bianor on 4/15/2017.
 */

public class TrimestreAdapter extends BaseAdapter {

    private Context context;
    private String[] trimestres;

    public TrimestreAdapter(Context context, String[] trimestres) {
        this.context = context;
        this.trimestres = trimestres;
    }

    @Override
    public int getCount() {
        return trimestres.length;
    }

    @Override
    public Object getItem(int position) {
        return trimestres[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_listview_trimestre_background, parent, false);

        if (view != null) {
            TextView infoTrimestre = (TextView) view.findViewById(R.id.infoTrimestre);
            infoTrimestre.setText(trimestres[position]);
        }

        return view;
    }
}
