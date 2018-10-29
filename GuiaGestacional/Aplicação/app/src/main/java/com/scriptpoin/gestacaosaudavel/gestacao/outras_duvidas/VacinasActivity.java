package com.scriptpoin.gestacaosaudavel.gestacao.outras_duvidas;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

public class VacinasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);

        Resources res = getResources();
        setTitle(res.getString(R.string.vacinas_titulo));

        TextView tvVacinas = (TextView) findViewById(R.id.tvVacinas);
        tvVacinas.setText(Html.fromHtml(getString(R.string.vacinas)));
    }
}
