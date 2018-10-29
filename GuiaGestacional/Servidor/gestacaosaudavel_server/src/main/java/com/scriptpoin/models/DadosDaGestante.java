package com.scriptpoin.models;

public class DadosDaGestante {

    private DadosPessoais dadosPessoais;
    private DadosObstetricos dadosObstetricos;

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public DadosObstetricos getDadosObstetricos() {
        return dadosObstetricos;
    }

    public void setDadosObstetricos(DadosObstetricos dadosObstetricos) {
        this.dadosObstetricos = dadosObstetricos;
    }

}
