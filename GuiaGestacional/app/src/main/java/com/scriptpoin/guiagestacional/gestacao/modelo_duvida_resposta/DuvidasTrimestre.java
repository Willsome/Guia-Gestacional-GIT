package com.scriptpoin.guiagestacional.gestacao.modelo_duvida_resposta;

import java.io.Serializable;

/**
 * Created by Willi on 31-Jul-17.
 */

public class DuvidasTrimestre implements Serializable {

    private int id;
    private int trimestre;
    private String pergunta;
    private String resposta;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getTrimestre() {
        return trimestre;
    }
    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
    }

    public String getPergunta() {
        return pergunta;
    }
    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }
    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
