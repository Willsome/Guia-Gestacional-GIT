package com.scriptpoin.guiagestacional.gestacao.trimestre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoDuvidas;

import java.util.ArrayList;

public class TerceiroTrimestreActivity extends AppCompatActivity {

    private LinearLayout llPerguntasTrimestre1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceiro_trimestre);

        setTitle("Segundo Trimestre");

        llPerguntasTrimestre1 = (LinearLayout) findViewById(R.id.llPerguntasTrimestre1);

        LinearLayoutCompat.LayoutParams lparams = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        DaoDuvidas daoDuvidas = new DaoDuvidas(this);

        final ArrayList<DuvidasTrimestre> duvidas = daoDuvidas.pegaDuvidas(3);

        daoDuvidas.close();

        for (int i = 0; i < duvidas.size(); i++) {
            final DuvidasTrimestre duvidasTrimestre = duvidas.get(i);

            LayoutInflater inflater = LayoutInflater.from(this);

            View view = inflater.inflate(R.layout.layout_perguntas_trimestre, null);
            view.setId(duvidasTrimestre.getId());

            TextView pergunta = (TextView) view.findViewById(R.id.trimestrePergunta);
            pergunta.setText(duvidasTrimestre.getPergunta());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TerceiroTrimestreActivity.this, RespostasActivity.class);
                    intent.putExtra("duvida", duvidasTrimestre);
                    startActivity(intent);
                }
            });

            this.llPerguntasTrimestre1.addView(view);
        }
    }
}
