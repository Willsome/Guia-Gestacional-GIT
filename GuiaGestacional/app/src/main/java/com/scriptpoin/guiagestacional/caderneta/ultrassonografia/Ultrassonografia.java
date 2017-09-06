package com.scriptpoin.guiagestacional.caderneta.ultrassonografia;

/**
 * Created by Willi on 05-Sep-17.
 */

public class Ultrassonografia {

    private String data;
    private String igDum;
    private String igUsg;
    private int pesoFetal;
    private String placenta;
    private float liquidoAmniotico;

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getIgDum() {
        return igDum;
    }
    public void setIgDum(String igDum) {
        this.igDum = igDum;
    }

    public String getIgUsg() {
        return igUsg;
    }
    public void setIgUsg(String igUsg) {
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

    public float getLiquidoAmniotico() {
        return liquidoAmniotico;
    }
    public void setLiquidoAmniotico(float liquidoAmniotico) {
        this.liquidoAmniotico = liquidoAmniotico;
    }
}
