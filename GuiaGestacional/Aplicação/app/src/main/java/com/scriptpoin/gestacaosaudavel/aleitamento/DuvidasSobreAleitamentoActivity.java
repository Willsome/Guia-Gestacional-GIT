package com.scriptpoin.gestacaosaudavel.aleitamento;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

public class DuvidasSobreAleitamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duvidas_sobre_aleitamento);

        Resources res = getResources();
        setTitle(res.getString(R.string.aleitamento_materno));

        TextView tvDuvidasAleitamento = findViewById(R.id.tvDuvidasAleitamento);
        tvDuvidasAleitamento.setText(Html.fromHtml(getString(R.string.duvidasAleitamento)));

    }
}
