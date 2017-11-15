package com.scriptpoin.guiagestacional.gestacao.outras_duvidas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

public class GanhoDePesoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganho_de_peso);

        setTitle("Ganho de Peso");

        TextView tvGanhoDePeso = (TextView) findViewById(R.id.tvGanhoDePeso);
        tvGanhoDePeso.setText(Html.fromHtml(getString(R.string.ganhoDePeso)));
    }
}
