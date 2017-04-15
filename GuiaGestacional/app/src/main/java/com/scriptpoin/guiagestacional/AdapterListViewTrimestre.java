package com.scriptpoin.guiagestacional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bianor on 4/15/2017.
 */

public class AdapterListViewTrimestre extends BaseAdapter {

    private Context context;
    private String[] trimestre;

    public AdapterListViewTrimestre(Context context, String[] trimestre) {
        this.context = context;
        this.trimestre = trimestre;
    }

    @Override
    public int getCount() {
        return trimestre.length;
    }

    @Override
    public Object getItem(int position) {
        return trimestre[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listview_trimestre_background, null);

        if (view != null) {
            TextView infoTrimestre = (TextView) view.findViewById(R.id.infoTrimestre);
            infoTrimestre.setText(trimestre[position]);
        }

        return view;
    }
}
