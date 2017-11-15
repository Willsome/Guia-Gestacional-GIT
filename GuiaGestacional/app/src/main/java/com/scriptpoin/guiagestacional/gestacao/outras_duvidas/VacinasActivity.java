package com.scriptpoin.guiagestacional.gestacao.outras_duvidas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

public class VacinasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);

        setTitle("Vacinas");

        TextView tvVacinas = (TextView) findViewById(R.id.tvVacinas);
        tvVacinas.setText(Html.fromHtml(getString(R.string.vacinas)));
    }
}
