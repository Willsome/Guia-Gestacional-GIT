package com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;
import com.scriptpoin.gestacaosaudavel.dao.DaoDuvidas;
import com.scriptpoin.gestacaosaudavel.gestacao.modelo_duvida_resposta.DuvidasTrimestre;
import com.scriptpoin.gestacaosaudavel.gestacao.modelo_duvida_resposta.RespostasActivity;

import java.util.ArrayList;

public class DireitosDaMulherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direitos_da_mulher);

        Resources res = getResources();
        setTitle(res.getString(R.string.direitos_mulher_titulo));

        LinearLayout llDireitosDaMulher = findViewById(R.id.llDireitosDaMulher);

        DaoDuvidas daoDuvidas = new DaoDuvidas(this);

        final ArrayList<DuvidasTrimestre> duvidas = daoDuvidas.pegaDuvidas(5);

        daoDuvidas.close();

        for (int i = 0; i < duvidas.size(); i++) {
            final DuvidasTrimestre duvidasTrimestre = duvidas.get(i);

            LayoutInflater inflater = LayoutInflater.from(this);

            View view = inflater.inflate(R.layout.layout_perguntas_trimestre, null);
            view.setId(duvidasTrimestre.getId());

            TextView pergunta = view.findViewById(R.id.trimestrePergunta);
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

            llDireitosDaMulher.addView(view);
        }
    }
}
