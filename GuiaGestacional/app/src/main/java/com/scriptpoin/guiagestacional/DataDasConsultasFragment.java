package com.scriptpoin.guiagestacional;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.adapter.ConsultasAdapter;
import com.scriptpoin.guiagestacional.dao.DaoConsultas;
import com.scriptpoin.guiagestacional.data_das_consultas.CadastroDeConsultasActivity;
import com.scriptpoin.guiagestacional.data_das_consultas.Consulta;
import com.scriptpoin.guiagestacional.data_das_consultas.ConsultaBroadcastReceiver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataDasConsultasFragment extends Fragment {


    private View view;
//    private ListView dcLvConsultas;
    private LinearLayout dcLlConsultas;
    private LayoutInflater inflater;
    private ArrayList<Consulta> consultas;


    public DataDasConsultasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Data das Consultas");

        this.inflater = inflater;

        view = inflater.inflate(R.layout.fragment_data_das_consultas, container, false);


        Button dcBtAdicionarConsulta = (Button) view.findViewById(R.id.dcBtAdicionarConsulta);
        dcBtAdicionarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CadastroDeConsultasActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        carregaListaDeConsultas();
    }

    public void carregaListaDeConsultas() {

        DaoConsultas daoConsultas = new DaoConsultas(getContext());

        consultas = daoConsultas.pegaConsultas();

        dcLlConsultas = (LinearLayout) view.findViewById(R.id.dcLlConsultas);

        dcLlConsultas.removeAllViews();

        if (consultas.size() == 1) {

            View v = inflater.inflate(R.layout.layout_data_das_consultas, null, false);

            TextView dcTvNumeroConsulta = (TextView) v.findViewById(R.id.dcTvNumeroConsulta);
            TextView dcTvDataDaConsulta = (TextView) v.findViewById(R.id.dcTvDataDaConsulta);
            TextView dcTvHoraDaConsulta = (TextView) v.findViewById(R.id.dcTvHoraDaConsulta);
            TextView dcTvNomeDoProfissional = (TextView) v.findViewById(R.id.dcTvNomeDoProfissional);

            dcTvNumeroConsulta.setText(consultas.get(0).getNumeroConsulta() + "ª consulta");

            Calendar dataHoraConsulta = consultas.get(0).getDataHoraConsulta();

            SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
            dcTvDataDaConsulta.setText("data: " + formatadorData.format(dataHoraConsulta.getTime()));

            SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
            dcTvHoraDaConsulta.setText("hora: " + formatadorHora.format(dataHoraConsulta.getTime()));

            dcTvNomeDoProfissional.setText(consultas.get(0).getTipoProfissional() + " " + consultas.get(0).getNomeProfissional());

            dcLlConsultas.addView(v);

            registerForContextMenu(dcLlConsultas);

            dcLlConsultas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getActivity(), "Em produção...", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            TextView tv = new TextView(getContext());
            tv.setText("Nenhuma consulta adicionada");
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            dcLlConsultas.addView(tv);
        }

//        dcLlConsultas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Consulta consulta = consultas.get(0);
//
//                Intent intent = new Intent(getActivity(), CadastroDeConsultasActivity.class);
//                intent.putExtra("consulta", consulta);
//                startActivity(intent);
//
//                Toast.makeText(getActivity(), "Em produção...", Toast.LENGTH_SHORT).show();
//            }
//        });

//        dcLvConsultas = (ListView) view.findViewById(R.id.dcLvConsultas);
//        final ConsultasAdapter consultasAdapter = new ConsultasAdapter(getContext(), consultas);
//        dcLvConsultas.setAdapter(consultasAdapter);
//
//        registerForContextMenu(dcLvConsultas);
//
//        dcLvConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {

//                Consulta consulta = consultas.get(position);
//
//                Intent intent = new Intent(getActivity(), CadastroDeConsultasActivity.class);
//                intent.putExtra("consulta", consulta);
//                startActivity(intent);

//            }
//        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        final MenuItem deletar = menu.add("deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                DaoConsultas daoConsultas = new DaoConsultas(getContext());

                if (daoConsultas.deletaConsulta(consultas.get(0))) {
                    deletarAlarme();
                    carregaListaDeConsultas();
                    Toast.makeText(getActivity(), "A consulta foi deletada !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Ocorreu um erro ao tentar deletar a consulta...", Toast.LENGTH_SHORT).show();
                }
                daoConsultas.close();

                return false;
            }
        });
    }

    public void deletarAlarme() {

        Intent intent = new Intent(getContext(), ConsultaBroadcastReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getActivity(),
                0,
                intent,
                0
        );

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();

        Toast.makeText(getActivity(), "Alarme cancelado !", Toast.LENGTH_SHORT).show();
    }
}