package com.scriptpoin.guiagestacional.dao;

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
        String criaTabela = "CREATE TABLE IF NOT EXISTS Duvidas(id INTEGER PRIMARY KEY, trimestre INT NOT NULL, pergunta TEXT NOT NULL, resposta TEXT NOT NULL);";
        db.execSQL(criaTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Duvidas;";
        db.execSQL(sql);
        onCreate(db);
    }

    public ArrayList<DuvidasTrimestre> pegaDuvidas(int trimestreX) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = null;
        if(trimestreX == 1) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=1";
        } else if(trimestreX == 2) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=2";
        } else if(trimestreX == 3) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=3";
        } else if(trimestreX == 41) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=41 ORDER BY pergunta ASC";
        } else if(trimestreX == 42) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=42";
        } else if(trimestreX == 43) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=43";
        } else if(trimestreX == 44) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=44";
        }
        
        Cursor c = db.rawQuery(sql, null);

        ArrayList<DuvidasTrimestre> duvidas = new ArrayList<>();
        while(c.moveToNext()) {
            DuvidasTrimestre duvidasTrimestre = new DuvidasTrimestre();
            duvidasTrimestre.setId(c.getInt(c.getColumnIndex("id")));
            duvidasTrimestre.setTrimestre(c.getInt(c.getColumnIndex("trimestre")));
            duvidasTrimestre.setPergunta(c.getString(c.getColumnIndex("pergunta")));
            duvidasTrimestre.setResposta(c.getString(c.getColumnIndex("resposta")));

            duvidas.add(duvidasTrimestre);
        }
        c.close();

        return duvidas;
    }

    public void insere() {
        SQLiteDatabase db = getWritableDatabase();
        String p1Ttrimestre1 = "INSERT INTO Duvidas VALUES(11, 1," +
                "'O que acontece com as mamas durante a gestação ?'," +
                "'As mamas aumentam de tamanho e peso rapidamente. Tornam-se mais sensíveis logo nas primeiras semanas de gravidez. " +
                "Surgem novos ductos lactíferos.\nAs auréolas (camada escura) das mamas escurecem. Com o aumento do envio de sangue " +
                "para os seios, as veias se tornam mais visíveis.'),";
        String p2Trimestre1 = "(12, 1," +
                "'A gestante faz  mais xixi do que as mulheres não grávidas, por quê ?'," +
                "'É verdade que durante a gestação aumente a vontade de fazer xixi, pois o útero cresce e comprime a bexiga, assim, a gestante vai mais ao banheiro.'),";
        String p3Trimestre1 = "(13, 1," +
                "'É normal a gestante ter desejo por alimentos e querer comer mais ?'," +
                "'a fatores hormonais (prolactina e progesterona são responsáveis pela alteração do apetite e do pH da boca), comer causa, sensações de prazer melhorando o humor. ocorrer alterações metabólicas, modificações no gosto e sensibilidade olfatória, que estão juntos da grávida.'),";
        String p4Trimestre1 = "(14, 1," +
                "'Durante a gestação a atividade sexual pode ser praticada ?'," +
                "'Durante a gestação, a atividade sexual pode ser praticada normalmente, o que dependerá somente do casal. Em qualquer momento da gravidez, na ocorrência de sangramentos, cólicas e contrações a relação sexual com penetração deve ser suspensa temporariamente até o profissional de saúde identificar a causa.'),";
        String p5Trimestre1 = "(15, 1," +
                "'Como está meu bebê com 4 semanas de gestação ?'," +
                "'Com 4 semanas ele é do tamanho de um grão de arroz, seu coração começa a bater e aparecem pequenos brotos que serão depois os braços e as pernas.'),";
        String p6Trimestre1 = "(16, 1," +
                "'Como está meu bebê com 8 semanas de gestação ?'," +
                "'Ao final de 8 semanas já estão se formando os dedos, as mãos, as orelhas e os órgãos internos. Ele é do tamanho de uma ervilha e pesa mais ou menos 7 gramas.'),";
        String p7Trimestre1 = "(17, 1," +
                "'Como está meu bebê com 12 semanas de gestação ?'," +
                "'O bebê já está praticamente todo formado. Dentro da bolsa de água, ele consegue piscar, mover os dedos, abrir a boca os rins estão quase totalmente formados, logo, irá realizar o primeiro xixi. Já é possível escutar os batimentos do coração do bebê durante a consulta de pré-natal.'),";



        String p1Trimestre2 = "(21, 2," +
                "'As mamas podem formigar e ficar doloridas ?'," +
                "'Pode sim, com o avançar da gravidez as mamas vão aumentando de tamanho devido os hormônios, ficando mais pesadas e doloridas. Orienta-se usar sutiã para sustentar melhor a mama na sua forma anatômica.'),";

        String p2Trimestre2 = "(22, 2," +
                "'Podem aparecer regiões com manchas ou pigmento na pele ?'," +
                "'Durante a gestação é comum a presença de manchas no rosto e aparecer uma linha escura na barriga, conhecida como línea nigra. Orienta-se utilizar protetor solar no rosto. Quanto a linha escura na barriga, ela desaparece após o aparto.'),";

        String p3Trimestre2 = "(23, 2," +
                "'Quando vou sentir o bebê mexer dentro da barriga ?'," +
                "'Os movimentos do bebê iniciam a partir da metade da gestação, ou seja, por volta de semanas de gravidez (ou quatro meses e meio).'),";

        String p4Trimestre2 = "(24, 2," +
                "'Quais as mudanças no corpo da mulher no segundo trimestre da gestação ?'," +
                "'A barriga cresce pouco a pouco, sendo bem visível a partir de 20 semanas (5 meses). Os seios vão aumentando e é comum aparecer colostro (leite) antes de parir. Pode aparecer manchas no rosto, ou pele ficarem um pouco escura. As alterações fisiológicas observadas na gestação decorrem principalmente de fatores hormonais.'),";

        String p5Trimestre2 = "(25, 2," +
                "'Como está meu bebê entre 16  a 20 semanas de gestação ?'," +
                "'A partir de 16 semanas de gestação, a mãe começa aperceber os movimentos do feto. Com 20 semanas, aparece no bebê a cabelos (lanugem) e a pele do bebê está coberta de vernix caseoso (gordura de proteção). Com 24 semanas de vida os órgãos estão todos desenvolvidos, mas o bebê ainda não está com o sistema respiratório funcionando normal para vir ao mundo.\n\nCom 20 semanas seu bebe mede cerca de 16,5 cm (comprimento do topo da cabeça a rabo) e pesa aproximadamente 300 g. O seu bebê já tem um sentido auditivo mais apurado e já consegue reconhecer a sua voz. Converse e cante  muito para ele!\n\nCom 26 semanas de gestação seu bebê cresce muito rapidamente e o desenvolvimento do seu cérebro é muito intenso nesta fase.'),";



        String p1Trimestre3 = "(31, 3," +
                "'como está meu bebê entre 37  a 40 semanas de gestação ?'," +
                "'Às 38 semanas de gravidez o seu bebê mede cerca de 49,8 cm da cabeça aos pés e pesa aproximadamente 3050 g. O bebê continua a ganhar peso e pode nascer a qualquer momento. O cérebro está completamente preparado para se adaptar ao mundo exterior. Os ossos do crânio ainda não estão completamente fechados para facilitar a passagem da cabeça pelo canal de parto. O intestino continua a produzir mecônio (primeira matéria fecal produzida pelo bebê). Todos os sistemas do bebê estão completamente desenvolvidos e prontos para a próxima etapa com exceção dos pulmões que continuam o seu amadurecimento. O tamanho da cabeça é proporcional ao corpo (a cabeça e o abdômen têm agora o mesmo diâmetro). A placenta começa a envelhecer e a tornar-se menos eficiente no fornecimento de nutrientes.'),";

        String p2Trimestre3 = "(32, 3," +
                "'Quais são os sinais clínicos do trabalho de parto ?'," +
                "'A barriga fica bem maior, a coluna inclina um pouco para sustentar a mulher em pé. Ocorre um aumento na vontade de fazer xixi, pois o útero cresceu para acolher o feto, e com isso o útero comprime a bexiga.'),";

        String p3Trimestre3 = "(33, 3," +
                "'Quais as mudanças no corpo da mulher no terceiro trimestre de gestação ?'," +
                "'Existe os sinais de alarme FALSOS do trabalho de parto e os sinais VERDADEIROS.\n\n" +
                "Os sinais de alarme falso: \n\n- Indicam que está próximo do parto, mas as vezes ainda demoras alguns dias para a mulher parir: exemplos: contrações irregulares (uma contração agora e outra daqui a cinco horas); dores nas costas; pode acontecer de perder o tampão mucoso (muco grosso que sai pela vagina); mas é interessante procurar a Unidade Básica ou a maternidade para avaliação.\n\n" +
                "Os sinais verdadeiros de trabalho de parto:\n\n" +
                "- Presença de contrações ritmadas 5 e 15 minutos, porém curtas, passam rápidas (duram de 30 a 60 segundos, e o colo do útero começa a dilatar. \n\n" +
                "- Com o passar das horas, as contrações as contrações vão acontecendo a cada 3 minutos e dura 1 minuto cada contração (chama de fase ativa do parto).\n\n" +
                "- Em seguida as contrações vão ocorrem a cada 2 minutos, duram 90 segundos, é quando o útero está dilatado entre 8 a 10 centímetros.\n\n" +
                "Para acompanhar as contrações é preciso um relógio e colocar a mão sobre a barriga, quando a barriga ficar dura representa uma contração e só acaba quando a barriga fica um pouco mole.\n\n" +
                "Caso complete 41 semanas e não apresente nenhum sinal, procure o profissional de saúde que esteja lhe acompanhando.'),";



        String p1DuvidasGestacaoPI = "(411, 41," +
                "'Náuseas/Vômitos'," +
                "'São freqüentes nos primeiros três meses de gravidez, mais intenso pela manhã, ao acordar ou após um período de jejum prolongado. Pioram com estímulos sensoriais, em especial do olfato, como o cheiro de cigarro ou do paladar, como pasta de dentes. As náuseas podem está presentes, são relacionadas a níveis crescentes de gonadotrofina coriônica humana HCG) e de estrógeno.'),";

        String p2DuvidasGestacaoPI = "(412, 41," +
                "'Queixas urinárias'," +
                "'São frequentes nos primeiros e nos últimos meses de gravidez. A provável compressão da bexiga pelo útero gravídico, diminuindo a capacidade guardar mais urina, ocasiona o aumento de idas ao banho.'),";

        String p3DuvidasGestacaoPI = "(413, 41," +
                "'Dor abdominal/Cólica'," +
                "'É comum no primeiro trimestre, pois acontece o desenvolvimento do feto, comunicar sempre a equipe de profissionais que acompanha o pré-natal.'),";

        String p4DuvidasGestacaoPI = "(414, 41," +
                "'Edema'," +
                "'Surge no 3º trimestre da gestação, limitando-se aos membros inferiores e, ocasionalmente às mãos. Piora quando ficar em pé por várias horas e quando anda; desaparece pela manhã e pode piorar ao longo do dia. Os edemas precisam serem acompanhados.'),";

        String p5DuvidasGestacaoPI = "(415, 41," +
                "'Azia'," +
                "'(Ocorre o relaxamento do músculo liso o esfíncter esofágico inferior pode contribuir. O aumento do volume do útero causa a compressão do estômago, ocasionando um aumento de refluxo), diretamente ligada com a alimentação e alteração fisiológica que o estômago sofre, devido a pressão que o útero exerce sobre ele. O refluxo do esôfago pode provocar azia, devido ao relaxamento do esfíncter no alto do estômago.'),";

        String p6DuvidasGestacaoPI = "(416, 41," +
                "'Fraqueza/Tontura'," +
                "'Estão associadas à hipoglicemia, alteração frequente quando do jejum prolongado da grávida.'),";

        String p7DuvidasGestacaoPI = "(417, 41," +
                "'Dificuldade para respirar'," +
                "'Acontece mais no terceiro trimestre, pois o útero comprime o diafragma o que contribui para a dificuldade de respirar.'),";

        String p8DuvidasGestacaoPI = "(418, 41," +
                "'Constipação intestinal/Flatulências'," +
                "'Comum na gestação, agravada pelo esforço na evacuação ou pela consistência das fezes. Comer alimentos com fibras ajuda a reduzir os desconfortos.'),";

        String p9DuvidasGestacaoPI = "(419, 41," +
                "'Dor lombar'," +
                "'A adaptação da postura materna para sustentar a barriga sobrecarrega as articulações da coluna vertebral, mas será aliviada após o parto.'),";

        String p10DuvidasGestacaoPI = "(4110, 41," +
                "'Câimbras'," +
                "'Acontece devido o peso da barriga e as mudanças na circulação. Fazer alongamento, ficar em pé e massagear alivia os desconfortos.'),";

        String p11DuvidasGestacaoPI = "(4111, 41," +
                "'Pigmentação/Cloasma gravídico/Linha nigra'," +
                "'A gestação provoca alterações na pigmentação da pele e essas são mais observadas em mulheres de raça negra.'),";

        String p1DuvidasGestacaoPII = "(421, 42," +
                "'O uso do ácido fólico e sulfato ferroso é importante ?'," +
                "'O ácido fólico é importante principalmente antes da gravidez e até o  terceiro mês de gravidez, pois irá ajudar na formação do tubo neural (estrutura que da origem ao cérebro e a medula espinhal) da criança. O Sulfato ferroso deve ser tomado durante toda a gestação e até 3 meses depois do parto, ajuda a prevenir anemias.'),";

        String p1DuvidasGestacaoPIII = "(431, 43," +
                "'Que tipo de atividade física posso fazer ?'," +
                "'Durante as consultas, o enfermeiro e médico irão orientar qual a melhor atividade para o período gestacional, mas geralmente a caminhada é livre para todas as gestantes que não apresente problemas de saúde no período gestacional.\n\n" +
                "Os exercícios físicos são benéficos à saúde porque melhoram a circulação e a digestão, o apetite, a função intestinal, além de proporcionar relaxamento e um sono repousante.'),";

        String p1DuvidasGestacaoPIV = "(441, 44," +
                "'Quantos quilos posso aumentar durante a gestação ?'," +
                "'O ganho de peso é calculado pelo peso e dividido pela altura2, ou seja, é feito o cálculo pelo índice de massa corporal (IMC), o enfermeiro faz o cálculo durante a consulta de pré-natal. Mas as gestantes que tem o peso adequado pode aumentar entre 11 quilos e meio à 16 quilos. Para as gestante que são obesas deverá ganhar em torno de 5 à 9 quilos. E as de baixo peso, deve ganhar 12,5 à 18 quilos.');";



        String sql =
                p1Ttrimestre1 + p2Trimestre1 + p3Trimestre1 + p4Trimestre1 + p5Trimestre1 + p6Trimestre1 + p7Trimestre1 +
                p1Trimestre2 + p2Trimestre2 + p3Trimestre2 + p4Trimestre2 + p5Trimestre2 +
                p1Trimestre3 + p2Trimestre3 + p3Trimestre3 +
                p1DuvidasGestacaoPI + p2DuvidasGestacaoPI + p3DuvidasGestacaoPI + p4DuvidasGestacaoPI + p5DuvidasGestacaoPI + p6DuvidasGestacaoPI + p7DuvidasGestacaoPI + p8DuvidasGestacaoPI + p9DuvidasGestacaoPI + p10DuvidasGestacaoPI + p11DuvidasGestacaoPI +
                p1DuvidasGestacaoPII +
                p1DuvidasGestacaoPIII +
                p1DuvidasGestacaoPIV;

        db.execSQL(sql);
    }
}
