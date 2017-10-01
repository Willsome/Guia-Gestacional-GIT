package com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados;

import java.io.Serializable;

/**
 * Created by Willi on 02-Sep-17.
 */

public class ExamesSolicitadosResultados implements Serializable {

    private Long id;

    private int aboRh;
    private int glicemiaJejum;
    private int toleranciaGlicose;
    private int sifilis;
    private int Vdrl;
    private int Hiv;
    private int hepatiteBC;
    private int Hbsag;
    private int toxoplasmose;
    private int hemoglobina;
    private int urinaEas;
    private int urinaCultura;
    private int coombs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAboRh() {
        return aboRh;
    }

    public void setAboRh(int aboRh) {
        this.aboRh = aboRh;
    }

    public int getGlicemiaJejum() {
        return glicemiaJejum;
    }

    public void setGlicemiaJejum(int glicemiaJejum) {
        this.glicemiaJejum = glicemiaJejum;
    }

    public int getToleranciaGlicose() {
        return toleranciaGlicose;
    }

    public void setToleranciaGlicose(int toleranciaGlicose) {
        this.toleranciaGlicose = toleranciaGlicose;
    }

    public int getSifilis() {
        return sifilis;
    }

    public void setSifilis(int sifilis) {
        this.sifilis = sifilis;
    }

    public int getVdrl() {
        return Vdrl;
    }

    public void setVdrl(int vdrl) {
        Vdrl = vdrl;
    }

    public int getHiv() {
        return Hiv;
    }

    public void setHiv(int hiv) {
        Hiv = hiv;
    }

    public int getHepatiteBC() {
        return hepatiteBC;
    }

    public void setHepatiteBC(int hepatiteBC) {
        this.hepatiteBC = hepatiteBC;
    }

    public int getHbsag() {
        return Hbsag;
    }

    public void setHbsag(int hbsag) {
        Hbsag = hbsag;
    }

    public int getToxoplasmose() {
        return toxoplasmose;
    }

    public void setToxoplasmose(int toxoplasmose) {
        this.toxoplasmose = toxoplasmose;
    }

    public int getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(int hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public int getUrinaEas() {
        return urinaEas;
    }

    public void setUrinaEas(int urinaEas) {
        this.urinaEas = urinaEas;
    }

    public int getUrinaCultura() {
        return urinaCultura;
    }

    public void setUrinaCultura(int urinaCultura) {
        this.urinaCultura = urinaCultura;
    }

    public int getCoombs() {
        return coombs;
    }

    public void setCoombs(int coombs) {
        this.coombs = coombs;
    }
}
