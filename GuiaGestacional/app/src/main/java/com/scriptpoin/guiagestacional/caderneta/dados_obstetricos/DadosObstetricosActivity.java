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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DadosObstetricosActivity extends AppCompatActivity {

    // VARIÁVEIS DO MÉTODO "pegaDataDumDpp()"
    private Calendar dataDum;
    private Calendar dataDpp;
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

        setTitle("Adicionar");

        Intent intent = getIntent();
        dadosObstetricos = (DadosObstetricos) intent.getSerializableExtra("dadosObstetricos");

        dadosObstetricosHelper = new DadosObstetricosHelper(this, 1, null);

        if (dadosObstetricos != null) {
            setTitle("Editar");
            dadosObstetricosHelper.preencheFormularioDadosObstetricos(dadosObstetricos);
        }

        pegaDataDumDpp();

        Button doBtSalvar = (Button) findViewById(R.id.doBtSalvar);
        doBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    DadosObstetricos dadosObstetricos = dadosObstetricosHelper.pegaDadosObstetricos(dataDum, dataDpp);

                    DaoCaderneta dao = new DaoCaderneta(DadosObstetricosActivity.this);

                    if (dadosObstetricos.getId() != null) {
                        dao.alteraDadosObstetricos(dadosObstetricos);
                        Toast.makeText(DadosObstetricosActivity.this, "Dados Obstétricos atualizados !", Toast.LENGTH_SHORT).show();
                    } else {
                        dao.salvaDadosObstetricos(dadosObstetricos);
                        Toast.makeText(DadosObstetricosActivity.this, "Dados Obstétricos adicionados !", Toast.LENGTH_SHORT).show();
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

        dataDum = Calendar.getInstance();

        doTvDpDum = dadosObstetricosHelper.getDoTvDpDum();
        doTvDpDum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dadosObstetricos != null) {

                    dataDum = dadosObstetricos.getDum();
                }

                dia = dataDum.get(Calendar.DAY_OF_MONTH);
                mes = dataDum.get(Calendar.MONTH);
                ano = dataDum.get(Calendar.YEAR);

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

        dataDpp = Calendar.getInstance();

        doTvDpDpp = dadosObstetricosHelper.getDoTvDpDpp();
        doTvDpDpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dadosObstetricos != null) {

                    dataDpp = dadosObstetricos.getDpp();

                }

                dia = dataDpp.get(Calendar.DAY_OF_MONTH);
                mes = dataDpp.get(Calendar.MONTH);
                ano = dataDpp.get(Calendar.YEAR);


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

                SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

                if (i == 1) {

                    dataDum.set(Calendar.DAY_OF_MONTH, dia);
                    dataDum.set(Calendar.MONTH, mes);
                    dataDum.set(Calendar.YEAR, ano);

                    doTvDpDum.setText(formataData.format(dataDum.getTime()));

                } else if (i == 2) {

                    dataDpp.set(Calendar.DAY_OF_MONTH, dia);
                    dataDpp.set(Calendar.MONTH, mes);
                    dataDpp.set(Calendar.YEAR, ano);

                    doTvDpDpp.setText(formataData.format(dataDpp.getTime()));
                }
            }
        };
    }
}