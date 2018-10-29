package com.scriptpoin.gestacaosaudavel.caderneta.ultrassonografia;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.gestacaosaudavel.R;
import com.scriptpoin.gestacaosaudavel.dao.DaoCaderneta;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UltrassonografiaActivity extends AppCompatActivity {

    // // VARIÁVEIS DO MÉTODO "pegaDatasUltrassonografia()"
    private Calendar data;
    private TextView uTvDpData;
    private DatePickerDialog.OnDateSetListener datePickerListener;
    private int dia;
    private int mes;
    private int ano;

    // OUTROS
    private Ultrassonografia ultrassonografia;
    private UltrassonografiaHelper ultrassonografiaHelper;
    private CheckBox uCbResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultrassonografia);

        setTitle("Adicionar");

        Intent intent = getIntent();
        ultrassonografia = (Ultrassonografia) intent.getSerializableExtra("ultrassonografia");

        ultrassonografiaHelper = new UltrassonografiaHelper(this, 1, null);

        // CHECK BOXES
        CheckBox uCbSolicitacao = findViewById(R.id.uCbSolicitacao);
        uCbResultado = findViewById(R.id.uCbResultado);

        ultrassonografiaHelper.marcaResultado(false);
        uCbSolicitacao.setChecked(true);

        uCbSolicitacao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ativado) {
                if (ativado) {
                    uCbResultado.setChecked(false);
                }
            }
        });

        uCbResultado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ativado) {
                if (ativado) {
                    ultrassonografiaHelper.marcaResultado(true);
                } else {
                    ultrassonografiaHelper.marcaResultado(false);
                }
            }
        });


        // SPINNER
        Spinner uSpPlacenta = findViewById(R.id.uSpPlacenta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_placenta,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uSpPlacenta.setAdapter(adapter);
        // SPINNER

        if (ultrassonografia != null) {
            setTitle("Editar");

            // SOLICITAÇÃO OU RESULTADO
            if (ultrassonografia.getSolicitacao() == 0) {
                ultrassonografiaHelper.marcaResultado(true);
                uCbResultado.setEnabled(true);
                uCbResultado.setChecked(true);
                uCbSolicitacao.setChecked(false);
            }

            int posicao = adapter.getPosition(ultrassonografia.getPlacenta());
            ultrassonografiaHelper.preencheFormularioUltrassonografia(ultrassonografia, posicao);
        }

        pegaDatasUltrassonografia();

        Button uBtSalvar = findViewById(R.id.uBtSalvar);
        uBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

//                    Ultrassonografia ultrassonografia = ultrassonografiaHelper.pegaUltrassonografia(data, dataIgDum, dataIgUsg);
                    Ultrassonografia ultrassonografia = ultrassonografiaHelper.pegaUltrassonografia(data);

                    DaoCaderneta dao = new DaoCaderneta(UltrassonografiaActivity.this);

                    Intent intent = new Intent();

                    if (dao.existeConsulta(ultrassonografia.getNumeroConsultaSolicitacao())) {
                        Toast.makeText(UltrassonografiaActivity.this, "A consulta de solicitação da ultra não existe...", Toast.LENGTH_SHORT).show();

                    } else {
                        if (ultrassonografia.getId() != null) {
                            dao.alteraUltrassonografia(ultrassonografia);
                            intent.putExtra("consultaSolicitacao", ultrassonografia.getNumeroConsultaSolicitacao());
                            Toast.makeText(UltrassonografiaActivity.this, "Ultrassonografia atualizada !", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK, intent);
                            dao.close();
                            finish();

                        } else {
                            if (!dao.existeUltra(ultrassonografia.getNumeroConsultaSolicitacao())) {
                                dao.salvaUltrassonografia(ultrassonografia);
                                Toast.makeText(UltrassonografiaActivity.this, "Ultrassonografia adicionada !", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK, intent);
                                dao.close();
                                finish();

                            } else {
                                Toast.makeText(UltrassonografiaActivity.this, "Já existe uma ultrassonografia com a consulta informada...", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(UltrassonografiaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });
    }


    private void pegaDatasUltrassonografia() {

        data = Calendar.getInstance();

        uTvDpData = findViewById(R.id.uTvDpData);
        uTvDpData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ultrassonografia != null && ultrassonografia.getSolicitacao() == 0) {
                    data = ultrassonografia.getData();
                }

                dia = data.get(Calendar.DAY_OF_MONTH);
                mes = data.get(Calendar.MONTH);
                ano = data.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UltrassonografiaActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        datePickerListener,
                        ano, mes, dia
                );

                datePickerDialog.getWindow();
                datePickerDialog.show();

//                i = 1;
            }
        });

//        dataIgDum = Calendar.getInstance();
//
//        uTvDpIgDum = (TextView) findViewById(R.id.uTvDpIgDum);
//        uTvDpIgDum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (ultrassonografia != null && ultrassonografia.getSolicitacao() == 0) {
//
//                    dataIgDum = ultrassonografia.getIgDum();
//                }
//
//                dia = dataIgDum.get(Calendar.DAY_OF_MONTH);
//                mes = dataIgDum.get(Calendar.MONTH);
//                ano = dataIgDum.get(Calendar.YEAR);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        UltrassonografiaActivity.this,
//                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
//                        datePickerListener,
//                        ano, mes, dia
//                );
//
//                datePickerDialog.getWindow();
//                datePickerDialog.show();
//
//                i = 2;
//            }
//        });
//
//        dataIgUsg = Calendar.getInstance();
//
//        uTvDpIgUsg = (TextView) findViewById(R.id.uTvDpIgUsg);
//        uTvDpIgUsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (ultrassonografia != null && ultrassonografia.getSolicitacao() == 0) {
//
//                    dataIgUsg = ultrassonografia.getIgUsg();
//                }
//
//                dia = dataIgUsg.get(Calendar.DAY_OF_MONTH);
//                mes = dataIgUsg.get(Calendar.MONTH);
//                ano = dataIgUsg.get(Calendar.YEAR);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        UltrassonografiaActivity.this,
//                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
//                        datePickerListener,
//                        ano, mes, dia
//                );
//
//                datePickerDialog.getWindow();
//                datePickerDialog.show();
//
//                i = 3;
//            }
//        });

        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {

                SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

//                if (i == 1) {

                data.set(Calendar.DAY_OF_MONTH, dia);
                data.set(Calendar.MONTH, mes);
                data.set(Calendar.YEAR, ano);

                uTvDpData.setText(formataData.format(data.getTime()));

//                } else if (i == 2) {
//
//                    dataIgDum.set(Calendar.DAY_OF_MONTH, dia);
//                    dataIgDum.set(Calendar.MONTH, mes);
//                    dataIgDum.set(Calendar.YEAR, ano);
//
//                    uTvDpIgDum.setText(formataData.format(dataIgDum.getTime()));
//
//                } else if (i == 3) {
//
//                    dataIgUsg.set(Calendar.DAY_OF_MONTH, dia);
//                    dataIgUsg.set(Calendar.MONTH, mes);
//                    dataIgUsg.set(Calendar.YEAR, ano);
//
//                    uTvDpIgUsg.setText(formataData.format(dataIgUsg.getTime()));
//                }
            }
        };
    }
}
