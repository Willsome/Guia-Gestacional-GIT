package com.scriptpoin.gestacaosaudavel.caderneta.exames_solicitados_resultados;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Willi on 02-Sep-17.
 */

public class ExamesSolicitadosResultados implements Serializable, Comparable<ExamesSolicitadosResultados> {

    private Long id;

    private Long solicitacao;

    private Long numeroConsultaSolicitacao;
    private Long numeroConsultaResultado;

    private Exame exame;
    private String resultado;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Long solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Long getNumeroConsultaSolicitacao() {
        return numeroConsultaSolicitacao;
    }

    public void setNumeroConsultaSolicitacao(Long numeroConsultaSolicitacao) {
        this.numeroConsultaSolicitacao = numeroConsultaSolicitacao;
    }

    public Long getNumeroConsultaResultado() {
        return numeroConsultaResultado;
    }

    public void setNumeroConsultaResultado(Long numeroConsultaResultado) {
        this.numeroConsultaResultado = numeroConsultaResultado;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public int compareTo(@NonNull ExamesSolicitadosResultados outroExamesSolicitadosResultados) {
        if (this.exame.getId() > outroExamesSolicitadosResultados.getExame().getId()) return 1;
        if (this.exame.getId() < outroExamesSolicitadosResultados.getExame().getId()) return -1;
        return 0;
    }
}
