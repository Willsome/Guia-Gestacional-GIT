package com.scriptpoin.guiagestacional.gestacao.outras_duvidas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

public class AtividadeFisicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_fisica);

        setTitle("Atividade Física");

        TextView tvAtividadeFisica = (TextView) findViewById(R.id.tvAtividadeFisica);
        tvAtividadeFisica.setText(Html.fromHtml(getString(R.string.atividadeFisica)));
    }
}
