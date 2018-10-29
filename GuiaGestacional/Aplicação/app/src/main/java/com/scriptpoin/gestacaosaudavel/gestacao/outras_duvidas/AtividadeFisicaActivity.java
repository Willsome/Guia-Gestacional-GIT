package com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

public class AtividadeFisicaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_fisica);

        Resources res = getResources();
        setTitle(res.getString(R.string.atividade_fisica_titulo));

        TextView tvAtividadeFisica = findViewById(R.id.tvAtividadeFisica);
        tvAtividadeFisica.setText(Html.fromHtml(getString(R.string.atividade_fisica)));
    }
}
