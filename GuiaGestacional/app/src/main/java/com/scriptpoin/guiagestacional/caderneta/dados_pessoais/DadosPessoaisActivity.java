package com.scriptpoin.guiagestacional.caderneta.dados_pessoais;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;
import java.util.Calendar;

public class DadosPessoaisActivity extends AppCompatActivity {

    private TextView dpTvDpDataNascimento;

    private DatePickerDialog.OnDateSetListener datePickerListener;

    int dia;
    int mes;
    int ano;

    private DadosPessoaisHelper dph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);

        DaoCaderneta dao = new DaoCaderneta(DadosPessoaisActivity.this);
        final DadosPessoais dp = dao.pegaDadosPessoais();
        dao.close();

        dph = new DadosPessoaisHelper(DadosPessoaisActivity.this, 1, null);

        if(dp.getNome() != null) {
            dph.preencheFormularioDadosPessoais(dp);
        }

        pegaDataNascimento(dp);

        Button dpBtSalvar = (Button) findViewById(R.id.dpBtSalvar);
        dpBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DadosPessoais dadosPessoais = dph.pegaDadosPessoais();

                DaoCaderneta daoCaderneta = new DaoCaderneta(DadosPessoaisActivity.this);
                daoCaderneta.salvaDadosPessoais(dadosPessoais);
                daoCaderneta.close();

                finish();
            }
        });


    }

    public void pegaDataNascimento(final DadosPessoais dp) {
        dpTvDpDataNascimento = dph.getDpTvDpDataNascimento();
        dpTvDpDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dp.getNome() != null) {
                    dia = Integer.valueOf(dp.getDataNascimento().substring(0, 2));
                    mes = Integer.valueOf(dp.getDataNascimento().substring(3, 5)) - 1;
                    ano = Integer.valueOf(dp.getDataNascimento().substring(6, 10));
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

                if(dia < 10 && mes < 10) {
                    data = "0" + dia + "/0" + mes + "/" + ano;
                } else if(dia < 10) {
                    data = "0" + dia + "/" + mes + "/" + ano;
                } else if(mes < 10) {
                    data = dia + "/0" + mes + "/" + ano;
                } else {
                    data = dia + "/" + mes + "/" + ano;
                }

                dp.setDataNascimento(data);
                dpTvDpDataNascimento.setText(dp.getDataNascimento());
            }
        };
    }
}
