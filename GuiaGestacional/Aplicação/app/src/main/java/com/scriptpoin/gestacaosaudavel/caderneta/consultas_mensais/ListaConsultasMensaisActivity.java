package com.scriptpoin.gestacaosaudavel.caderneta.consultas_mensais;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.scriptpoin.gestacaosaudavel.R;
import com.scriptpoin.gestacaosaudavel.dao.DaoCaderneta;

import java.util.ArrayList;

public class ListaConsultasMensaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_consultas_mensais);

        DaoCaderneta dao = new DaoCaderneta(this);

        final ArrayList<String> consultas = dao.pegaQuantConsultasMensais();

        dao.close();

        if (consultas.size() > 1) {

            ListView lvConsultasMensais = findViewById(R.id.lvConsultasMensais);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    consultas
            );
            lvConsultasMensais.setAdapter(adapter);

            lvConsultasMensais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {

                    String consulta = (String) lista.getItemAtPosition(posicao);

                    Intent intent = new Intent();
                    intent.putExtra("numeroConsulta", Integer.parseInt(consulta.substring(0, consulta.indexOf("ª"))));
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });

            Button btVoltarListaConsultasMensais = findViewById(R.id.btVoltarListaConsultasMensais);
            btVoltarListaConsultasMensais.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        } else {
            finish();
            Toast.makeText(this, "Só existe a consulta atual...", Toast.LENGTH_SHORT).show();
        }

    }
}
