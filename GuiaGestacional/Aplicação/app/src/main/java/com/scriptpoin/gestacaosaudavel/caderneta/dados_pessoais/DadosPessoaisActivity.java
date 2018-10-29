package com.scriptpoin.gestacaosaudavel.caderneta.dados_pessoais;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.gestacaosaudavel.R;
import com.scriptpoin.gestacaosaudavel.dao.DaoCaderneta;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DadosPessoaisActivity extends AppCompatActivity {

    // VARIÁVEIS DO MÉTODO "pegaDataNascimento()"
    private Calendar dataNascimento;
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

        setTitle("Adicionar");

        Intent intent = getIntent();
        dadosPessoais = (DadosPessoais) intent.getSerializableExtra("dadosPessoais");

        dadosPessoaisHelper = new DadosPessoaisHelper(this, 1, null);

        if (dadosPessoais != null) {
            setTitle("Editar");
            dadosPessoaisHelper.preencheFormularioDadosPessoais(dadosPessoais);
        }

        pegaDataNascimento();

        Button dpBtSalvar = findViewById(R.id.dpBtSalvar);
        dpBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    DadosPessoais dadosPessoais = dadosPessoaisHelper.pegaFormularioDadosPessoais(dataNascimento);

                    DaoCaderneta dao = new DaoCaderneta(DadosPessoaisActivity.this);

                    if (dadosPessoais.getId() != null) {
                        dao.alteraDadosPessoais(dadosPessoais);
                        Toast.makeText(DadosPessoaisActivity.this, "Dados Pessoais atualizados !", Toast.LENGTH_SHORT).show();
                    } else {
                        dao.salvaDadosPessoais(dadosPessoais);
                        Toast.makeText(DadosPessoaisActivity.this, "Dados Pessoais adicionados !", Toast.LENGTH_SHORT).show();
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

        dataNascimento = Calendar.getInstance();

        dpTvDpDataNascimento = dadosPessoaisHelper.getDpTvDpDataNascimento();
        dpTvDpDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dadosPessoais != null) {

                    dataNascimento = dadosPessoais.getDataNascimento();
                }

                dia = dataNascimento.get(Calendar.DAY_OF_MONTH);
                mes = dataNascimento.get(Calendar.MONTH);
                ano = dataNascimento.get(Calendar.YEAR);

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

                dataNascimento.set(Calendar.DAY_OF_MONTH, dia);
                dataNascimento.set(Calendar.MONTH, mes);
                dataNascimento.set(Calendar.YEAR, ano);

                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento.getTime());

                dpTvDpDataNascimento.setText(data);
            }
        };
    }
}
