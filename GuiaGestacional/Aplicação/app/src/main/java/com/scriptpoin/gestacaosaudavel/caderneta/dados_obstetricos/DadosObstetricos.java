package com.scriptpoin.gestacaosaudavel.caderneta.dados_obstetricos;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Willi on 27-Aug-17.
 */

public class DadosObstetricos implements Serializable {

    private Long id;

    private Calendar dum;
    private Calendar dpp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDum() {
        return dum;
    }

    public void setDum(Calendar dum) {
        this.dum = dum;
    }

    public Calendar getDpp() {
        return dpp;
    }

    public void setDpp(Calendar dpp) {
        this.dpp = dpp;
    }
}
