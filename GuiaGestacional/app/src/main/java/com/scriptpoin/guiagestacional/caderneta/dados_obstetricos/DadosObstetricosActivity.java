package com.scriptpoin.guiagestacional.caderneta.dados_obstetricos;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;
import java.util.Calendar;

public class DadosObstetricosActivity extends AppCompatActivity {

    private DadosObstetricosHelper doh;

    private TextView doTvDpDum;
    private TextView doTvDpDpp;

    private DatePickerDialog.OnDateSetListener datePickerListener;

    private DadosObstetricos dadosObstetricos;

    private int i;

    private int dia;
    private int mes;
    private int ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_obstetricos);

        DaoCaderneta dao = new DaoCaderneta(DadosObstetricosActivity.this);

        doh = new DadosObstetricosHelper(DadosObstetricosActivity.this, 1, null);

        dadosObstetricos = dao.pegaDadosObstetricos();

        dao.close();

        if(dadosObstetricos.getDum() != null) {
            doh.preencheFormularioDadosObstetricos(dadosObstetricos);
        } else {
            dadosObstetricos = new DadosObstetricos();
        }


        pegaDataDumDpp();

        Button doBtSalvar = (Button) findViewById(R.id.doBtSalvar);
        doBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doh.setDadosObstetricos(dadosObstetricos);

                DaoCaderneta dao = new DaoCaderneta(DadosObstetricosActivity.this);
                dao.salvaDadosObstetricos(dadosObstetricos);
                dao.close();

                finish();
            }
        });

    }

    private void pegaDataDumDpp() {
        doTvDpDum = doh.getDoTvDpDum();
        doTvDpDum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dadosObstetricos.getDum() != null) {
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

        doTvDpDpp = doh.getDoTvDpDpp();
        doTvDpDpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dadosObstetricos.getDpp() != null) {
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

                if(dia < 10 && mes < 10) {
                    data = "0" + dia + "/0" + mes + "/" + ano;
                } else if(dia < 10) {
                    data = "0" + dia + "/" + mes + "/" + ano;
                } else if(mes < 10) {
                    data = dia + "/0" + mes + "/" + ano;
                } else {
                    data = dia + "/" + mes + "/" + ano;
                }

                if(i == 1) {
                    dadosObstetricos.setDum(data);
                    doTvDpDum.setText(dadosObstetricos.getDum());
                } else if(i == 2) {
                    dadosObstetricos.setDpp(data);
                    doTvDpDpp.setText(dadosObstetricos.getDpp());
                }
            }
        };
    }
}