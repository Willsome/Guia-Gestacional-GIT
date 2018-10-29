package com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

public class UsoDeSuplementosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso_de_suplementos);

        Resources res = getResources();
        setTitle(res.getString(R.string.suplementos_titulo));

        TextView tvUsoDeSuplementos = findViewById(R.id.tvUsoDeSuplementos);
        tvUsoDeSuplementos.setText(Html.fromHtml(getString(R.string.suplementos)));
    }
}
