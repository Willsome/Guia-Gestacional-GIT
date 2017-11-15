package com.scriptpoin.guiagestacional.data_das_consultas;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.scriptpoin.guiagestacional.dao.DaoCaderneta;
import com.scriptpoin.guiagestacional.dao.DaoConsultas;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Willi on 10-Oct-17.
 */

public class BootCompletedConsultaBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {

            DaoConsultas daoConsultas = new DaoConsultas(context);

            ArrayList<Consulta> consultas = daoConsultas.pegaConsultas();

            daoConsultas.close();

            if (consultas.size() == 1) {

                long vinteQuatroHoras = (1000 * 60 * 60) * 24;
//            long dataAtual = System.currentTimeMillis();

//            Intent intent = new Intent("DISPARAR_ALARME");
                Intent intent2 = new Intent(context, ConsultaBroadcastReceiver.class);
                intent2.putExtra("consulta", consultas.get(0));

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        context,
                        0,
                        intent2,
                        0
                );

                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                alarmManager.set(AlarmManager.RTC_WAKEUP, consultas.get(0).getDataHoraConsulta().getTimeInMillis() - vinteQuatroHoras, pendingIntent);
            }

        }
    }
}

