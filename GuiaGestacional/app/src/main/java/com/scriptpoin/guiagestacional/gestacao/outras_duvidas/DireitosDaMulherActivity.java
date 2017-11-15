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

public class DireitosDaMulherActivity extends AppCompatActivity {

    private LinearLayout llDireitosDaMulher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direitos_da_mulher);

        setTitle("Direitos da Mulher");

        llDireitosDaMulher = (LinearLayout) findViewById(R.id.llDireitosDaMulher);

        DaoDuvidas daoDuvidas = new DaoDuvidas(this);

        final ArrayList<DuvidasTrimestre> duvidas = daoDuvidas.pegaDuvidas(5);

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
                    Intent intent = new Intent(DireitosDaMulherActivity.this, RespostasActivity.class);
                    intent.putExtra("duvida", duvidasTrimestre);
                    startActivity(intent);
                }
            });

            this.llDireitosDaMulher.addView(view);
        }
    }
}
