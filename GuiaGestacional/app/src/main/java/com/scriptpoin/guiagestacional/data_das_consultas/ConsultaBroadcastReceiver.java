package com.scriptpoin.guiagestacional.data_das_consultas;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.scriptpoin.guiagestacional.DataDasConsultasFragment;
import com.scriptpoin.guiagestacional.MenuLateralPrincipalActivity;
import com.scriptpoin.guiagestacional.R;

import java.text.SimpleDateFormat;

/**
 * Created by Willi on 07-Oct-17.
 */

public class ConsultaBroadcastReceiver extends BroadcastReceiver {

    private Consulta consulta;

    @Override
    public void onReceive(Context context, Intent intent) {

        consulta = (Consulta) intent.getSerializableExtra("consulta");

        Intent intentNotificacao = new Intent(context, MenuLateralPrincipalActivity.class);

        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intentNotificacao,
                0
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setTicker("Consulta marcada...");
        builder.setContentTitle("Consulta marcada");
        builder.setContentText("Informações da consulta:");

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.addLine("Informações da consulta:");
        if (consulta != null) {
            style.addLine("Dia: " + formatData.format(consulta.getDataHoraConsulta().getTime()));
            style.addLine("Hora: " + formatHora.format((consulta.getDataHoraConsulta().getTime())));
            style.addLine(consulta.getTipoProfissional() + " " + consulta.getNomeProfissional());
        }
        builder.setStyle(style);

        builder.setSmallIcon(R.drawable.icone_notificacao);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icone_guia_gestacional));

        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setVibrate(new long[]{0, 300, 150, 300});
        builder.setLights(Notification.DEFAULT_LIGHTS, 1000, 500);

        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);


        Notification notification = builder.build();

        notificationManager.notify(150, notification);
    }
}
