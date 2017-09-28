package com.scriptpoin.guiagestacional.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                        "nome TEXT NOT NULL," +
                        "dataNascimento TEXT NOT NULL," +
                        "idade INT NOT NULL," +
                        "endereco TEXT NOT NULL," +
                        "nomeCompanheiro TEXT NOT NULL);";
        db.execSQL(criaTabelaDadosPessoais);

        String criaTabelaDadosObstetricos =
                "CREATE TABLE IF NOT EXISTS DadosObstetricos(" +
                        "dum TEXT NOT NULL," +
                        "dpp TEXT NOT NULL);";
        db.execSQL(criaTabelaDadosObstetricos);

        String criaTabelaExamesSolicitadosResultados =
                "CREATE TABLE IF NOT EXISTS ExamesSolicitadosResultados(" +
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
        db.execSQL(criaTabelaExamesSolicitadosResultados);

        String criaTabelaUltrassonografia =
                "CREATE TABLE IF NOT EXISTS Ultrassonografia(" +
                        "data TEXT NOT NULL," +
                        "igDum TEXT NOT NULL," +
                        "igUsg TEXT NOT NULL," +
                        "pesoFetal REAL NOT NULL," +
                        "placenta TEXT NOT NULL," +
                        "liquidoAmniotico REAL NOT NULL);";
        db.execSQL(criaTabelaUltrassonografia);

        String criaTabelaUsoDeMedicamento =
                "CREATE TABLE IF NOT EXISTS UsoDeMedicamento(" +
                        "id INT NOT NULL," +
                        "medicamento TEXT NOT NULL);";
        db.execSQL(criaTabelaUsoDeMedicamento);

        String criaTabelaConsultasMensais =
                "CREATE TABLE IF NOT EXISTS ConsultasMensais(" +
                        "numeroConsulta INT NOT NULL," +
                        "dataConsulta TEXT NOT NULL," +
                        "queixa TEXT NOT NULL," +
                        "ig REAL NOT NULL," +
                        "peso REAL NOT NULL," +
                        "imc REAL NOT NULL," +
                        "edema TEXT NOT NULL," +
                        "paI REAL NOT NULL," +
                        "paII REAL NOT NULL," +
                        "alturaUterina INT NOT NULL," +
                        "posicaoFetal TEXT NOT NULL," +
                        "bcf INT NOT NULL," +
                        "movFetal TEXT NOT NULL," +
                        "nomeDoProfissional TEXT NOT NULL);";
        db.execSQL(criaTabelaConsultasMensais);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    // ***** DADOS PESSOAIS ***** //
    public void salvaDadosPessoais(DadosPessoais dados) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", dados.getNome());
        contentValues.put("dataNascimento", dados.getDataNascimento());
        contentValues.put("idade", dados.getIdade());
        contentValues.put("endereco", dados.getEndereco());
        contentValues.put("nomeCompanheiro", dados.getNomeCompanheiro());

        db.insert("DadosPessoais", null, contentValues);
    }

    public DadosPessoais pegaDadosPessoais() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM DadosPessoais";

        DadosPessoais dadosPessoais = new DadosPessoais();

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
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
    public void salvaDadosObstetricos(DadosObstetricos dados) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("dum", dados.getDum());
        contentValues.put("dpp", dados.getDpp());

        db.insert("DadosObstetricos", null, contentValues);
    }

    public DadosObstetricos pegaDadosObstetricos() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM DadosObstetricos";

        DadosObstetricos dadosObstetricos = new DadosObstetricos();

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            dadosObstetricos.setDum(c.getString(c.getColumnIndex("dum")));
            dadosObstetricos.setDpp(c.getString(c.getColumnIndex("dpp")));
        }
        c.close();

        return dadosObstetricos;
    }


    // ***** EXAMES SOLICITADOS/RESULTADOS ***** //
    public void salvaExamesSolicitadosResultados(ExamesSolicitadosResultados es) {

        SQLiteDatabase db = getWritableDatabase();

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

        db.insert("ExamesSolicitadosResultados", null, contentValues);
    }

    public ExamesSolicitadosResultados pegaExamesSolicitadosResultados() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM ExamesSolicitadosResultados";

        ExamesSolicitadosResultados es = new ExamesSolicitadosResultados();

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            es.setAboRh(c.getInt(c.getColumnIndex("abo")));
            es.setGlicemiaJejum(c.getInt(c.getColumnIndex("glicemiaJejum")));
            es.setToleranciaGlicose(c.getInt(c.getColumnIndex("toleranciaGlicose")));
            es.setSifilis(c.getInt(c.getColumnIndex("sifilis")));
            es.setVdrl(c.getInt(c.getColumnIndex("vdrl")));
            es.setHiv(c.getInt(c.getColumnIndex("hiv")));
            es.setHepatiteBC(c.getInt(c.getColumnIndex("hepatiteBC")));
            es.setHbsag(c.getInt(c.getColumnIndex("hbsag")));
            es.setToxoplasmose(c.getInt(c.getColumnIndex("toxoplasmose")));
            es.setHemoglobina(c.getInt(c.getColumnIndex("hemoglobina")));
            es.setUrinaEas(c.getInt(c.getColumnIndex("urinaEas")));
            es.setUrinaCultura(c.getInt(c.getColumnIndex("urinaCultura")));
            es.setCoombs(c.getInt(c.getColumnIndex("coombs")));
        }
        c.close();

        return es;
    }


    // ***** ULTRASSONOGRAFIA ***** //
    public void salvaUltrassonografia(Ultrassonografia u) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("data", u.getData());
        contentValues.put("igDum", u.getIgDum());
        contentValues.put("igUsg", u.getIgUsg());
        contentValues.put("pesoFetal", u.getPesoFetal());
        contentValues.put("placenta", u.getPlacenta());
        contentValues.put("liquidoAmniotico", u.getLiquidoAmniotico());

        db.insert("Ultrassonografia", null, contentValues);
    }

    public Ultrassonografia pegaUltrassonografia() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Ultrassonografia";

        Ultrassonografia u = new Ultrassonografia();

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            u.setData(c.getString(c.getColumnIndex("data")));
            u.setIgDum(c.getString(c.getColumnIndex("igDum")));
            u.setIgUsg(c.getString(c.getColumnIndex("igUsg")));
            u.setPesoFetal(c.getInt(c.getColumnIndex("pesoFetal")));
            u.setPlacenta(c.getString(c.getColumnIndex("placenta")));
            u.setLiquidoAmniotico(c.getFloat(c.getColumnIndex("liquidoAmniotico")));
        }
        c.close();

        return u;
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

        db.close();
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

        db.insert("ConsultasMensais", null, contentValues);
    }

    public ConsultasMensais pegaConsultasMensais(int numeroDaConsulta) {

        SQLiteDatabase db = getReadableDatabase();

        String sql;
        if(numeroDaConsulta == -1) {
            sql = "SELECT * FROM ConsultasMensais WHERE numeroConsulta=" +
                    "(SELECT MAX(cm.numeroConsulta) FROM ConsultasMensais as cm)";
        } else {
            sql = "SELECT * FROM ConsultasMensais WHERE numeroConsulta=" + numeroDaConsulta;
        }

        ConsultasMensais consultasMensais = new ConsultasMensais();

        Cursor c;
        c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
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
        while(c.moveToNext()) {
            consultas.add(c.getInt(c.getColumnIndex("numeroConsulta")) + "ª consulta");
        }

        c.close();

        return consultas;
    }

    public boolean deletaConsultasMensais(int numeroDaConsulta) {

        SQLiteDatabase db = getWritableDatabase();

        String sql = "DELETE FROM ConsultasMensais WHERE numeroConsulta=" + numeroDaConsulta;

        try {
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
