package com.scriptpoin.guiagestacional.aleitamento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

public class BeneficiosAleitamentoBebeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficios_aleitamento_bebe);

        setTitle("Aleitamento Materno");

        TextView tvBenefícioAleitamentoBebe = (TextView) findViewById(R.id.tvBenefícioAleitamentoBebe);
        tvBenefícioAleitamentoBebe.setText(Html.fromHtml(getString(R.string.beneficioAleitamentoBebe)));

    }
}
