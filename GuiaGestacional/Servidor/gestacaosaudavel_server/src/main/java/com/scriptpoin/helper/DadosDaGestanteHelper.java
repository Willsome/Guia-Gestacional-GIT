package com.scriptpoin.helper;

import com.scriptpoin.models.DadosDaGestante;
import com.scriptpoin.models.DadosObstetricos;
import com.scriptpoin.models.DadosPessoais;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Iterator;

public class DadosDaGestanteHelper {

    private DadosDaGestante dadosDaGestante = new DadosDaGestante();

    public DadosDaGestante getDadosDaGestante() {
        return dadosDaGestante;
    }

    public void addDadosPessoais(JSONObject dadosPessoaisJSON) {
        dadosPessoaisJSON = dadosPessoaisJSON.getJSONObject("dados_pessoais");

        Iterator<String> chaves = dadosPessoaisJSON.keys();

        DadosPessoais dadosPessoais = new DadosPessoais();

        while (chaves.hasNext()) {
            String campo = chaves.next();

            if (campo.equals("id")) {
                dadosPessoais.setId(dadosPessoaisJSON.getLong(campo));

            } else if (campo.equals("nome")) {
                dadosPessoais.setNome(dadosPessoaisJSON.getString(campo));

            } else if (campo.equals("data_nascimento")) {
                Long dataNascimentoEmMilis = dadosPessoaisJSON.getLong(campo);
                Calendar dataNascimento = Calendar.getInstance();
                dataNascimento.setTimeInMillis(dataNascimentoEmMilis);
                dadosPessoais.setDataNascimento(dataNascimento);

            } else if (campo.equals("idade")) {
                dadosPessoais.setIdade(dadosPessoaisJSON.getInt(campo));

            } else if (campo.equals("endereco")) {
                dadosPessoais.setEndereco(dadosPessoaisJSON.getString(campo));

            } else if (campo.equals("nome_companheiro")) {
                dadosPessoais.setNomeCompanheiro(dadosPessoaisJSON.getString(campo));
            }
        }

        dadosDaGestante.setDadosPessoais(dadosPessoais);
    }


    public void addDadosObstetricos(JSONObject dadosObstetricosJSON) {
        dadosObstetricosJSON = dadosObstetricosJSON.getJSONObject("dados_obstetricos");

        Iterator<String> chaves = dadosObstetricosJSON.keys();

        DadosObstetricos dadosObstetricos = new DadosObstetricos();

        while (chaves.hasNext()) {
            String campo = chaves.next();

            if (campo.equals("id")) {
                dadosObstetricos.setId(dadosObstetricosJSON.getLong(campo));

            } else if (campo.equals("dum")) {
                Long dumEmMilis = dadosObstetricosJSON.getLong(campo);
                Calendar dum = Calendar.getInstance();
                dum.setTimeInMillis(dumEmMilis);
                dadosObstetricos.setDum(dum);

            } else if (campo.equals("dpp")) {
                Long dppEmMilis = dadosObstetricosJSON.getLong(campo);
                Calendar dpp = Calendar.getInstance();
                dpp.setTimeInMillis(dppEmMilis);
                dadosObstetricos.setDpp(dpp);

            }
        }

        dadosDaGestante.setDadosObstetricos(dadosObstetricos);
    }

    public void addUltrassonografia(JSONArray ultrassonografiasJSON) {

    }
}
