package com.scriptpoin.guiagestacional.dao_duvidas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scriptpoin.guiagestacional.gestacao.trimestre.DuvidasTrimestre;

import java.util.ArrayList;

/**
 * Created by Willi on 30-Jul-17.
 */

public class DaoDuvidas extends SQLiteOpenHelper {

    public DaoDuvidas(Context context) {
        super(context, "duvidasDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criaTabela = "CREATE TABLE IF NOT EXISTS Duvidas(id INTEGER PRIMARY KEY, pergunta TEXT NOT NULL, resposta TEXT NOT NULL);INSERT INTO Duvidas VALUES(1, 'teste', 'testao');";
        db.execSQL(criaTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IS EXISTS Duvidas;";
        db.execSQL(sql);
        onCreate(db);
    }

    public ArrayList<DuvidasTrimestre> pegaDuvidas() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Duvidas;";

        Cursor c = db.rawQuery(sql, null);

        ArrayList<DuvidasTrimestre> duvidas = new ArrayList<>();
        while(c.moveToNext()) {
            DuvidasTrimestre duvidasTrimestre = new DuvidasTrimestre();
            duvidasTrimestre.setId(c.getInt(c.getColumnIndex("id")));
            duvidasTrimestre.setPergunta(c.getString(c.getColumnIndex("pergunta")));
            duvidasTrimestre.setResposta(c.getString(c.getColumnIndex("resposta")));

            duvidas.add(duvidasTrimestre);
        }
        c.close();

        return duvidas;
    }

    public void insere() {
        SQLiteDatabase db = getWritableDatabase();
        String p1Ttrimestre1 = "INSERT INTO Duvidas VALUES(11," +
                "'O que acontece com as mamas durante a gestação ?'," +
                "'As mamas aumentam de tamanho e peso rapidamente. Tornam-se mais sensíveis logo nas primeiras semanas de gravidez. " +
                "Surgem novos ductos lactíferos.\nAs auréolas (camada escura) das mamas escurecem. Com o aumento do envio de sangue " +
                "para os seios, as veias se tornam mais visíveis.'),";
        String p2Trimestre1 = "(12," +
                "'A gestante faz  mais xixi do que as mulheres não grávidas, por quê ?'," +
                "'É verdade que durante a gestação aumente a vontade de fazer xixi, pois o útero cresce e comprime a bexiga, assim, a gestante vai mais ao banheiro.'),";
        String p3Trimestre1 = "(13," +
                "'É normal a gestante ter desejo por alimentos e querer comer mais ?'," +
                "'a fatores hormonais (prolactina e progesterona são responsáveis pela alteração do apetite e do pH da boca), comer causa, sensações de prazer melhorando o humor. ocorrer alterações metabólicas, modificações no gosto e sensibilidade olfatória, que estão juntos da grávida.'),";
        String p4Trimestre1 = "(14," +
                "'Durante a gestação a atividade sexual pode ser praticada ?'," +
                "'Durante a gestação, a atividade sexual pode ser praticada normalmente, o que dependerá somente do casal. Em qualquer momento da gravidez, na ocorrência de sangramentos, cólicas e contrações a relação sexual com penetração deve ser suspensa temporariamente até o profissional de saúde identificar a causa.'),";
        String p5Trimestre1 = "(15," +
                "'Como está meu bebê com 4 semanas de gestação ?'," +
                "'Com 4 semanas ele é do tamanho de um grão de arroz, seu coração começa a bater e aparecem pequenos brotos que serão depois os braços e as pernas.'),";
        String p6Trimestre1 = "(16," +
                "'Como está meu bebê com 8 semanas de gestação ?'," +
                "'Ao final de 8 semanas já estão se formando os dedos, as mãos, as orelhas e os órgãos internos. Ele é do tamanho de uma ervilha e pesa mais ou menos 7 gramas.'),";
        String p7Trimestre1 = "(17," +
                "'Como está meu bebê com 12 semanas de gestação ?'," +
                "'O bebê já está praticamente todo formado. Dentro da bolsa de água, ele consegue piscar, mover os dedos, abrir a boca os rins estão quase totalmente formados, logo, irá realizar o primeiro xixi. Já é possível escutar os batimentos do coração do bebê durante a consulta de pré-natal.');";

        String sql = p1Ttrimestre1 + p2Trimestre1 + p3Trimestre1 + p4Trimestre1 + p5Trimestre1 + p6Trimestre1 + p7Trimestre1;
        db.execSQL(sql);
    }
}
