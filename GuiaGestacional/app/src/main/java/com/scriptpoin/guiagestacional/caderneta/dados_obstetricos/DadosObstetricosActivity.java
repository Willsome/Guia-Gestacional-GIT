package com.scriptpoin.guiagestacional.caderneta.dados_obstetricos;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.caderneta.dados_pessoais.DadosPessoaisActivity;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.util.Calendar;

public class DadosObstetricosActivity extends AppCompatActivity {

    // VARIÁVEIS DO MÉTODO "pegaDataDumDpp()"
    private TextView doTvDpDum;
    private TextView doTvDpDpp;
    private DatePickerDialog.OnDateSetListener datePickerListener;
    private int i;
    private int dia;
    private int mes;
    private int ano;

    // OUTROS
    private DadosObstetricos dadosObstetricos;
    private DadosObstetricosHelper dadosObstetricosHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_obstetricos);

        setTitle("Editar Dados Obstétricos");

        Intent intent = getIntent();
        dadosObstetricos = (DadosObstetricos) intent.getSerializableExtra("dadosObstetricos");

        dadosObstetricosHelper = new DadosObstetricosHelper(this, 1, null);

        if (dadosObstetricos != null) {
            dadosObstetricosHelper.preencheFormularioDadosObstetricos(dadosObstetricos);
        }

        pegaDataDumDpp();

        Button doBtSalvar = (Button) findViewById(R.id.doBtSalvar);
        doBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    DadosObstetricos dadosObstetricos = dadosObstetricosHelper.pegaDadosObstetricos();

                    DaoCaderneta dao = new DaoCaderneta(DadosObstetricosActivity.this);

                    if (dadosObstetricos.getId() != null) {
                        dao.alteraDadosObstetricos(dadosObstetricos);
                        Toast.makeText(DadosObstetricosActivity.this, "Dados Obstétricos atualizados !", Toast.LENGTH_SHORT).show();
                    } else {
                        dao.salvaDadosObstetricos(dadosObstetricos);
                    }
                    dao.close();

                    finish();

                } catch (Exception e) {
                    Toast.makeText(DadosObstetricosActivity.this, "Existem campos não preenchidos...", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

    private void pegaDataDumDpp() {

        doTvDpDum = dadosObstetricosHelper.getDoTvDpDum();
        doTvDpDum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dadosObstetricos != null) {
                    dia = Integer.valueOf(dadosObstetricos.getDum().substring(0, 2));
                    mes = Integer.valueOf(dadosObstetricos.getDum().substring(3, 5)) - 1;
                    ano = Integer.valueOf(dadosObstetricos.getDum().substring(6, 10));
                } else {
                    Calendar cal = Calendar.getInstance();
                    dia = cal.get(Calendar.DAY_OF_MONTH);
                    mes = cal.get(Calendar.MONTH);
                    ano = cal.get(Calendar.YEAR);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DadosObstetricosActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        datePickerListener,
                        ano, mes, dia
                );

                datePickerDialog.getWindow();
                datePickerDialog.show();

                i = 1;
            }
        });

        doTvDpDpp = dadosObstetricosHelper.getDoTvDpDpp();
        doTvDpDpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dadosObstetricos != null) {
                    dia = Integer.valueOf(dadosObstetricos.getDpp().substring(0, 2));
                    mes = Integer.valueOf(dadosObstetricos.getDpp().substring(3, 5)) - 1;
                    ano = Integer.valueOf(dadosObstetricos.getDpp().substring(6, 10));
                } else {
                    Calendar cal = Calendar.getInstance();
                    dia = cal.get(Calendar.DAY_OF_MONTH);
                    mes = cal.get(Calendar.MONTH);
                    ano = cal.get(Calendar.YEAR);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DadosObstetricosActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        datePickerListener,
                        ano, mes, dia
                );

                datePickerDialog.getWindow();
                datePickerDialog.show();

                i = 2;
            }
        });

        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                mes += 1;

                String data;

                if (dia < 10 && mes < 10) {
                    data = "0" + dia + "/0" + mes + "/" + ano;
                } else if (dia < 10) {
                    data = "0" + dia + "/" + mes + "/" + ano;
                } else if (mes < 10) {
                    data = dia + "/0" + mes + "/" + ano;
                } else {
                    data = dia + "/" + mes + "/" + ano;
                }

                if (i == 1) {
                    doTvDpDum.setText(data);
                } else if (i == 2) {
                    doTvDpDpp.setText(data);
                }
            }
        };
    }
}