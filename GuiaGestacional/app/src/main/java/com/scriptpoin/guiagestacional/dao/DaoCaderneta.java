package com.scriptpoin.guiagestacional.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.scriptpoin.guiagestacional.caderneta.consultas_mensais.ConsultasMensais;
import com.scriptpoin.guiagestacional.caderneta.dados_obstetricos.DadosObstetricos;
import com.scriptpoin.guiagestacional.caderneta.dados_pessoais.DadosPessoais;
import com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados.ExamesSolicitadosResultados;
import com.scriptpoin.guiagestacional.caderneta.ultrassonografia.Ultrassonografia;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Willi on 20-Aug-17.
 */

public class DaoCaderneta extends SQLiteOpenHelper {

    public DaoCaderneta(Context context) {
        super(context, "cadernetaDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criaTabelaDadosPessoais =
                "CREATE TABLE IF NOT EXISTS DadosPessoais(" +
                        "id INTEGER PRIMARY KEY," +
                        "nome TEXT NOT NULL," +
                        "dataNascimento TEXT NOT NULL," +
                        "idade INT NOT NULL," +
                        "endereco TEXT NOT NULL," +
                        "nomeCompanheiro TEXT NOT NULL);";


        String criaTabelaDadosObstetricos =
                "CREATE TABLE IF NOT EXISTS DadosObstetricos(" +
                        "id INTEGER PRIMARY KEY," +
                        "dum TEXT NOT NULL," +
                        "dpp TEXT NOT NULL);";


        String criaTabelaExamesSolicitadosResultados =
                "CREATE TABLE IF NOT EXISTS ExamesSolicitadosResultados(" +
                        "id INTEGER PRIMARY KEY," +
                        "abo INT NOT NULL," +
                        "glicemiaJejum INT NOT NULL, " +
                        "toleranciaGlicose INT NOT NULL, " +
                        "sifilis INT NOT NULL, " +
                        "vdrl INT NOT NULL, " +
                        "hiv INT NOT NULL, " +
                        "hepatiteBC INT NOT NULL, " +
                        "hbsag INT NOT NULL, " +
                        "toxoplasmose INT NOT NULL, " +
                        "hemoglobina INT NOT NULL, " +
                        "urinaEas INT NOT NULL, " +
                        "urinaCultura INT NOT NULL, " +
                        "coombs INT NOT NULL);";


        String criaTabelaUltrassonografia =
                "CREATE TABLE IF NOT EXISTS Ultrassonografia(" +
                        "id INTEGER PRIMARY KEY," +
                        "data TEXT NOT NULL," +
                        "igDum TEXT NOT NULL," +
                        "igUsg TEXT NOT NULL," +
                        "pesoFetal REAL NOT NULL," +
                        "placenta TEXT NOT NULL," +
                        "liquidoAmniotico REAL NOT NULL);";


        String criaTabelaUsoDeMedicamento =
                "CREATE TABLE IF NOT EXISTS UsoDeMedicamento(" +
                        "id INTEGER PRIMARY KEY," +
                        "medicamento TEXT NOT NULL);";


        String criaTabelaConsultasMensais =
                "CREATE TABLE IF NOT EXISTS ConsultasMensais(" +
                        "id INTEGER PRIMARY KEY," +
                        "numeroConsulta INTEGER NOT NULL," +
                        "dataConsulta TEXT NOT NULL," +
                        "queixa TEXT NOT NULL," +
                        "ig REAL NOT NULL," +
                        "peso REAL NOT NULL," +
                        "imc REAL NOT NULL," +
                        "edema TEXT NOT NULL," +
                        "paI REAL NOT NULL," +
                        "paII REAL NOT NULL," +
                        "alturaUterina INTEGER NOT NULL," +
                        "posicaoFetal TEXT NOT NULL," +
                        "bcf INTEGER NOT NULL," +
                        "movFetal TEXT NOT NULL," +
                        "nomeDoProfissional TEXT NOT NULL);";


        db.execSQL(criaTabelaDadosPessoais);
        db.execSQL(criaTabelaDadosObstetricos);
        db.execSQL(criaTabelaExamesSolicitadosResultados);
        db.execSQL(criaTabelaUltrassonografia);
        db.execSQL(criaTabelaUsoDeMedicamento);
        db.execSQL(criaTabelaConsultasMensais);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DadosPessoais");
        db.execSQL("DROP TABLE IF EXISTS DadosObstetricos");
        db.execSQL("DROP TABLE IF EXISTS ExamesSolicitadosResultados");
        db.execSQL("DROP TABLE IF EXISTS Ultrassonografia");
        db.execSQL("DROP TABLE IF EXISTS UsoDeMedicamento");
        db.execSQL("DROP TABLE IF EXISTS ConsultasMensais");
        onCreate(db);
    }


    // ***** DADOS PESSOAIS ***** //
    public void salvaDadosPessoais(DadosPessoais dadosPessoais) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesDadosPessoais(dadosPessoais);

        db.insert("DadosPessoais", null, contentValues);
    }

