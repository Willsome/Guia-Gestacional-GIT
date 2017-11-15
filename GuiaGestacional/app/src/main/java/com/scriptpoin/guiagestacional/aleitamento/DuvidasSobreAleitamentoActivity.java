package com.scriptpoin.guiagestacional.aleitamento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

public class DuvidasSobreAleitamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duvidas_sobre_aleitamento);

        setTitle("Aleitamento Materno");

        TextView tvDuvidasAleitamento = (TextView) findViewById(R.id.tvDuvidasAleitamento);
        tvDuvidasAleitamento.setText(Html.fromHtml(getString(R.string.duvidasAleitamento)));

    }
}
