package com.scriptpoin.guiagestacional.caderneta.consultas_mensais;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.CadernetaFragment;
import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.caderneta.dados_obstetricos.DadosObstetricosActivity;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.io.Serializable;
import java.util.Calendar;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class ConsultasMensaisActivity extends AppCompatActivity {

    // VARIÁVEIS DO MÉTODO "pegaDataNascimento()"
    private DatePickerDialog.OnDateSetListener datePickerListener;
    private TextView cmTvFormDataConsulta;
    private int dia;
    private int mes;
    private int ano;

    // OUTROS
    private ConsultasMensais consultaMensal;
    private ConsultasMensaisHelper consultasMensaisHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas_mensais);

        setTitle("Editar Consultas Mensais");

        Intent intent = getIntent();
        consultaMensal = (ConsultasMensais) intent.getSerializableExtra("consultaMensal");

        // SPINNER
        Spinner cmSpPosicaoFetal = (Spinner) findViewById(R.id.cmSpPosicaoFetal);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_posicao_fetal,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmSpPosicaoFetal.setAdapter(adapter);
        // SPINNER

        consultasMensaisHelper = new ConsultasMensaisHelper(this, 1, null);

        if (consultaMensal != null) {
            consultasMensaisHelper.preencheFormularioConsultasMensais(
                    consultaMensal,
                    adapter.getPosition(consultaMensal.getPosicaoFetal())
            );
        }

        pegaDataConsulta(consultaMensal);

        Button cmBtSalvar = (Button) findViewById(R.id.cmBtSalvar);
        cmBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    ConsultasMensais consultaMensal = consultasMensaisHelper.pegaConsultasMensais();

                    DaoCaderneta dao = new DaoCaderneta(ConsultasMensaisActivity.this);

                    Intent intent = new Intent();

                    if (consultaMensal.getId() != null) {
                        dao.alteraConsultasMensais(consultaMensal);
                        intent.putExtra("numeroConsulta", consultaMensal.getNumeroConsulta());
                        Toast.makeText(ConsultasMensaisActivity.this, "A consulta foi atualizada !", Toast.LENGTH_SHORT).show();
                    } else {
                        dao.salvaConsultasMensais(consultaMensal);
                        Toast.makeText(ConsultasMensaisActivity.this, "A ccnsulta adicionada !", Toast.LENGTH_SHORT).show();
                    }
                    dao.close();

                    setResult(RESULT_OK, intent);

                    finish();

                } catch (Exception e) {
                    Toast.makeText(ConsultasMensaisActivity.this, "Existem campos não preenchidos...", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

    private void pegaDataConsulta(final ConsultasMensais consultaMensal) {

        cmTvFormDataConsulta = (TextView) findViewById(R.id.cmTvFormDataConsulta);
        cmTvFormDataConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (consultaMensal != null) {
                    dia = Integer.valueOf(consultaMensal.getDataConsulta().substring(0, 2));
                    mes = Integer.valueOf(consultaMensal.getDataConsulta().substring(3, 5)) - 1;
                    ano = Integer.valueOf(consultaMensal.getDataConsulta().substring(6, 10));
                } else {
                    Calendar cal = Calendar.getInstance();
                    dia = cal.get(Calendar.DAY_OF_MONTH);
                    mes = cal.get(Calendar.MONTH);
                    ano = cal.get(Calendar.YEAR);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ConsultasMensaisActivity.this,
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

                cmTvFormDataConsulta.setText(data);
            }
        };
    }
}