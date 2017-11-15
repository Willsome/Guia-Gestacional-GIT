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
import com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados.Exame;
import com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados.ExamesSolicitadosResultados;
import com.scriptpoin.guiagestacional.caderneta.ultrassonografia.Ultrassonografia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * Created by Willi on 20-Aug-17.
 */

public class DaoCaderneta extends SQLiteOpenHelper {

    public DaoCaderneta(Context context) {
        super(context, "cadernetaDB", null, 1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=1;");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String criaTabelaConsultasMensais =
                "CREATE TABLE IF NOT EXISTS ConsultasMensais(" +
                        "numeroConsulta INTEGER PRIMARY KEY," +
                        "dataConsulta DATE NOT NULL," +
                        "queixa TEXT NOT NULL," +
                        "ig REAL NOT NULL," +
                        "peso REAL NOT NULL," +
                        "imc REAL NOT NULL," +
                        "edema TEXT NOT NULL," +
                        "paI REAL NOT NULL," +
                        "paII REAL NOT NULL," +
                        "alturaUterina TEXT NOT NULL," +
                        "posicaoFetal TEXT NOT NULL," +
                        "bcf INTEGER NOT NULL," +
                        "movFetal TEXT NOT NULL," +
                        "tipoProfissional TEXT NOT NULL," +
                        "nomeDoProfissional TEXT NOT NULL);";

        String criaTabelaExame =
                "CREATE TABLE IF NOT EXISTS Exame(" +
                        "id INTEGER PRIMARY KEY," +
                        "nome TEXT NOT NULL);";

        String criaTabelaExamesSolicitadosResultados =
                "CREATE TABLE IF NOT EXISTS ExamesSolicitadosResultados(" +
                        "id INTEGER PRIMARY KEY," +
                        "solicitacao INTEGER," +
                        "numeroConsultaSolicitacao INTEGER NOT NULL," +
                        "id_exame INTEGER," +
                        "numeroConsultaResultado INTEGER," +
                        "resultado TEXT," +
                        "FOREIGN KEY(numeroConsultaSolicitacao) REFERENCES ConsultasMensais(numeroConsulta)," +
                        "FOREIGN KEY(id_exame) REFERENCES Exame(id)" +
                        ");";

        String criaTabelaUltrassonografia =
                "CREATE TABLE IF NOT EXISTS Ultrassonografia(" +
                        "id INTEGER PRIMARY KEY," +
                        "solicitacao INTEGER," +
                        "numeroConsultaSolicitacao INTEGER NOT NULL," +
                        "numeroConsultaResultado INTEGER," +
                        "data DATE," +
                        "igDum DATE," +
                        "igUsg DATE," +
                        "pesoFetal REAL," +
                        "placenta TEXT," +
                        "liquidoAmniotico REAL," +
                        "FOREIGN KEY(numeroConsultaSolicitacao) REFERENCES ConsultasMensais(numeroConsulta)" +
                        ");";


        String criaTabelaDadosPessoais =
                "CREATE TABLE IF NOT EXISTS DadosPessoais(" +
                        "id INTEGER PRIMARY KEY," +
                        "nome TEXT NOT NULL," +
                        "dataNascimento DATE NOT NULL," +
                        "idade INT NOT NULL," +
                        "endereco TEXT NOT NULL," +
                        "nomeCompanheiro TEXT NOT NULL);";


        String criaTabelaDadosObstetricos =
                "CREATE TABLE IF NOT EXISTS DadosObstetricos(" +
                        "id INTEGER PRIMARY KEY," +
                        "dum DATE NOT NULL," +
                        "dpp DATE NOT NULL);";


        String criaTabelaUsoDeMedicamento =
                "CREATE TABLE IF NOT EXISTS UsoDeMedicamento(" +
                        "id INTEGER PRIMARY KEY," +
                        "medicamento TEXT NOT NULL);";


        db.execSQL("PRAGMA foreign_keys=1;");

        db.execSQL(criaTabelaConsultasMensais);
        db.execSQL(criaTabelaExame);
        db.execSQL(criaTabelaExamesSolicitadosResultados);
        db.execSQL(criaTabelaUltrassonografia);
        db.execSQL(criaTabelaDadosPessoais);
        db.execSQL(criaTabelaDadosObstetricos);
        db.execSQL(criaTabelaUsoDeMedicamento);

        String insereExames = "INSERT INTO Exame VALUES" +
                "(1, 'ABO-RH')," +
                "(2, 'Glicemia de jejum')," +
                "(3, 'Hematócrito')," +
                "(4, 'Hemoglobina')," +
                "(5, 'Hepatite B (HBsAg)')," +
                "(6, 'HIV (teste rápido)')," +
                "(7, 'Tolerância à glicose')," +
                "(8, 'Toxoplasmose IgG')," +
                "(9, 'Toxoplasmose IgM')," +
                "(10, 'Urina tipo 1')," +
                "(11, 'Urocultura')," +
                "(12, 'VDRL / teste rápido para sífilis');";

        db.execSQL(insereExames);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("PRAGMA foreign_keys=1;");

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
    public ContentValues getContentValuesDadosPessoais(DadosPessoais dadosPessoais) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", dadosPessoais.getNome());
        contentValues.put("dataNascimento", dadosPessoais.getDataNascimento().getTimeInMillis());
        contentValues.put("idade", dadosPessoais.getIdade());
        contentValues.put("endereco", dadosPessoais.getEndereco());
        contentValues.put("nomeCompanheiro", dadosPessoais.getNomeCompanheiro());
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

            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTimeInMillis(c.getLong(c.getColumnIndex("dataNascimento")));
            dadosPessoais.setDataNascimento(calendarDataNascimento);

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
        contentValues.put("dum", dadosObstetricos.getDum().getTimeInMillis());
        contentValues.put("dpp", dadosObstetricos.getDpp().getTimeInMillis());
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

            Calendar dataDum = Calendar.getInstance();
            dataDum.setTimeInMillis(c.getLong(c.getColumnIndex("dum")));
            dadosObstetricos.setDum(dataDum);

            Calendar dataDpp = Calendar.getInstance();
            dataDpp.setTimeInMillis(c.getLong(c.getColumnIndex("dpp")));
            dadosObstetricos.setDpp(dataDpp);
        }
        c.close();

        return dadosObstetricos;
    }