    public void alteraDadosPessoais(DadosPessoais dadosPessoais) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesDadosPessoais(dadosPessoais);

        String[] params = {String.valueOf(dadosPessoais.getId())};

        db.update("DadosPessoais", contentValues, "id=?", params);
    }

    @NonNull
    public ContentValues getContentValuesDadosPessoais(DadosPessoais dados) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", dados.getNome());
        contentValues.put("dataNascimento", dados.getDataNascimento());
        contentValues.put("idade", dados.getIdade());
        contentValues.put("endereco", dados.getEndereco());
        contentValues.put("nomeCompanheiro", dados.getNomeCompanheiro());
        return contentValues;
    }

    public DadosPessoais pegaDadosPessoais() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM DadosPessoais";

        DadosPessoais dadosPessoais = null;

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            dadosPessoais = new DadosPessoais();
            dadosPessoais.setId(c.getLong(c.getColumnIndex("id")));
            dadosPessoais.setNome(c.getString(c.getColumnIndex("nome")));
            dadosPessoais.setDataNascimento(c.getString(c.getColumnIndex("dataNascimento")));
            dadosPessoais.setIdade(c.getInt(c.getColumnIndex("idade")));
            dadosPessoais.setEndereco(c.getString(c.getColumnIndex("endereco")));
            dadosPessoais.setNomeCompanheiro(c.getString(c.getColumnIndex("nomeCompanheiro")));
        }
        c.close();

        return dadosPessoais;
    }


    // ***** DADOS OBSTETRICOS ***** //
    public void salvaDadosObstetricos(DadosObstetricos dadosObstetricos) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesDadosObstetricos(dadosObstetricos);

        db.insert("DadosObstetricos", null, contentValues);
    }

    public void alteraDadosObstetricos(DadosObstetricos dadosObstetricos) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesDadosObstetricos(dadosObstetricos);

        String[] params = {String.valueOf(dadosObstetricos.getId())};

        db.update("DadosObstetricos", contentValues, "id=?", params);
    }

    @NonNull
    public ContentValues getContentValuesDadosObstetricos(DadosObstetricos dadosObstetricos) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("dum", dadosObstetricos.getDum());
        contentValues.put("dpp", dadosObstetricos.getDpp());
        return contentValues;
    }

    public DadosObstetricos pegaDadosObstetricos() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM DadosObstetricos";

        DadosObstetricos dadosObstetricos = null;

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            dadosObstetricos = new DadosObstetricos();
            dadosObstetricos.setId(c.getLong(c.getColumnIndex("id")));
            dadosObstetricos.setDum(c.getString(c.getColumnIndex("dum")));
            dadosObstetricos.setDpp(c.getString(c.getColumnIndex("dpp")));
        }
        c.close();

        return dadosObstetricos;
    }


    // ***** EXAMES SOLICITADOS/RESULTADOS ***** //
    public void salvaExamesSolicitadosResultados(ExamesSolicitadosResultados examesSolicitadosResultados) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesExamesSolicitadosResultados(examesSolicitadosResultados);

        db.insert("ExamesSolicitadosResultados", null, contentValues);
    }

    public void alteraExamesSolicitadosResultados(ExamesSolicitadosResultados examesSolicitadosResultados) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesExamesSolicitadosResultados(examesSolicitadosResultados);

        String[] params = {String.valueOf(examesSolicitadosResultados.getId())};

        db.update("ExamesSolicitadosResultados", contentValues, "id=?", params);
    }

    @NonNull
    public ContentValues getContentValuesExamesSolicitadosResultados(ExamesSolicitadosResultados es) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("abo", es.getAboRh());
        contentValues.put("glicemiaJejum", es.getGlicemiaJejum());
        contentValues.put("toleranciaGlicose", es.getToleranciaGlicose());
        contentValues.put("sifilis", es.getSifilis());
        contentValues.put("vdrl", es.getVdrl());
        contentValues.put("hiv", es.getHiv());
        contentValues.put("hepatiteBC", es.getHepatiteBC());
        contentValues.put("hbsag", es.getHbsag());
        contentValues.put("toxoplasmose", es.getToxoplasmose());
        contentValues.put("hemoglobina", es.getHemoglobina());
        contentValues.put("urinaEas", es.getUrinaEas());
        contentValues.put("urinaCultura", es.getUrinaCultura());
        contentValues.put("coombs", es.getCoombs());
        return contentValues;
    }

    public ExamesSolicitadosResultados pegaExamesSolicitadosResultados() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM ExamesSolicitadosResultados";

        ExamesSolicitadosResultados examesSolicitadosResultados = null;

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            examesSolicitadosResultados = new ExamesSolicitadosResultados();
            examesSolicitadosResultados.setId(c.getLong(c.getColumnIndex("id")));
            examesSolicitadosResultados.setAboRh(c.getInt(c.getColumnIndex("abo")));
            examesSolicitadosResultados.setGlicemiaJejum(c.getInt(c.getColumnIndex("glicemiaJejum")));
            examesSolicitadosResultados.setToleranciaGlicose(c.getInt(c.getColumnIndex("toleranciaGlicose")));
            examesSolicitadosResultados.setSifilis(c.getInt(c.getColumnIndex("sifilis")));
            examesSolicitadosResultados.setVdrl(c.getInt(c.getColumnIndex("vdrl")));
            examesSolicitadosResultados.setHiv(c.getInt(c.getColumnIndex("hiv")));
            examesSolicitadosResultados.setHepatiteBC(c.getInt(c.getColumnIndex("hepatiteBC")));
            examesSolicitadosResultados.setHbsag(c.getInt(c.getColumnIndex("hbsag")));
            examesSolicitadosResultados.setToxoplasmose(c.getInt(c.getColumnIndex("toxoplasmose")));
            examesSolicitadosResultados.setHemoglobina(c.getInt(c.getColumnIndex("hemoglobina")));
            examesSolicitadosResultados.setUrinaEas(c.getInt(c.getColumnIndex("urinaEas")));
            examesSolicitadosResultados.setUrinaCultura(c.getInt(c.getColumnIndex("urinaCultura")));
            examesSolicitadosResultados.setCoombs(c.getInt(c.getColumnIndex("coombs")));
        }
        c.close();

        return examesSolicitadosResultados;
    }


    // ***** ULTRASSONOGRAFIA ***** //
    public void salvaUltrassonografia(Ultrassonografia ultrassonografia) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesUltrassonografia(ultrassonografia);

        db.insert("Ultrassonografia", null, contentValues);
    }

    public void alteraUltrassonografia(Ultrassonografia ultrassonografia) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesUltrassonografia(ultrassonografia);

        String[] params = {String.valueOf(ultrassonografia.getId())};

        db.update("Ultrassonografia", contentValues, "id=?", params);
    }

    @NonNull
    public ContentValues getContentValuesUltrassonografia(Ultrassonografia ultrassonografia) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", ultrassonografia.getData());
        contentValues.put("igDum", ultrassonografia.getIgDum());
        contentValues.put("igUsg", ultrassonografia.getIgUsg());
        contentValues.put("pesoFetal", ultrassonografia.getPesoFetal());
        contentValues.put("placenta", ultrassonografia.getPlacenta());
        contentValues.put("liquidoAmniotico", ultrassonografia.getLiquidoAmniotico());
        return contentValues;
    }

    public Ultrassonografia pegaUltrassonografia() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Ultrassonografia";

        Ultrassonografia ultrassonografia = null;

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            ultrassonografia = new Ultrassonografia();
            ultrassonografia.setId(c.getLong(c.getColumnIndex("id")));
            ultrassonografia.setData(c.getString(c.getColumnIndex("data")));
            ultrassonografia.setIgDum(c.getString(c.getColumnIndex("igDum")));
            ultrassonografia.setIgUsg(c.getString(c.getColumnIndex("igUsg")));
            ultrassonografia.setPesoFetal(c.getInt(c.getColumnIndex("pesoFetal")));
            ultrassonografia.setPlacenta(c.getString(c.getColumnIndex("placenta")));
            ultrassonografia.setLiquidoAmniotico(c.getDouble(c.getColumnIndex("liquidoAmniotico")));
        }
        c.close();

        return ultrassonografia;
    }


    // ***** USO DE MEDICAMENTO ***** //
    public void salvaUsoDeMedicamento(ArrayList<String> medicamentos) {

        SQLiteDatabase db = getWritableDatabase();

        String sql = "DELETE FROM UsoDeMedicamento";

        db.execSQL(sql);

        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < medicamentos.size(); i++) {
            contentValues.put("id", i + 1);
            contentValues.put("medicamento", medicamentos.get(i));

            db.insert("UsoDeMedicamento", null, contentValues);
        }
    }

    public ArrayList<String> pegaUsoDeMedicamento() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT medicamento FROM UsoDeMedicamento";

        ArrayList<String> medicamentos = new ArrayList<>();

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            medicamentos.add(c.getString(c.getColumnIndex("medicamento")));
        }
        c.close();

        Collections.sort(medicamentos);

        return medicamentos;
    }


    // ***** CONSULTAS MENSAIS ***** //
    public void salvaConsultasMensais(ConsultasMensais consultasMensais) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesConsultasMensais(consultasMensais);

        db.insert("ConsultasMensais", null, contentValues);
    }

    public void alteraConsultasMensais(ConsultasMensais consultasMensais) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValuesConsultasMensais(consultasMensais);

        String[] params = {String.valueOf(consultasMensais.getId())};

        db.update("ConsultasMensais", contentValues, "id=?", params);
    }

    @NonNull
    public ContentValues getContentValuesConsultasMensais(ConsultasMensais consultasMensais) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("numeroConsulta", consultasMensais.getNumeroConsulta());
        contentValues.put("dataConsulta", consultasMensais.getDataConsulta());
        contentValues.put("queixa", consultasMensais.getQueixa());
        contentValues.put("ig", consultasMensais.getIg());
        contentValues.put("peso", consultasMensais.getPeso());
        contentValues.put("imc", consultasMensais.getImc());
        contentValues.put("edema", consultasMensais.getEdema());
        contentValues.put("paI", consultasMensais.getPaI());
        contentValues.put("paII", consultasMensais.getPaII());
        contentValues.put("alturaUterina", consultasMensais.getAlturaUterina());
        contentValues.put("posicaoFetal", consultasMensais.getPosicaoFetal());
        contentValues.put("bcf", consultasMensais.getBcf());
        contentValues.put("movFetal", consultasMensais.getMovFetal());
        contentValues.put("nomeDoProfissional", consultasMensais.getNomeDoProfissional());
        return contentValues;
    }

    public ConsultasMensais pegaConsultasMensais(int numeroDaConsulta) {

        SQLiteDatabase db = getReadableDatabase();

        String sql;
        if (numeroDaConsulta == -1) {
            sql = "SELECT * FROM ConsultasMensais WHERE numeroConsulta=" +
                    "(SELECT MAX(cm.numeroConsulta) FROM ConsultasMensais as cm)";
        } else {
            sql = "SELECT * FROM ConsultasMensais WHERE numeroConsulta=" + numeroDaConsulta;
        }

        ConsultasMensais consultasMensais = null;

        Cursor c;
        c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            consultasMensais = new ConsultasMensais();
            consultasMensais.setId(c.getLong(c.getColumnIndex("id")));
            consultasMensais.setNumeroConsulta(c.getInt(c.getColumnIndex("numeroConsulta")));
            consultasMensais.setDataConsulta(c.getString(c.getColumnIndex("dataConsulta")));
            consultasMensais.setQueixa(c.getString(c.getColumnIndex("queixa")));
            consultasMensais.setIg(c.getDouble(c.getColumnIndex("ig")));
            consultasMensais.setPeso(c.getDouble(c.getColumnIndex("peso")));
            consultasMensais.setImc(c.getDouble(c.getColumnIndex("imc")));
            consultasMensais.setEdema(c.getString(c.getColumnIndex("edema")));
            consultasMensais.setPaI(c.getDouble(c.getColumnIndex("paI")));
            consultasMensais.setPaII(c.getDouble(c.getColumnIndex("paII")));
            consultasMensais.setAlturaUterina(c.getInt(c.getColumnIndex("alturaUterina")));
            consultasMensais.setPosicaoFetal(c.getString(c.getColumnIndex("posicaoFetal")));
            consultasMensais.setBcf(c.getInt(c.getColumnIndex("bcf")));
            consultasMensais.setMovFetal(c.getString(c.getColumnIndex("movFetal")));
            consultasMensais.setNomeDoProfissional(c.getString(c.getColumnIndex("nomeDoProfissional")));
        }
        c.close();

        return consultasMensais;
    }

    public ArrayList<String> pegaQuantConsultasMensais() {

        ArrayList<String> consultas = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT numeroConsulta FROM ConsultasMensais ORDER BY numeroConsulta ASC";

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            consultas.add(c.getInt(c.getColumnIndex("numeroConsulta")) + "ª consulta");
        }

        c.close();

        return consultas;
    }

    public boolean deletaConsultasMensais(ConsultasMensais consultasMensais) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(consultasMensais.getId())};

        try {

            db.delete("ConsultasMensais", "id=?", params);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
