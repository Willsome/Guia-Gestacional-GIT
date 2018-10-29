package com.scriptpoin.gestacaosaudavel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;
import com.scriptpoin.gestacaosaudavel.data_das_consultas.Consulta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Willi on 06-Oct-17.
 */

public class ConsultasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Consulta> consultas;

    public ConsultasAdapter(Context context, ArrayList<Consulta> consultas) {
        this.context = context;
        this.consultas = consultas;
    }

    @Override
    public int getCount() {
        return consultas.size();
    }

    @Override
    public Object getItem(int position) {
        return consultas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return consultas.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.layout_data_das_consultas, viewGroup, false);

        if (view != null) {

            if (position == consultas.size() - 1) {
                LinearLayout dcllConsulta = (LinearLayout) view.findViewById(R.id.dcLlConsulta);
                dcllConsulta.setPadding(0, 0, 0, 100);
            }

            TextView dcTvNumeroConsulta = (TextView) view.findViewById(R.id.dcTvNumeroConsulta);
            TextView dcTvDataDaConsulta = (TextView) view.findViewById(R.id.dcTvDataDaConsulta);
            TextView dcTvHoraDaConsulta = (TextView) view.findViewById(R.id.dcTvHoraDaConsulta);
            TextView dcTvNomeDoProfissional = (TextView) view.findViewById(R.id.dcTvNomeDoProfissional);

            dcTvNumeroConsulta.setText(consultas.get(position).getNumeroConsulta() + "ª consulta");

            Calendar dataHoraConsulta = consultas.get(position).getDataHoraConsulta();

            SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
            dcTvDataDaConsulta.setText("data: " + formatadorData.format(dataHoraConsulta.getTime()));

            SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
            dcTvHoraDaConsulta.setText("hora: " + formatadorHora.format(dataHoraConsulta.getTime()));

            dcTvNomeDoProfissional.setText("Dr(a). " + consultas.get(position).getNomeProfissional());
        }

        return view;
    }
}
