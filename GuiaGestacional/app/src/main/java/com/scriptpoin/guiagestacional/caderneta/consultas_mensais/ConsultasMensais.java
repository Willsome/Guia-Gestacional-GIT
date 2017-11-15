package com.scriptpoin.guiagestacional.caderneta.consultas_mensais;


import java.io.Serializable;
import java.util.Calendar;

public class ConsultasMensais implements Serializable {

    private Long numeroConsulta;

    private Calendar dataConsulta;
    private String queixa;
    private double ig;
    private double peso;
    private double imc;
    private String edema;
    private double paI, paII;
    private String alturaUterina;
    private String posicaoFetal;
    private int bcf;
    private String movFetal;
    private String tipoProfissional;
    private String nomeDoProfissional;


    public Long getNumeroConsulta() {
        return numeroConsulta;
    }

    public void setNumeroConsulta(Long id) {
        this.numeroConsulta = id;
    }

    public Calendar getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Calendar dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getQueixa() {
        return queixa;
    }

    public void setQueixa(String queixa) {
        this.queixa = queixa;
    }

    public double getIg() {
        return ig;
    }

    public void setIg(double ig) {
        this.ig = ig;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getEdema() {
        return edema;
    }

    public void setEdema(String edema) {
        this.edema = edema;
    }

    public double getPaI() {
        return paI;
    }

    public void setPaI(double paI) {
        this.paI = paI;
    }

    public double getPaII() {
        return paII;
    }

    public void setPaII(double paII) {
        this.paII = paII;
    }

    public String getAlturaUterina() {
        return alturaUterina;
    }

    public void setAlturaUterina(String alturaUterina) {
        this.alturaUterina = alturaUterina;
    }

    public String getPosicaoFetal() {
        return posicaoFetal;
    }

    public void setPosicaoFetal(String posicaoFetal) {
        this.posicaoFetal = posicaoFetal;
    }

    public int getBcf() {
        return bcf;
    }

    public void setBcf(int bcf) {
        this.bcf = bcf;
    }

    public String getMovFetal() {
        return movFetal;
    }

    public void setMovFetal(String movFetal) {
        this.movFetal = movFetal;
    }

    public String getTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(String tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
    }

    public String getNomeDoProfissional() {
        return nomeDoProfissional;
    }

    public void setNomeDoProfissional(String nomeDoProfissional) {
        this.nomeDoProfissional = nomeDoProfissional;
    }

}
