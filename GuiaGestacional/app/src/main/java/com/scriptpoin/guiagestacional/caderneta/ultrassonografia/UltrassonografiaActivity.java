package com.scriptpoin.guiagestacional.caderneta.ultrassonografia;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;

import java.util.Calendar;

public class UltrassonografiaActivity extends AppCompatActivity {

    // // VARIÁVEIS DO MÉTODO "pegaDatasUltrassonografia()"
    private TextView uTvDpData;
    private TextView uTvDpIgDum;
    private TextView uTvDpIgUsg;
    private DatePickerDialog.OnDateSetListener datePickerListener;
    private int i;
    private int dia;
    private int mes;
    private int ano;

    // OUTROS
    private Ultrassonografia ultrassonografia;
    UltrassonografiaHelper ultrassonografiaHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultrassonografia);

        setTitle("Editar Ultrassonografia");

        Intent intent = getIntent();
        ultrassonografia = (Ultrassonografia) intent.getSerializableExtra("ultrassonografia");

        ultrassonografiaHelper = new UltrassonografiaHelper(this, 1, null);

        // SPINNER
        Spinner uSpPlacenta = (Spinner) findViewById(R.id.uSpPlacenta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_placenta,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uSpPlacenta.setAdapter(adapter);
        // SPINNER

        if (ultrassonografia != null) {
            int posicao = adapter.getPosition(ultrassonografia.getPlacenta());
            ultrassonografiaHelper.preencheFormularioUltrassonografia(ultrassonografia, posicao);
        }

        pegaDatasUltrassonografia();

        Button uBtSalvar = (Button) findViewById(R.id.uBtSalvar);
        uBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Ultrassonografia ultrassonografia = ultrassonografiaHelper.pegaUltrassonografia();

                    DaoCaderneta dao = new DaoCaderneta(UltrassonografiaActivity.this);

                    if (ultrassonografia.getId() != null) {
                        dao.alteraUltrassonografia(ultrassonografia);
                        Toast.makeText(UltrassonografiaActivity.this, "Ultrassonografia atualizada !", Toast.LENGTH_SHORT).show();
                    } else {
                        dao.salvaUltrassonografia(ultrassonografia);
                    }
                    dao.close();

                    finish();

                } catch (Exception e) {
                    Toast.makeText(UltrassonografiaActivity.this, "Existem campos não preenchidos...", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
    }


    private void pegaDatasUltrassonografia() {

        uTvDpData = (TextView) findViewById(R.id.uTvDpData);
        uTvDpData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ultrassonografia != null) {
                    dia = Integer.valueOf(ultrassonografia.getData().substring(0, 2));
                    mes = Integer.valueOf(ultrassonografia.getData().substring(3, 5)) - 1;
                    ano = Integer.valueOf(ultrassonografia.getData().substring(6, 10));
                } else {
                    Calendar cal = Calendar.getInstance();
                    dia = cal.get(Calendar.DAY_OF_MONTH);
                    mes = cal.get(Calendar.MONTH);
                    ano = cal.get(Calendar.YEAR);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UltrassonografiaActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        datePickerListener,
                        ano, mes, dia
                );

                datePickerDialog.getWindow();
                datePickerDialog.show();

                i = 1;
            }
        });

        uTvDpIgDum = (TextView) findViewById(R.id.uTvDpIgDum);
        uTvDpIgDum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ultrassonografia != null) {
                    dia = Integer.valueOf(ultrassonografia.getIgDum().substring(0, 2));
                    mes = Integer.valueOf(ultrassonografia.getIgDum().substring(3, 5)) - 1;
                    ano = Integer.valueOf(ultrassonografia.getIgDum().substring(6, 10));
                } else {
                    Calendar cal = Calendar.getInstance();
                    dia = cal.get(Calendar.DAY_OF_MONTH);
                    mes = cal.get(Calendar.MONTH);
                    ano = cal.get(Calendar.YEAR);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UltrassonografiaActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        datePickerListener,
                        ano, mes, dia
                );

                datePickerDialog.getWindow();
                datePickerDialog.show();

                i = 2;
            }
        });

        uTvDpIgUsg = (TextView) findViewById(R.id.uTvDpIgUsg);
        uTvDpIgUsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ultrassonografia != null) {
                    dia = Integer.valueOf(ultrassonografia.getIgUsg().substring(0, 2));
                    mes = Integer.valueOf(ultrassonografia.getIgUsg().substring(3, 5)) - 1;
                    ano = Integer.valueOf(ultrassonografia.getIgUsg().substring(6, 10));
                } else {
                    Calendar cal = Calendar.getInstance();
                    dia = cal.get(Calendar.DAY_OF_MONTH);
                    mes = cal.get(Calendar.MONTH);
                    ano = cal.get(Calendar.YEAR);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UltrassonografiaActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        datePickerListener,
                        ano, mes, dia
                );

                datePickerDialog.getWindow();
                datePickerDialog.show();

                i = 3;
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
                    uTvDpData.setText(data);
                } else if (i == 2) {
                    uTvDpIgDum.setText(data);
                } else if (i == 3) {
                    uTvDpIgUsg.setText(data);
                }
            }
        };
    }
}
