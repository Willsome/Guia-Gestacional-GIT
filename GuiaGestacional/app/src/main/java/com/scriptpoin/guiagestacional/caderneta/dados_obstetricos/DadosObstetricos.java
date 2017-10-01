package com.scriptpoin.guiagestacional.caderneta.dados_obstetricos;

import java.io.Serializable;

/**
 * Created by Willi on 27-Aug-17.
 */

public class DadosObstetricos implements Serializable {

    private Long id;

    private String dum;
    private String dpp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDum() {
        return dum;
    }

    public void setDum(String dum) {
        this.dum = dum;
    }

    public String getDpp() {
        return dpp;
    }

    public void setDpp(String dpp) {
        this.dpp = dpp;
    }
}