    // ***** EXAMES SOLICITADOS/RESULTADOS ***** //

    public void salvaExamesSolicitadosResultados(ArrayList<ExamesSolicitadosResultados> examesSolicitadosResultados) {

        SQLiteDatabase db = getWritableDatabase();

        for (ExamesSolicitadosResultados es : examesSolicitadosResultados) {
            ContentValues contentValues = getContentValuesExamesSolicitadosResultados(es);
            db.insert("ExamesSolicitadosResultados", null, contentValues);
        }
    }

    public void alteraExamesSolicitadosResultados(ArrayList<ExamesSolicitadosResultados> examesSolicitadosResultados) {

        SQLiteDatabase db = getWritableDatabase();

        // DELETA DADOS ATUAIS
        deletaExamesSolicitados(examesSolicitadosResultados);

        // RE-SALVA DADOS NOVOS
        for (ExamesSolicitadosResultados es : examesSolicitadosResultados) {
            ContentValues contentValues = getContentValuesExamesSolicitadosResultados(es);
            db.insert("ExamesSolicitadosResultados", null, contentValues);
        }
    }

    public ArrayList<Exame> pegaExames() {

        ArrayList<Exame> exames = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Exame";

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            Exame exame = new Exame();
            exame.setId(c.getLong(c.getColumnIndex("id")));
            exame.setNomeDoExame(c.getString(c.getColumnIndex("nome")));
            exames.add(exame);
        }

