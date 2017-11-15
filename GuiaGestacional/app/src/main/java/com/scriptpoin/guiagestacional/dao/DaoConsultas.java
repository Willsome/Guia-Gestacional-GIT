package com.scriptpoin.guiagestacional.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.scriptpoin.guiagestacional.data_das_consultas.Consulta;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Willi on 06-Oct-17.
 */

public class DaoConsultas extends SQLiteOpenHelper {

    public DaoConsultas(Context context) {
        super(context, "consultasDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criaTabelaConsultas = "CREATE TABLE IF NOT EXISTS Consultas(" +
                "id INTEGER PRIMARY KEY," +
                "numeroConsulta INTEGER NOT NULL," +
                "dataHoraConsulta DATE NOT NULL," +
                "tipoProfissional TEXT NOT NULL," +
                "nomeProfissional TEXT NOT NULL);";

        db.execSQL(criaTabelaConsultas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Consultas");
        onCreate(db);
    }

    public void salvaConsulta(Consulta consulta) {

        SQLiteDatabase db = getWritableDatabase();

        db.insert("Consultas", null, getContentValuesConsulta(consulta));
    }

    public void alteraConsulta(Consulta consulta) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(consulta.getId())};

        db.update("Consultas", getContentValuesConsulta(consulta), "id=?", params);
    }

    @NonNull
    public ContentValues getContentValuesConsulta(Consulta consulta) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("numeroConsulta", consulta.getNumeroConsulta());
        contentValues.put("dataHoraConsulta", consulta.getDataHoraConsulta().getTimeInMillis());
        contentValues.put("tipoProfissional", consulta.getTipoProfissional());
        contentValues.put("nomeProfissional", consulta.getNomeProfissional());
        return contentValues;
    }

    public ArrayList<Consulta> pegaConsultas() {

        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Consulta> consultas = new ArrayList<>();

        String sql = "SELECT * FROM Consultas";

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            Consulta consulta = new Consulta();
            consulta.setId(c.getLong(c.getColumnIndex("id")));
            consulta.setNumeroConsulta(c.getInt(c.getColumnIndex("numeroConsulta")));

            Calendar calendarDataHora = Calendar.getInstance();
            calendarDataHora.setTimeInMillis(c.getLong(c.getColumnIndex("dataHoraConsulta")));
            consulta.setDataHoraConsulta(calendarDataHora);

            consulta.setTipoProfissional(c.getString(c.getColumnIndex("tipoProfissional")));

            consulta.setNomeProfissional(c.getString(c.getColumnIndex("nomeProfissional")));

            consultas.add(consulta);
        }
        c.close();

        return consultas;
    }

    public boolean deletaConsulta(Consulta consulta) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(consulta.getId())};

        try {

            db.delete("Consultas", "id=?", params);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
