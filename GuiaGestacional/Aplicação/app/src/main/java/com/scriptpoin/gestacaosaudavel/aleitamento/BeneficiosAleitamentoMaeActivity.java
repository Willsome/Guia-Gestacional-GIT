package com.scriptpoin.gestacaosaudavel.aleitamento;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

public class BeneficiosAleitamentoMaeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficios_aleitamento_mae);

        Resources res = getResources();
        setTitle(res.getString(R.string.aleitamento_materno));

        TextView tvBeneficioAleitamentoMae = findViewById(R.id.tvBenefícioAleitamentoMae);
        tvBeneficioAleitamentoMae.setText(Html.fromHtml(getString(R.string.beneficio_aleitamento_mae)));

    }
}
