package com.scriptpoin.guiagestacional.data_das_consultas;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Willi on 04-Oct-17.
 */

public class Consulta implements Serializable {

    private Long id;

    private int numeroConsulta;
    private Calendar dataHoraConsulta;
    private String tipoProfissional;
    private String nomeProfissional;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroConsulta() {
        return numeroConsulta;
    }

    public void setNumeroConsulta(int numeroConsulta) {
        this.numeroConsulta = numeroConsulta;
    }

    public Calendar getDataHoraConsulta() {
        return dataHoraConsulta;
    }

    public void setDataHoraConsulta(Calendar dataHoraConsulta) {
        this.dataHoraConsulta = dataHoraConsulta;
    }

    public String getTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(String tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }
}
