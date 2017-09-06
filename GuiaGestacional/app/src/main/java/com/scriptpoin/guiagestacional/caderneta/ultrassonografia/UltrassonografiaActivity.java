package com.scriptpoin.guiagestacional.caderneta.ultrassonografia;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import com.scriptpoin.guiagestacional.R;
import com.scriptpoin.guiagestacional.dao.DaoCaderneta;
import java.util.Calendar;

public class UltrassonografiaActivity extends AppCompatActivity {

    UltrassonografiaHelper uh;
    Ultrassonografia u;

    private TextView uTvDpData;
    private TextView uTvDpIgDum;
    private TextView uTvDpIgUsg;

    private DatePickerDialog.OnDateSetListener datePickerListener;

    private int i;

    private int dia;
    private int mes;
    private int ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultrassonografia);

        pegaDatasUltrassonografia();

        DaoCaderneta dao = new DaoCaderneta(this);
        u = dao.pegaUltrassonografia();
        dao.close();

        uh = new UltrassonografiaHelper(this, 1, null);

        Spinner uSpPlacenta = (Spinner) findViewById(R.id.uSpPlacenta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_placenta,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uSpPlacenta.setAdapter(adapter);

        if(u.getData() != null) {
            int posicao = adapter.getPosition(u.getPlacenta());
            uh.preencheFormularioUltrassonografia(u, posicao);
        }

        Button uBtSalvar = (Button) findViewById(R.id.uBtSalvar);
        uBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ultrassonografia ultrassonografia = uh.pegaUltrassonografia();

                DaoCaderneta dao = new DaoCaderneta(UltrassonografiaActivity.this);
                dao.salvaUltrassonografia(ultrassonografia);
                dao.close();

                finish();
            }
        });
    }


    private void pegaDatasUltrassonografia() {

        uTvDpData = (TextView) findViewById(R.id.uTvDpData);
        uTvDpData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(u.getData() != null) {
                    dia = Integer.valueOf(u.getData().substring(0, 2));
                    mes = Integer.valueOf(u.getData().substring(3, 5)) - 1;
                    ano = Integer.valueOf(u.getData().substring(6, 10));
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

                if(u.getIgDum() != null) {
                    dia = Integer.valueOf(u.getIgDum().substring(0, 2));
                    mes = Integer.valueOf(u.getIgDum().substring(3, 5)) - 1;
                    ano = Integer.valueOf(u.getIgDum().substring(6, 10));
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

                if(u.getIgUsg() != null) {
                    dia = Integer.valueOf(u.getIgUsg().substring(0, 2));
                    mes = Integer.valueOf(u.getIgUsg().substring(3, 5)) - 1;
                    ano = Integer.valueOf(u.getIgUsg().substring(6, 10));
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
                    u.setData(data);
                    uTvDpData.setText(u.getData());
                } else if(i == 2) {
                    u.setIgDum(data);
                    uTvDpIgDum.setText(u.getIgDum());
                } else if(i ==3) {
                    u.setIgUsg(data);
                    uTvDpIgUsg.setText(u.getIgUsg());
                }
            }
        };
    }
}
