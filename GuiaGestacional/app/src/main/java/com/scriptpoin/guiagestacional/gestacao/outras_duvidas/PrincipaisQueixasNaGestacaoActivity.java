package com.scriptpoin.guiagestacional.gestacao.outras_duvidas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoDuvidas;
import com.scriptpoin.guiagestacional.gestacao.modelo_duvida_resposta.DuvidasTrimestre;
import com.scriptpoin.guiagestacional.gestacao.modelo_duvida_resposta.RespostasActivity;

import java.util.ArrayList;

public class PrincipaisQueixasNaGestacaoActivity extends AppCompatActivity {

    LinearLayout llPrincipaisQueixasNaGestacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principais_queixas_na_gestacao);

        setTitle("Principais Queixas na Gestação");

        llPrincipaisQueixasNaGestacao = (LinearLayout) findViewById(R.id.llPrincipaisQueixasNaGestacao);

        DaoDuvidas daoDuvidas = new DaoDuvidas(this);

        final ArrayList<DuvidasTrimestre> duvidas = daoDuvidas.pegaDuvidas(4);

        daoDuvidas.close();

        for (int i = 0; i < duvidas.size(); i++) {
            final DuvidasTrimestre duvidasTrimestre = duvidas.get(i);

            LayoutInflater inflater = LayoutInflater.from(this);

            View view = inflater.inflate(R.layout.layout_perguntas_trimestre, null);
            view.setId(duvidasTrimestre.getId());

            TextView pergunta = (TextView) view.findViewById(R.id.trimestrePergunta);
            pergunta.setText(duvidasTrimestre.getPergunta());
            pergunta.setGravity(Gravity.CENTER);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PrincipaisQueixasNaGestacaoActivity.this, RespostasActivity.class);
                    intent.putExtra("duvida", duvidasTrimestre);
                    startActivity(intent);
                }
            });

            this.llPrincipaisQueixasNaGestacao.addView(view);
        }

    }
}
