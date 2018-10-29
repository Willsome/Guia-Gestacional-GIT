package com.scriptpoin.gestacaosaudavel.gestacao.trimestre;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;
import com.scriptpoin.gestacaosaudavel.dao.DaoDuvidas;
import com.scriptpoin.gestacaosaudavel.gestacao.modelo_duvida_resposta.DuvidasTrimestre;
import com.scriptpoin.gestacaosaudavel.gestacao.modelo_duvida_resposta.RespostasActivity;

import java.util.ArrayList;

public class TerceiroTrimestreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceiro_trimestre);

        Resources res = getResources();
        setTitle(res.getString(R.string.trimestre_3_titulo));

        LinearLayout llPerguntasTrimestre3 = findViewById(R.id.llPerguntasTrimestre3);

        //LinearLayoutCompat.LayoutParams lparams = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        DaoDuvidas daoDuvidas = new DaoDuvidas(this);

        final ArrayList<DuvidasTrimestre> duvidas = daoDuvidas.pegaDuvidas(3);

        daoDuvidas.close();

        for (int i = 0; i < duvidas.size(); i++) {
            final DuvidasTrimestre duvidasTrimestre = duvidas.get(i);

            LayoutInflater inflater = LayoutInflater.from(this);

            View view = inflater.inflate(R.layout.layout_perguntas_trimestre, null);
            view.setId(duvidasTrimestre.getId());

            TextView pergunta = view.findViewById(R.id.trimestrePergunta);
            pergunta.setText(duvidasTrimestre.getPergunta());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TerceiroTrimestreActivity.this, RespostasActivity.class);
                    intent.putExtra("duvida", duvidasTrimestre);
                    startActivity(intent);
                }
            });

            llPerguntasTrimestre3.addView(view);
        }
    }
}
