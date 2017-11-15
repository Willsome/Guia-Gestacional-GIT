package com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.util.ArrayList;

public class ListaExamesSolicitadosResultadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exames_solicitados_resultados);

        DaoCaderneta dao = new DaoCaderneta(this);

        final ArrayList<String> examesSolicitadosResultados = dao.pegaQuantExamesSolicitadosResultados();

        dao.close();

        if (examesSolicitadosResultados.size() > 1) {

            ListView lvExamesSolicitadosResultados = (ListView) findViewById(R.id.lvExamesSolicitadosResultados);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    examesSolicitadosResultados
            );
            lvExamesSolicitadosResultados.setAdapter(adapter);

            lvExamesSolicitadosResultados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {

                    String exames = (String) lista.getItemAtPosition(posicao);

                    Intent intent = new Intent();
                    intent.putExtra("consultaSolicitacao", Integer.parseInt(exames.substring(0, exames.indexOf("ª"))));
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });

            Button btVoltarListaExamesSolicitadosResultados = (Button) findViewById(R.id.btVoltarListaExamesSolicitadosResultados);
            btVoltarListaExamesSolicitadosResultados.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        } else {
            finish();
            Toast.makeText(this, "Só existem os exames solicitados/resultados atuais...", Toast.LENGTH_SHORT).show();
        }

    }
}

