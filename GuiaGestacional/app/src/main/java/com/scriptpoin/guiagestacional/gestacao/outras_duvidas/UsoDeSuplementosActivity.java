package com.scriptpoin.guiagestacional.gestacao.outras_duvidas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

public class UsoDeSuplementosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso_de_suplementos);

        setTitle("Uso de Suplementos");

        TextView tvUsoDeSuplementos = (TextView) findViewById(R.id.tvUsoDeSuplementos);
        tvUsoDeSuplementos.setText(Html.fromHtml(getString(R.string.suplementos)));
    }
}