        return exames;
    }

    @NonNull
    public ContentValues getContentValuesExamesSolicitadosResultados(ExamesSolicitadosResultados es) {
        ContentValues contentValues = new ContentValues();
        if (es.getSolicitacao() == 1) {
            contentValues.put("solicitacao", es.getSolicitacao());
            contentValues.put("numeroConsultaSolicitacao", es.getNumeroConsultaSolicitacao());
            contentValues.put("id_exame", es.getExame().getId());
            contentValues.put("numeroConsultaResultado", 0);
            contentValues.put("resultado", "");

        } else {
            contentValues.put("solicitacao", es.getSolicitacao());
            contentValues.put("numeroConsultaSolicitacao", es.getNumeroConsultaSolicitacao());
            contentValues.put("id_exame", es.getExame().getId());
            contentValues.put("numeroConsultaResultado", es.getNumeroConsultaResultado());
            contentValues.put("resultado", es.getResultado());
        }

        return contentValues;
    }

    public ArrayList<ExamesSolicitadosResultados> pegaExamesSolicitadosResultados(int numeroDaConsulta, Context context) {

        ArrayList<ExamesSolicitadosResultados> ListaDeExamesSolicitadosResultados = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String sql;
        if (numeroDaConsulta == -1) {
            sql = "SELECT * FROM ExamesSolicitadosResultados WHERE numeroConsultaSolicitacao=" +
                    "(SELECT MAX(es.numeroConsultaSolicitacao) FROM ExamesSolicitadosResultados as es)";
        } else {
            sql = "SELECT * FROM ExamesSolicitadosResultados WHERE numeroConsultaSolicitacao=" + numeroDaConsulta;
        }

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            ExamesSolicitadosResultados es = new ExamesSolicitadosResultados();
            es.setId(c.getLong(c.getColumnIndex("id")));

            es.setSolicitacao(c.getLong(c.getColumnIndex("solicitacao")));
            es.setNumeroConsultaSolicitacao(c.getLong(c.getColumnIndex("numeroConsultaSolicitacao")));
            es.setNumeroConsultaResultado(c.getLong(c.getColumnIndex("numeroConsultaResultado")));

            DaoCaderneta dao = new DaoCaderneta(context);
            Exame exame = dao.pegaExamePeloId(c.getLong(c.getColumnIndex("id_exame")));
            es.setExame(exame);
            dao.close();

            es.setResultado(c.getString(c.getColumnIndex("resultado")));

            ListaDeExamesSolicitadosResultados.add(es);
        }
        c.close();

        return ListaDeExamesSolicitadosResultados;
    }

    public Exame pegaExamePeloId(Long id) {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Exame WHERE id=" + id;

        Exame exame = null;

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            exame = new Exame();
            exame.setId(c.getLong(c.getColumnIndex("id")));
            exame.setNomeDoExame(c.getString(c.getColumnIndex("nome")));
        }

        return exame;
    }

    public ArrayList<String> pegaQuantExamesSolicitadosResultados() {

        ArrayList<String> examesSolicitadosResultados = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT numeroConsultaSolicitacao FROM ExamesSolicitadosResultados ORDER BY numeroConsultaSolicitacao ASC";

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            examesSolicitadosResultados.add(c.getLong(c.getColumnIndex("numeroConsultaSolicitacao")) + "ª consulta");
        }

        c.close();

        return examesSolicitadosResultados;
    }

    public boolean existeExamesSolicitadosResultados(Long numeroConsulta) {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM ExamesSolicitadosResultados WHERE numeroConsultaSolicitacao=" + numeroConsulta;

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() <= 0) {
            c.close();
            return false;
        }
        c.close();
        return true;

    }

    public boolean deletaExamesSolicitados(ArrayList<ExamesSolicitadosResultados> examesSolicitadosResultados) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(examesSolicitadosResultados.get(0).getNumeroConsultaSolicitacao())};

        try {

            db.delete("ExamesSolicitadosResultados", "numeroConsultaSolicitacao=?", params);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
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

        if (ultrassonografia.getSolicitacao() == 1) {
            contentValues.put("solicitacao", ultrassonografia.getSolicitacao());
            contentValues.put("numeroConsultaSolicitacao", ultrassonografia.getNumeroConsultaSolicitacao());
            contentValues.put("numeroConsultaResultado", "");
            contentValues.put("data", "");
            contentValues.put("igDum", "");
            contentValues.put("igUsg", "");
            contentValues.put("pesoFetal", "");
            contentValues.put("placenta", "");
            contentValues.put("liquidoAmniotico", "");

        } else {
            contentValues.put("solicitacao", ultrassonografia.getSolicitacao());
            contentValues.put("numeroConsultaSolicitacao", ultrassonografia.getNumeroConsultaSolicitacao());
            contentValues.put("numeroConsultaResultado", ultrassonografia.getNumeroConsultaResultado());
            contentValues.put("data", ultrassonografia.getData().getTimeInMillis());
            contentValues.put("igDum", ultrassonografia.getIgDum().getTimeInMillis());
            contentValues.put("igUsg", ultrassonografia.getIgUsg().getTimeInMillis());
            contentValues.put("pesoFetal", ultrassonografia.getPesoFetal());
            contentValues.put("placenta", ultrassonografia.getPlacenta());
            contentValues.put("liquidoAmniotico", ultrassonografia.getLiquidoAmniotico());
        }

        return contentValues;
    }

    public Ultrassonografia pegaUltrassonografia(int numeroDaConsulta) {

        SQLiteDatabase db = getReadableDatabase();

        String sql;
        if (numeroDaConsulta == -1) {
            sql = "SELECT * FROM Ultrassonografia WHERE numeroConsultaSolicitacao=" +
                    "(SELECT MAX(u.numeroConsultaSolicitacao) FROM Ultrassonografia as u)";
        } else {
            sql = "SELECT * FROM Ultrassonografia WHERE numeroConsultaSolicitacao=" + numeroDaConsulta;
        }

        Ultrassonografia ultrassonografia = null;

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            ultrassonografia = new Ultrassonografia();
            ultrassonografia.setId(c.getLong(c.getColumnIndex("id")));

            ultrassonografia.setSolicitacao(c.getLong(c.getColumnIndex("solicitacao")));
            ultrassonografia.setNumeroConsultaSolicitacao(c.getLong(c.getColumnIndex("numeroConsultaSolicitacao")));
            ultrassonografia.setNumeroConsultaResultado(c.getLong(c.getColumnIndex("numeroConsultaResultado")));

            Calendar data = Calendar.getInstance();
            data.setTimeInMillis(c.getLong(c.getColumnIndex("data")));
            ultrassonografia.setData(data);

            Calendar dataIgDum = Calendar.getInstance();
            dataIgDum.setTimeInMillis(c.getLong(c.getColumnIndex("igDum")));
            ultrassonografia.setIgDum(dataIgDum);

            Calendar dataIgUsg = Calendar.getInstance();
            dataIgUsg.setTimeInMillis(c.getLong(c.getColumnIndex("igUsg")));
            ultrassonografia.setIgUsg(dataIgUsg);

            ultrassonografia.setPesoFetal(c.getInt(c.getColumnIndex("pesoFetal")));
            ultrassonografia.setPlacenta(c.getString(c.getColumnIndex("placenta")));
            ultrassonografia.setLiquidoAmniotico(c.getDouble(c.getColumnIndex("liquidoAmniotico")));
        }
        c.close();

        return ultrassonografia;
    }

    public ArrayList<String> pegaQuantUltrassonografias() {

        ArrayList<String> ultrassonografias = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT numeroConsultaSolicitacao FROM Ultrassonografia ORDER BY numeroConsultaSolicitacao ASC";

        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            ultrassonografias.add(c.getLong(c.getColumnIndex("numeroConsultaSolicitacao")) + "ª consulta");
        }

        c.close();

        return ultrassonografias;
    }

    public boolean existeUltra(Long numeroConsultaSolicitacao) {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Ultrassonografia WHERE numeroConsultaSolicitacao=" + numeroConsultaSolicitacao;

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() <= 0) {
            c.close();
            return false;
        }
        c.close();
        return true;

    }

    public boolean deletaUltrassonografia(Ultrassonografia ultrassonografia) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(ultrassonografia.getId())};

        try {

            db.delete("Ultrassonografia", "id=?", params);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
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

        String[] params = {String.valueOf(consultasMensais.getNumeroConsulta())};

        db.update("ConsultasMensais", contentValues, "numeroConsulta=?", params);
    }

    @NonNull
    public ContentValues getContentValuesConsultasMensais(ConsultasMensais consultasMensais) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("numeroConsulta", consultasMensais.getNumeroConsulta());
        contentValues.put("dataConsulta", consultasMensais.getDataConsulta().getTimeInMillis());
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
        contentValues.put("tipoProfissional", consultasMensais.getTipoProfissional());
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
            consultasMensais.setNumeroConsulta(c.getLong(c.getColumnIndex("numeroConsulta")));

            Calendar dataConsulta = Calendar.getInstance();
            dataConsulta.setTimeInMillis(c.getLong(c.getColumnIndex("dataConsulta")));
            consultasMensais.setDataConsulta(dataConsulta);

            consultasMensais.setQueixa(c.getString(c.getColumnIndex("queixa")));
            consultasMensais.setIg(c.getDouble(c.getColumnIndex("ig")));
            consultasMensais.setPeso(c.getDouble(c.getColumnIndex("peso")));
            consultasMensais.setImc(c.getDouble(c.getColumnIndex("imc")));
            consultasMensais.setEdema(c.getString(c.getColumnIndex("edema")));
            consultasMensais.setPaI(c.getDouble(c.getColumnIndex("paI")));
            consultasMensais.setPaII(c.getDouble(c.getColumnIndex("paII")));
            consultasMensais.setAlturaUterina(c.getString(c.getColumnIndex("alturaUterina")));
            consultasMensais.setPosicaoFetal(c.getString(c.getColumnIndex("posicaoFetal")));
            consultasMensais.setBcf(c.getInt(c.getColumnIndex("bcf")));
            consultasMensais.setMovFetal(c.getString(c.getColumnIndex("movFetal")));
            consultasMensais.setTipoProfissional(c.getString(c.getColumnIndex("tipoProfissional")));
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
            consultas.add(c.getLong(c.getColumnIndex("numeroConsulta")) + "ª consulta");
        }

        c.close();

        return consultas;
    }

    public boolean existeConsulta(Long numeroConsulta) {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM ConsultasMensais WHERE numeroConsulta=" + numeroConsulta;

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() <= 0) {
            c.close();
            return false;
        }
        c.close();
        return true;

    }

    public boolean deletaConsultasMensais(ConsultasMensais consultasMensais) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(consultasMensais.getNumeroConsulta())};

        try {

            db.delete("ConsultasMensais", "numeroConsulta=?", params);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
