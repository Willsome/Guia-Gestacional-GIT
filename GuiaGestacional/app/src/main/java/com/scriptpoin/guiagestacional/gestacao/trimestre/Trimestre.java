package com.scriptpoin.guiagestacional.gestacao.trimestre;

import android.widget.ImageView;

import com.scriptpoin.guiagestacional.gestacao.Gestacao;

import java.io.Serializable;

/**
 * Created by Willi on 23-Jul-17.
 */

public class Trimestre implements Serializable {

    private int id;
    private String pergunta;
    private String resposta;

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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}
