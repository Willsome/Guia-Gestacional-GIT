package com.scriptpoin.gestacaosaudavel.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scriptpoin.gestacaosaudavel.gestacao.modelo_duvida_resposta.DuvidasTrimestre;

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
        String criaTabela = "CREATE TABLE IF NOT EXISTS Duvidas(" +
                "id INTEGER PRIMARY KEY," +
                "trimestre INT NOT NULL," +
                "pergunta TEXT NOT NULL," +
                "resposta TEXT NOT NULL);";

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
        if (trimestreX == 1) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=1";
        } else if (trimestreX == 2) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=2";
        } else if (trimestreX == 3) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=3";
        } else if (trimestreX == 4) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=4 ORDER BY pergunta ASC";
        } else if (trimestreX == 5) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=5";
        } else if (trimestreX == 6) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=6 ORDER BY pergunta ASC";
        } else if (trimestreX == 7) {
            sql = "SELECT * FROM Duvidas WHERE trimestre=7 ORDER BY pergunta ASC";
        }

        Cursor c = db.rawQuery(sql, null);

        ArrayList<DuvidasTrimestre> duvidas = new ArrayList<>();
        while (c.moveToNext()) {
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
        String p1Ttrimestre1 = "INSERT INTO Duvidas VALUES" +
                "(11, 1," +
                "'O que acontece com as mamas durante a gestação ?'," +
                "'1. As mamas aumentam de tamanho e peso rapidamente. Tornam-se mais sensíveis logo nas primeiras semanas de gravidez. Surgem novos ductos lactíferos; \n\n" +
                "2. As auréolas (camada escura) das mamas escurecem. Com o aumento do envio de sangue para os seios, as veias se tornam mais visíveis.'),";
        String p2Trimestre1 = "(12, 1," +
                "'A gestante faz  mais xixi do que as mulheres não grávidas ? Por quê ?'," +
                "'Porque o útero cresce e comprime a bexiga, assim, a gestante vai mais ao banheiro durante a gestação.'),";
        String p3Trimestre1 = "(13, 1," +
                "'É normal a gestante ter desejo por alimentos e querer comer mais ?'," +
                "'É sim, pois os fatores hormonais (prolactina e progesterona) são responsáveis pela alteração do apetite e do pH da boca, logo, comer causa sensações de prazer melhorando o humor.'),";
        String p4Trimestre1 = "(14, 1," +
                "'Durante a gestação a atividade sexual pode ser praticada ?'," +
                "'Durante a gestação a atividade sexual pode ser praticada normalmente, o que dependerá somente do casal. \n\n" +
                "Na presença de sangramentos, cólicas e contrações, a relação sexual com penetração deve ser suspensa temporariamente até o profissional de saúde identificar a causa.'),";
        String p5Trimestre1 = "(15, 1," +
                "'Como está meu bebê com 1 mês (4 semanas) de gestação ?'," +
                "'Com 1 mês, ele é do tamanho de um grão de arroz, seu coração começa a bater e aparecem pequenos brotos que serão depois os braços e as pernas.'),";
        String p6Trimestre1 = "(16, 1," +
                "'Como está meu bebê com 2 meses (8 semanas) de gestação ?'," +
                "'Com 2 meses, já estão se formando os dedos, as mãos, as orelhas e os órgãos internos. Ele é do tamanho de uma ervilha e pesa mais ou menos 7 gramas.'),";
        String p7Trimestre1 = "(17, 1," +
                "'Como está meu bebê com 3 meses (12 semanas) de gestação ?'," +
                "'Com 3 meses, seu bebê já está praticamente todo formado. Dentro da bolsa de água, ele consegue piscar, mover os dedos e abrir a boca. \n\n" +
                "Os rins estão quase totalmente formados, logo, irá realizar o primeiro xixi. Já é possível escutar os batimentos do coração do bebê durante a consulta de pré-natal.'),";


        String p1Trimestre2 = "(21, 2," +
                "'As mamas podem formigar e ficar doloridas ?'," +
                "'Podem sim, com o avançar da gravidez as mamas vão aumentando de tamanho devido os hormônios, ficando mais pesadas e doloridas.'),";

        String p2Trimestre2 = "(22, 2," +
                "'O que fazer para diminuir o desconforto das mamas ?'," +
                "'Orienta-se usar sutiã para sustentar melhor a mama na sua forma anatômica.'),";

        String p3Trimestre2 = "(23, 2," +
                "'Podem aparecer manchas ou pigmentos em algumas regiões da pele ?'," +
                "'Durante a gestação é comum a presença de manchas no rosto e aparecer uma linha escura na barriga, conhecida como línea nigra.'),";

        String p4Trimestre2 = "(24, 2," +
                "'O que fazer para prevenir o aparecimento das manchas ?'," +
                "'Orienta-se utilizar protetor solar no rosto. Quanto a linha escura na barriga, ela desaparece após o aparto.'),";

        String p5Trimestre2 = "(25, 2," +
                "'Quando vou sentir o bebê mexer dentro da barriga ?'," +
                "'Os movimentos do bebê iniciam a partir da metade da gestação, por volta de 4 meses e meio.'),";

        String p6Trimestre2 = "(26, 2," +
                "'Quais as mudanças físicas no corpo da mulher no segundo trimestre da gestação ?'," +
                "'A barriga cresce pouco a pouco, sendo bem visível a partir de 20 semanas (5 meses). \n\n" +
                "Os seios vão aumentando e é comum aparecer colostro (leite) antes de parir.\n\n" +
                "As alterações fisiológicas observadas na gestação decorrem principalmente de fatores hormonais.'),";

        String p7Trimestre2 = "(27, 2," +
                "'Como está meu bebê com 16 semanas de gestação ?'," +
                "'A partir de 16 semanas de gestação, a mãe começa a perceber os movimentos do feto.'),";

        String p8Trimestre2 = "(28, 2," +
                "'Como está meu bebê com 20 semanas de gestação ?'," +
                "'Com 20 semanas, seu bebê apresenta cabelos (lanugem) e a pele dele está coberta de gordura de proteção. \n\n" +
                "Seu bebe mede cerca de 16,5 cm e pesa aproximadamente 300 g. \n\n" +
                "Ele já tem um sentido auditivo mais apurado e já consegue reconhecer a sua voz. Converse e cante muito para ele !'),";

        String p9Trimestre2 = "(29, 2," +
                "'Como está meu bebê com 24 semanas de gestação ?'," +
                "'Com 24 semanas de vida, os órgãos do seu bebê  estão todos desenvolvidos, mas o bebê ainda não está com o sistema respiratório funcionando normal para vir ao mundo.'),";

        String p10Trimestre2 = "(210, 2," +
                "'Como está meu bebê com 26 semanas de gestação ?'," +
                "'Com 26 semanas de gestação, seu bebê cresce muito rapidamente e o desenvolvimento do seu cérebro é muito intenso nesta fase.'),";


        String p1Trimestre3 = "(31, 3," +
                "'Quais as mudanças no corpo da mulher no terceiro trimestre de gestação ?'," +
                "'A barriga fica bem maior, a coluna inclina um pouco para sustentar a mulher em pé. \n\n" +
                "Ocorre um aumento na vontade de fazer xixi, pois o útero cresceu para acolher o feto, e com isso o útero comprime a bexiga.'),";

        String p2Trimestre3 = "(32, 3," +
                "'Como está meu bebê entre 37 à 40 semanas de gestação ?'," +
                "'Às 38 semanas de gravidez, o seu bebê mede cerca de 49,8 cm da cabeça aos pés e pesa aproximadamente 3050 g. \n\n" +
                "O bebê continua a ganhar peso e pode nascer a qualquer momento. \n\n" +
                "O cérebro está completamente preparado para se adaptar ao mundo exterior. \n\n" +
                "Os ossos do crânio ainda não estão completamente fechados para facilitar a passagem da cabeça pelo canal de parto. \n\n" +
                "O intestino continua a produzir mecônio (fezes produzidas pelo bebê). \n\n" +
                "O tamanho da cabeça é proporcional ao corpo.'),";

        String p3Trimestre3 = "(33, 3," +
                "'Existem os sinais de alarme falso do trabalho de parto e os sinais verdadeiros ?'," +
                "'Sim.'),";

        String p4Trimestre3 = "(34, 3," +
                "'Quais os sinais verdadeiros de trabalho de parto ?'," +
                "'1. Presença de contrações ritmadas entre 5 e 15 minutos, porém curtas, passam rápidas (duram de 30 à 60 segundos), e o colo do útero começa a dilatar; \n\n" +
                "2. Com o passar das horas, as contrações vão acontecendo a cada 3 minutos e dura 1 minuto cada contração (chama de fase ativa do parto); \n\n" +
                "3. Em seguida as contrações vão ocorrendo a cada 2 minutos, duram 90 segundos, é quando o útero está dilatado entre 8 a 10 centímetros; \n\n" +
                "Atenção: faça uso de um relógio para contar o tempo das contrações e coloque a mão em cima da barriga próxima ao estômago.'),";

        String p5Trimestre3 = "(35, 3," +
                "'Quais os sinais de alarme falso de trabalho de parto ?'," +
                "'1. Contrações irregulares (uma contração agora e outra daqui a cinco horas); \n\n" +
                "2. Dores nas costas; \n\n" +
                "3. Pode acontecer de perder o tampão mucoso (muco grosso que sai pela vagina), mas neste caso é interessante procurar a Unidade Básica ou a maternidade para avaliação.'),";

        String p6Trimestre3 = "(36, 3," +
                "'Com 41 semanas de gestação, se eu não tiver nenhum sinal de trabalho de parto devo procurar a maternidade ?'," +
                "'Sim.'),";


        String p1QueixasGestacao = "(41, 4," +
                "'Náuseas e vômitos'," +
                "'São frequentes nos primeiros três meses de gravidez, mais intenso pela manhã, ao acordar ou após um período de jejum prolongado. \n\n" +
                "Pioram como o cheiro de cigarro e algumas comidas. \n\n" +
                "As náuseas estão relacionadas a níveis crescentes de gonadotrofina coriônica humana (HCG) e de estrógeno.'),";

        String p2QueixasGestacao = "(42, 4," +
                "'Queixas urinárias'," +
                "'São frequentes nos primeiros e nos últimos meses de gravidez. A provável compressão da bexiga pelo útero gravídico, diminuindo a capacidade guardar mais urina, ocasiona o aumento de idas ao banho.'),";

        String p3QueixasGestacao = "(43, 4," +
                "'Dor abdominal/cólica'," +
                "'É comum no primeiro trimestre, pois acontece o desenvolvimento do feto. Nesta situação comunicar sempre a equipe de profissionais que acompanha o pré-natal.'),";

        String p4QueixasGestacao = "(44, 4," +
                "'Inchaço nos pés'," +
                "'Surge no 3º trimestre da gestação, limitando-se aos membros inferiores e, ocasionalmente às mãos. \n\n" +
                "Piora quando fica-se em pé por várias horas ou quando anda. \n\n" +
                "Desaparece pela manhã e pode piorar ao longo do dia. \n\n" +
                "Os edemas (inchaços) precisam ser acompanhados...'),";

        String p5QueixasGestacao = "(45, 4," +
                "'Azia'," +
                "'O refluxo do esôfago pode provocar azia, devido ao relaxamento do músculo liso (esfíncter esofágico inferior) no alto do estômago. \n\n" +
                " O aumento do volume do útero causa a compressão do estômago, ocasionando um aumento de refluxo. '),";

        String p6QueixasGestacao = "(46, 4," +
                "'Fraqueza/tontura'," +
                "'Estão associadas à hipoglicemia (pouco açúcar no sangue), alteração frequente quando a grávida passa muito tempo sem alimentar-se.'),";

        String p7QueixasGestacao = "(47, 4," +
                "'Dificuldade para respirar'," +
                "'Acontece mais no 3º trimestre (a partir do 7º mês), pois o útero comprime o diafragma.'),";

        String p8QueixasGestacao = "(48, 4," +
                "'Constipação intestinal/flatulências'," +
                "'Comum na gestação, agravada pelo esforço na evacuação ou pela consistência das fezes. \n\n" +
                "Comer alimentos com fibras ajuda a reduzir os desconfortos.'),";

        String p9QueixasGestacao = "(49, 4," +
                "'Dor lombar'," +
                "'A adaptação da postura materna para sustentar a barriga sobrecarrega as articulações da coluna vertebral, mas será aliviada após o parto.'),";

        String p10QueixasGestacao = "(410, 4," +
                "'Câimbras'," +
                "'Acontece devido o peso da barriga e as mudanças na circulação. \n\n" +
                "Fazer alongamentos, ficar em pé e massagear as pernas aliviam os desconfortos.'),";

        String p11QueixasGestacao = "(411, 4," +
                "'Pigmentação/cloasma gravídico, linha nigra'," +
                "'A gestação provoca alterações na pigmentação da pele e essas são mais observadas em mulheres de raça negra.'),";

        String p12QueixasGestacao = "(412, 4," +
                "'Sonolência'," +
                "'Devido à alteração do hormônio progesterona, algumas gestantes sentem muito sono no primeiro trimestre de gestação, ou seja, nos primeiros três meses de gravidez. \n\n" +
                "Mas depois desse período a sonolência diminui.'),";


        String p1DireitosDaMulher = "(51, 5," +
                "'O pai da criança pode participar das consultas de pré-natal ?'," +
                "'O homem/companheiro ou um familiar  deve participar das consultas de pré-natais, parto e puerpério, conforme preconiza a Lei nº 11.108, de 7 de abril de 2005.'),";

        String p2DireitosDaMulher = "(52, 5," +
                "'Quais são meus Direitos Sociais ?'," +
                "'\u2022 Prioridade nas filas para atendimentos em instituições públicas ou privadas; \n\n" +
                "\u2022 Prioridade para acomodar-se sentada em transportes coletivos; \n\n" +
                "\u2022 Os pais têm direito de registrar seu bebê e obter a Certidão de Nascimento, gratuitamente, em qualquer cartório; \n\n" +
                "\u2022 A mulher tem direito à creche para seus filhos nas empresas que possuírem em seus quadros funcionais pelo menos 30 mulheres com mais de 16 anos de idade.'),";

        String p3DireitosDaMulher = "(53, 5," +
                "'Quais são meus Direitos Trabalhistas ?'," +
                "'\u2022 Toda empregada gestante tem direito à estabilidade no emprego, desde confirmação da gravidez até cinco meses após o parto; \n\n" +
                "\u2022 A gestante tem direito à licença-maternidade de 120 dias (art. 392), sem prejuízo do emprego e do salário, devendo a gestante notificar o seu empregador da data do início do afastamento, que poderá ocorrer entre o 28º dia antes do parto e a ocorrência deste; \n\n" +
                "\u2022 O salário-maternidade é devido às seguradas empregadas, trabalhadoras avulsas, empregadas domésticas, contribuintes individuais, facultativas e seguradas especiais, por ocasião do parto, inclusive o natimorto, aborto, adoção ou guarda judicial; \n\n" +
                "\u2022 Nos casos em que a criança venha a falecer durante a licença-maternidade, o salário-maternidade não será interrompido; \n\n" +
                "\u2022 Em casos de aborto, será pago o benefício por duas semanas, a licença varia entre 14 à 30 dias de acordo com a prescrição médica.'),";

        String p4DireitosDaMulher = "(54, 5," +
                "'Quais são os  Direitos do pai ?'," +
                "'\u2022 O pai tem direito a participar do pré-natal; \n\n" +
                "\u2022 O pai tem direito a acompanhar a gestante durante o pré-parto, parto e pós-parto; \n\n" +
                "\u2022 O pai tem direito à licença-paternidade de cinco dias contínuos logo após o nascimento do bebê.'),";


        String p1PuerperioCuidadoMae = "(61, 6," +
                "'Cuidado com as mamas'," +
                "'\u2022 Usar sutiã; \n\n" +
                "\u2022 Colocar a criança para mamar na posição correta; \n\n" +
                "\u2022 Se o bico do peito ferir, corrigir a pega da criança na hora de mamar, hidratar o bico com o próprio leite e pegar um pouco de sol; \n\n" +
                "\u2022 Mamas cheias de leite, quente e pontos endurecidos é sinal de mama ingurgitada. Orienta-se fazer massagem em movimentos circulares em toda a mama e depois colocar a criança pra mamar. Se depois que a criança mamar ainda tiver muito leite no peito, deve-se então retirar o excesso por meio do desmame.'),";

        String p2PuerperioCuidadoMae = "(62, 6," +
                "'Alimentação'," +
                "'Fazer uso de uma alimentação variada (feijão, carnes, frutas, verduras e ovos), se possível solicitando orientação de uma nutricionista.'),";

        String p3PuerperioCuidadoMae = "(63, 6," +
                "'Líquidos'," +
                "'Tomar bastante líquido como água e sucos, pois ajuda bastante na produção de leite.'),";

        String p4PuerperioCuidadoMae = "(64, 6," +
                "'Higiene'," +
                "'Tomar banho e trocar roupas e absorventes sempre que necessário. Isto ajuda a previnir infecções no período do resguardo.'),";

        String p5PuerperioCuidadoMae = "(65, 6," +
                "'Dor nas pernas'," +
                "'Se depois do parto apresentar dor nas pernas e dificuldade de andar, avisar a equipe de profissionais que acompanhou no parto ou no pré-natal.'),";

        String p6PuerperioCuidadoMae = "(66, 6," +
                "'Sinais de infecção'," +
                "'Febre, secreção com mau cheiro e/ou secreção de cor verde liberada pela vagina ou local da cirurgia são sinais de infecção.\n\n" +
                "Se aparecer algum sinal de infecção procurar imediatamente os profissionais de saúde para avaliar.'),";

        String p7PuerperioCuidadoMae = "(67, 6," +
                "'Sangramento vaginal'," +
                "'É normal o sangramento vaginal depois do parto, ele pode durar em média duas semanas e vai mudando de cor. Começa mais vermelho e vai ficando mais claro.\n\n" +
                "Qualquer mau cheiro no sangramento vaginal, comunicar à equipe de profissionais.'),";

        String p8PuerperioCuidadoMae = "(68, 6," +
                "'Depressão pós parto'," +
                "'Presença de choro, fadiga, raiva de si mesma e do bebê podem ser sinais de depressão.\n\n" +
                "Procurar imediatamente os profissionais de saúde.'),";

        String p9PuerperioCuidadoMae = "(69, 6," +
                "'Planejamento familiar'," +
                "'Depois do parto é recomendado conversar com os profissionais sobre o melhor método para evitar a gravidez sem planejamento.'),";

        String p1PuerperioCuidadoRecemNascido = "(71, 7," +
                "'Higienização'," +
                "'O banho deve se rápido para a criança não perder calor.\n\n" +
                "Um banho por dia é o suficiente. Evite o excesso de sabonete, shampoo e perfumes, pois a criança pode desenvolver certas alergias.'),";

        String p2PuerperioCuidadoRecemNascido = "(72, 7," +
                "'Alimentação'," +
                "'Somente o leite do peito até os 6 meses de vida.'),";

        String p3PuerperioCuidadoRecemNascido = "(73, 7," +
                "'Limpeza do coto umbilical'," +
                "'Fazer a limpeza do coto umbilical com água e sabão e depois secar com uma fralda limpa.\n\n" +
                "Esses cuidados devem ser tomados três vezes ao dia ou mais.\n\n" +
                "Geralmente  com 15 dias o coto seca e cai.'),";

        String p4PuerperioCuidadoRecemNascido = "(74, 7," +
                "'Teste da orelhinha'," +
                "'Deve ser feito a partir do segundo dia de vida do bebê pelo fonoaudiólogo.\n\n" +
                "O teste serve para avaliar se a criança tem algum problema de audição.'),";

        String p5PuerperioCuidadoRecemNascido = "(75, 7," +
                "'Teste do coraçãozinho'," +
                "'O teste do coraçãozinho é feito na maternidade que a criança nasce para saber se ela tem algum problema no coração.\n\n" +
                "O exame deve ser feito antes da alta da maternidade.'),";

        String p6PuerperioCuidadoRecemNascido = "(76, 7," +
                "'Teste do olhinho'," +
                "'O teste do olhinho é feito na maternidade onde a criança nasce e serve para saber se ela tem algum problema de visão (catarata, tumor, ...).'),";

        String p7PuerperioCuidadoRecemNascido = "(77, 7," +
                "'Teste da linguinha'," +
                "'É um teste para avaliar se a língua da criança está presa, o que dificulta a mamar, engolir e falar.\n\n" +
                "O pediatra deve fazer a avaliação na maternidade.'),";

        String p8PuerperioCuidadoRecemNascido = "(78, 7," +
                "'Teste do pezinho'," +
                "'O teste do pezinho deve ser feito entre o segundo e o quinto dia de vida da criança.\n\n" +
                "Serve para identificar várias doenças (problemas na tireóide, no sangue, ...).\n\n" +
                "O exame é feito nas Unidades Básicas de Saúde.'),";

        String p9PuerperioCuidadoRecemNascido = "(79, 7," +
                "'Consultas do crescimento e desenvolvimento'," +
                "'Quando a criança estiver com 30 dias de vida, levá-la à primeira consulta do crescimento e desenvolvimento no posto de saúde.\n\n" +
                "Lembre-se de marcar a consulta com antecedência.'),";

        String p10PuerperioCuidadoRecemNascido = "(710, 7," +
                "'Vacinas da criança'," +
                "'Ao nascer:\n" +
                "\u2022 BCG;\n" +
                "\u2022 1° dose de hepatite B.\n\n" +
                "2 meses de vida: (1ª dose das vacinas)\n" +
                "\u2022 Pentavalente;\n" +
                "\u2022 Poliomielite (VIP);\n" +
                "\u2022 Rotavírus;\n" +
                "\u2022 Pneumocócica 10-valente.\n\n" +
                "3 meses de vida: (1ª dose das vacinas)\n" +
                "\u2022 Meningocócica C.\n\n" +
                "4 meses de vida: (2ª dose das vacinas)\n" +
                "\u2022 Pentavalente;\n" +
                "\u2022 Poliomielite (VIP);\n" +
                "\u2022 Rotavírus;\n" +
                "\u2022 Pneumocócica 10-valente.\n\n" +
                "5 meses de vida: (2ª dose das vacinas)\n" +
                "\u2022 Meningocócica C.\n\n" +
                "6 meses de vida: (3ª dose das vacinas)\n" +
                "\u2022 Pentavalente;\n" +
                "\u2022 Poliomielite (VIP).\n\n" +
                "9 meses de vida:\n" +
                "\u2022 Vacina da Febre Amarela (quando recomendado ou em áreas que tenham o risco de aquirir a doença).\n\n" +
                "12 meses de vida:\n" +
                "\u2022 1ª dose da tríplice viral (SRC);\n" +
                "\u2022 Reforço da vacina pneumocócica 10-valente;\n" +
                "\u2022 Reforço da vacina Meningocócica C.\n\n" +
                "15 meses de vida:\n" +
                "\u2022 1º reforço da VOP (vacina oral poliomielite);\n" +
                "\u2022 Dose única da vacina hepatite A;\n" +
                "\u2022 1ª Reforço DTP (tríplice viral);\n" +
                "\u2022 Dose Única da tetra viral (SCRV).\n\n" +
                "4 anos de idade:\n" +
                "\u2022 2º Reforço da VOP;\n" +
                "\u2022 2º Reforço da DTP.\n\n" +
                "Atenção: procurar a Unidade Básica de Saúde para fazer as vacinas. Levar a caderneta da criança.');";


        String sql =
                p1Ttrimestre1 + p2Trimestre1 + p3Trimestre1 + p4Trimestre1 + p5Trimestre1 + p6Trimestre1 + p7Trimestre1 +
                        p1Trimestre2 + p2Trimestre2 + p3Trimestre2 + p4Trimestre2 + p5Trimestre2 + p6Trimestre2 + p7Trimestre2 + p8Trimestre2 + p9Trimestre2 + p10Trimestre2 +
                        p1Trimestre3 + p2Trimestre3 + p3Trimestre3 + p4Trimestre3 + p5Trimestre3 + p6Trimestre3 +
                        p1QueixasGestacao + p2QueixasGestacao + p3QueixasGestacao + p4QueixasGestacao + p5QueixasGestacao + p6QueixasGestacao + p7QueixasGestacao + p8QueixasGestacao + p9QueixasGestacao + p10QueixasGestacao + p11QueixasGestacao + p12QueixasGestacao +
                        p1DireitosDaMulher + p2DireitosDaMulher + p3DireitosDaMulher + p4DireitosDaMulher +
                        p1PuerperioCuidadoMae + p2PuerperioCuidadoMae + p3PuerperioCuidadoMae + p4PuerperioCuidadoMae + p5PuerperioCuidadoMae + p6PuerperioCuidadoMae + p7PuerperioCuidadoMae + p8PuerperioCuidadoMae + p9PuerperioCuidadoMae +
                        p1PuerperioCuidadoRecemNascido + p2PuerperioCuidadoRecemNascido + p3PuerperioCuidadoRecemNascido + p4PuerperioCuidadoRecemNascido + p5PuerperioCuidadoRecemNascido + p6PuerperioCuidadoRecemNascido + p7PuerperioCuidadoRecemNascido + p8PuerperioCuidadoRecemNascido + p9PuerperioCuidadoRecemNascido + p10PuerperioCuidadoRecemNascido;

        db.execSQL(sql);
    }
}
