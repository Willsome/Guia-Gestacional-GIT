package com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

public class GanhoDePesoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganho_de_peso);

        Resources res = getResources();
        setTitle(res.getString(R.string.ganho_de_peso_titulo));

        TextView tvGanhoDePeso = findViewById(R.id.tvGanhoDePeso);
        tvGanhoDePeso.setText(Html.fromHtml(getString(R.string.ganho_de_peso)));
    }
}
