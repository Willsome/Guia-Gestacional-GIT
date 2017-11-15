package com.scriptpoin.guiagestacional.caderneta.ultrassonografia;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Willi on 05-Sep-17.
 */

public class Ultrassonografia implements Serializable {

    private Long id;

    private Long solicitacao;

    private Long numeroConsultaSolicitacao;
    private Long numeroConsultaResultado;

    private Calendar data;
    private Calendar igDum;
    private Calendar igUsg;
    private int pesoFetal;
    private String placenta;
    private Double liquidoAmniotico;


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

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Calendar getIgDum() {
        return igDum;
    }

    public void setIgDum(Calendar igDum) {
        this.igDum = igDum;
    }

    public Calendar getIgUsg() {
        return igUsg;
    }

    public void setIgUsg(Calendar igUsg) {
        this.igUsg = igUsg;
    }

    public int getPesoFetal() {
        return pesoFetal;
    }

    public void setPesoFetal(int pesoFetal) {
        this.pesoFetal = pesoFetal;
    }

    public String getPlacenta() {
        return placenta;
    }

    public void setPlacenta(String placenta) {
        this.placenta = placenta;
    }

    public Double getLiquidoAmniotico() {
        return liquidoAmniotico;
    }

    public void setLiquidoAmniotico(Double liquidoAmniotico) {
        this.liquidoAmniotico = liquidoAmniotico;
    }
}
