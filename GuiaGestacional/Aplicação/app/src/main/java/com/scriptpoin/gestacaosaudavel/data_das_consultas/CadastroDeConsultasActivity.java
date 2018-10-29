package com.scriptpoin.gestacaosaudavel.data_das_consultas;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.scriptpoin.gestacaosaudavel.R;
import com.scriptpoin.gestacaosaudavel.dao.DaoConsultas;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastroDeConsultasActivity extends AppCompatActivity {

    // VARIÁVEIS DO MÉTODO "pegaDataHoraConsulta()"
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener datePickerListener;
    private TextView dcTvDataConsulta;
    private TimePickerDialog.OnTimeSetListener timePickerListener;
    private TextView dcTvHoraConsulta;
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;

    // OUTROS
    private Consulta consulta;
    private CadastroDeConsultasHelper cadastroDeConsultasHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_consulta);

        setTitle("Adicionar");

        Intent intent = getIntent();
        consulta = (Consulta) intent.getSerializableExtra("consulta");

        cadastroDeConsultasHelper = new CadastroDeConsultasHelper(this);

        // SPINNER
        Spinner dcSpTipoProfissional = (Spinner) findViewById(R.id.dcSpTipoProfissional);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_tipo_profissional,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dcSpTipoProfissional.setAdapter(adapter);
        // SPINNER

        if (consulta != null) {
            setTitle("Editar");
            cadastroDeConsultasHelper.preencheFormularioConsulta(
                    consulta,
                    adapter.getPosition(consulta.getTipoProfissional())
            );
        }

        pegaDataHoraConsulta();

        Button dpBtSalvar = (Button) findViewById(R.id.dpBtSalvar);
        dpBtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    DaoConsultas daoConsultas = new DaoConsultas(CadastroDeConsultasActivity.this);

                    Consulta consulta = cadastroDeConsultasHelper.pegaConsulta(calendar);

//                    if (consulta.getId() != null) {
//                        deletarAlarme();
//                        if (criarAlarme(consulta)) {
//                            daoConsultas.alteraConsulta(consulta);
//                            Toast.makeText(CadastroDeConsultasActivity.this, "A consulta foi atualizada !", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    } else {

                    if (criarAlarme(consulta)) {

                        daoConsultas.salvaConsulta(consulta);
                        daoConsultas.close();
                        Toast.makeText(CadastroDeConsultasActivity.this, "A consulta foi adicionada !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
//                    }


                } catch (Exception e) {
                    Toast.makeText(CadastroDeConsultasActivity.this, "Existem campos não preenchidos...", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

    }

    public void pegaDataHoraConsulta() {

        calendar = Calendar.getInstance();

        dcTvDataConsulta = (TextView) findViewById(R.id.dcTvDataConsulta);
        dcTvDataConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (consulta != null) {

                    calendar = consulta.getDataHoraConsulta();
                }

                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                ano = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CadastroDeConsultasActivity.this,
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

                calendar.set(Calendar.DAY_OF_MONTH, dia);
                calendar.set(Calendar.MONTH, mes);
                calendar.set(Calendar.YEAR, ano);

                String data = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());

                dcTvDataConsulta.setText(data);
            }
        };


        dcTvHoraConsulta = (TextView) findViewById(R.id.dcTvHoraConsulta);
        dcTvHoraConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (consulta != null) {

                    calendar = consulta.getDataHoraConsulta();
                }

                hora = calendar.get(Calendar.HOUR_OF_DAY);
                minuto = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CadastroDeConsultasActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        timePickerListener,
                        hora, minuto,
                        true
                );

                timePickerDialog.getWindow();
                timePickerDialog.show();
            }
        });

        timePickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hora, int minuto) {

                calendar.set(Calendar.HOUR_OF_DAY, hora);
                calendar.set(Calendar.MINUTE, minuto);

                String horaFinal = new SimpleDateFormat("HH:mm").format(calendar.getTime());

                dcTvHoraConsulta.setText(horaFinal);
            }
        };
    }

    public boolean criarAlarme(Consulta consulta) {

        long vinteQuatroHoras = (1000 * 60 * 60) * 24;
        long dataAtual = System.currentTimeMillis();

        if (consulta.getDataHoraConsulta().getTimeInMillis() < dataAtual + vinteQuatroHoras) {
            Toast.makeText(CadastroDeConsultasActivity.this, "Data para alarme inválida...", Toast.LENGTH_SHORT).show();
            return false;
        }

        boolean alarmeAtivo = (PendingIntent.getBroadcast(
                CadastroDeConsultasActivity.this,
                0,
                new Intent(this, ConsultaBroadcastReceiver.class),
                PendingIntent.FLAG_NO_CREATE
        )) == null;

        if (alarmeAtivo) {

//            Intent intent = new Intent("DISPARAR_ALARME");
            Intent intent = new Intent(this, ConsultaBroadcastReceiver.class);
            intent.putExtra("consulta", consulta);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    CadastroDeConsultasActivity.this,
                    0,
                    intent,
                    0
            );

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            alarmManager.set(AlarmManager.RTC_WAKEUP, consulta.getDataHoraConsulta().getTimeInMillis() - vinteQuatroHoras, pendingIntent);

            Toast.makeText(CadastroDeConsultasActivity.this, "Alarme criado !", Toast.LENGTH_SHORT).show();

            return true;

        } else {
            Toast.makeText(CadastroDeConsultasActivity.this, "Já existe um alarme criado...", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

//    public void deletarAlarme() {
//
//        Intent intent = new Intent(this, ConsultaBroadcastReceiver.class);
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                this,
//                0,
//                intent,
//                0
//        );
//
//        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//
//        alarmManager.cancel(pendingIntent);
//        pendingIntent.cancel();
//    }
}
