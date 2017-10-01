package com.scriptpoin.guiagestacional.caderneta.dados_pessoais;

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
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.util.Calendar;

public class DadosPessoaisActivity extends AppCompatActivity {

    // VARIÁVEIS DO MÉTODO "pegaDataNascimento()"
    private TextView dpTvDpDataNascimento;
    private DatePickerDialog.OnDateSetListener datePickerListener;
    int dia;
    int mes;
    int ano;

    // OUTROS
    private DadosPessoais dadosPessoais;
    private DadosPessoaisHelper dadosPessoaisHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);

        setTitle("Editar Dados Pessoais");

        Intent intent = getIntent();
        dadosPessoais = (DadosPessoais) intent.getSerializableExtra("dadosPessoais");

        dadosPessoaisHelper = new DadosPessoaisHelper(this, 1, null);

        if (dadosPessoais != null) {
            dadosPessoaisHelper.preencheFormularioDadosPessoais(dadosPessoais);
        }

        pegaDataNascimento();

        Button dpBtSalvar = (Button) findViewById(R.id.dpBtSalvar);
        dpBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    DadosPessoais dadosPessoais = dadosPessoaisHelper.pegaFormularioDadosPessoais();

                    DaoCaderneta dao = new DaoCaderneta(DadosPessoaisActivity.this);

                    if (dadosPessoais.getId() != null) {
                        dao.alteraDadosPessoais(dadosPessoais);
                        Toast.makeText(DadosPessoaisActivity.this, "Dados Pessoais atualizados !", Toast.LENGTH_SHORT).show();
                    } else {
                        dao.salvaDadosPessoais(dadosPessoais);
                    }
                    dao.close();

                    finish();

                } catch (Exception e) {
                    Toast.makeText(DadosPessoaisActivity.this, "Existem campos não preenchidos...", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


    }

    public void pegaDataNascimento() {

        dpTvDpDataNascimento = dadosPessoaisHelper.getDpTvDpDataNascimento();
        dpTvDpDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dadosPessoais != null) {
                    dia = Integer.valueOf(dadosPessoais.getDataNascimento().substring(0, 2));
                    mes = Integer.valueOf(dadosPessoais.getDataNascimento().substring(3, 5)) - 1;
                    ano = Integer.valueOf(dadosPessoais.getDataNascimento().substring(6, 10));
                } else {
                    Calendar cal = Calendar.getInstance();
                    dia = cal.get(Calendar.DAY_OF_MONTH);
                    mes = cal.get(Calendar.MONTH);
                    ano = cal.get(Calendar.YEAR);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DadosPessoaisActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        datePickerListener,
                        ano, mes, dia
                );

                datePickerDialog.getWindow();
                datePickerDialog.show();
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

                dpTvDpDataNascimento.setText(data);
            }
        };
    }
}
