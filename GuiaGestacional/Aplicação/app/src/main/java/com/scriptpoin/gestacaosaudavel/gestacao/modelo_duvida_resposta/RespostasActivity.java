package com.scriptpoin.gestacaosaudavel.gestacao.modelo_duvida_resposta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

public class RespostasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respostas);

        Intent intent = getIntent();
        DuvidasTrimestre duvidasTrimestre = (DuvidasTrimestre) intent.getSerializableExtra("duvida");

        TextView tv = (TextView) findViewById(R.id.respostaTrimestre);
        tv.setText(duvidasTrimestre.getResposta());

        Button btVoltarResposta = (Button) findViewById(R.id.btVoltarResposta);
        btVoltarResposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
