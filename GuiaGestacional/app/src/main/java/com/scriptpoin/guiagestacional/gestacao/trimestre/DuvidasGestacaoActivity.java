package com.scriptpoin.guiagestacional.gestacao.trimestre;

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

import java.util.ArrayList;

public class DuvidasGestacaoActivity extends AppCompatActivity {

    LinearLayout llPerguntasDuvidasGestacaoPI;
    LinearLayout llPerguntasDuvidasGestacaoPII;
    LinearLayout llPerguntasDuvidasGestacaoPIII;
    LinearLayout llPerguntasDuvidasGestacaoPIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duvidas_gestacao);

        setTitle("Dúvidas da Gestação");

        llPerguntasDuvidasGestacaoPI = (LinearLayout) findViewById(R.id.llPerguntasDuvidasGestacaoPI);
        llPerguntasDuvidasGestacaoPII = (LinearLayout) findViewById(R.id.llPerguntasDuvidasGestacaoPII);
        llPerguntasDuvidasGestacaoPIII = (LinearLayout) findViewById(R.id.llPerguntasDuvidasGestacaoPIII);
        llPerguntasDuvidasGestacaoPIV = (LinearLayout) findViewById(R.id.llPerguntasDuvidasGestacaoPIV);

        DaoDuvidas daoDuvidas = new DaoDuvidas(this);

        final ArrayList<DuvidasTrimestre> duvidasPI = daoDuvidas.pegaDuvidas(41);
        final ArrayList<DuvidasTrimestre> duvidasPII = daoDuvidas.pegaDuvidas(42);
        final ArrayList<DuvidasTrimestre> duvidasPIII = daoDuvidas.pegaDuvidas(43);
        final ArrayList<DuvidasTrimestre> duvidasPIV = daoDuvidas.pegaDuvidas(44);

        daoDuvidas.close();

        for (int i = 0; i < duvidasPI.size(); i++) {
            final DuvidasTrimestre duvidasTrimestre = duvidasPI.get(i);

            LayoutInflater inflater = LayoutInflater.from(this);

            View view = inflater.inflate(R.layout.layout_perguntas_trimestre, null);
            view.setId(duvidasTrimestre.getId());

            TextView pergunta = (TextView) view.findViewById(R.id.trimestrePergunta);
            pergunta.setText(duvidasTrimestre.getPergunta());
            pergunta.setGravity(Gravity.CENTER);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DuvidasGestacaoActivity.this, RespostasActivity.class);
                    intent.putExtra("duvida", duvidasTrimestre);
                    startActivity(intent);
                }
            });

            this.llPerguntasDuvidasGestacaoPI.addView(view);
        }

        for (int i = 0; i < duvidasPII.size(); i++) {
            final DuvidasTrimestre duvidasTrimestre = duvidasPII.get(i);

            LayoutInflater inflater = LayoutInflater.from(this);

            View view = inflater.inflate(R.layout.layout_perguntas_trimestre, null);
            view.setId(duvidasTrimestre.getId());

            TextView pergunta = (TextView) view.findViewById(R.id.trimestrePergunta);
            pergunta.setText(duvidasTrimestre.getPergunta());
            pergunta.setGravity(Gravity.CENTER);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DuvidasGestacaoActivity.this, RespostasActivity.class);
                    intent.putExtra("duvida", duvidasTrimestre);
                    startActivity(intent);
                }
            });

            this.llPerguntasDuvidasGestacaoPII.addView(view);
        }

        for (int i = 0; i < duvidasPIII.size(); i++) {
            final DuvidasTrimestre duvidasTrimestre = duvidasPIII.get(i);

            LayoutInflater inflater = LayoutInflater.from(this);

            View view = inflater.inflate(R.layout.layout_perguntas_trimestre, null);
            view.setId(duvidasTrimestre.getId());

            TextView pergunta = (TextView) view.findViewById(R.id.trimestrePergunta);
            pergunta.setText(duvidasTrimestre.getPergunta());
            pergunta.setGravity(Gravity.CENTER);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DuvidasGestacaoActivity.this, RespostasActivity.class);
                    intent.putExtra("duvida", duvidasTrimestre);
                    startActivity(intent);
                }
            });

            this.llPerguntasDuvidasGestacaoPIII.addView(view);
        }

        for (int i = 0; i < duvidasPIV.size(); i++) {
            final DuvidasTrimestre duvidasTrimestre = duvidasPIV.get(i);

            LayoutInflater inflater = LayoutInflater.from(this);

            View view = inflater.inflate(R.layout.layout_perguntas_trimestre, null);
            view.setId(duvidasTrimestre.getId());

            TextView pergunta = (TextView) view.findViewById(R.id.trimestrePergunta);
            pergunta.setText(duvidasTrimestre.getPergunta());
            pergunta.setGravity(Gravity.CENTER);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DuvidasGestacaoActivity.this, RespostasActivity.class);
                    intent.putExtra("duvida", duvidasTrimestre);
                    startActivity(intent);
                }
            });

            this.llPerguntasDuvidasGestacaoPIV.addView(view);
        }
    }
}
