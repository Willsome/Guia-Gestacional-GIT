package com.scriptpoin.gestacaosaudavel.caderneta.ultrassonografia;

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

public class ListaUltrassonografiasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ultrassonografia);

        DaoCaderneta dao = new DaoCaderneta(this);

        final ArrayList<String> ultrassonografias = dao.pegaQuantUltrassonografias();

        dao.close();

        if (ultrassonografias.size() > 1) {

            ListView lvUltrassonografia = findViewById(R.id.lvUltrassonografia);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    ultrassonografias
            );
            lvUltrassonografia.setAdapter(adapter);

            lvUltrassonografia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {

                    String ultra = (String) lista.getItemAtPosition(posicao);

                    Intent intent = new Intent();
                    intent.putExtra("consultaSolicitacao", Integer.parseInt(ultra.substring(0, ultra.indexOf("ª"))));
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });

            Button btVoltarListaUltrassonografia = findViewById(R.id.btVoltarListaUltrassonografia);
            btVoltarListaUltrassonografia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        } else {
            finish();
            Toast.makeText(this, "Só existe a ultra atual...", Toast.LENGTH_SHORT).show();
        }

    }
}
