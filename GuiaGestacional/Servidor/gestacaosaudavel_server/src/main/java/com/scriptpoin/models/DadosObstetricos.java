package com.scriptpoin.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;

@Entity
public class DadosObstetricos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Calendar dum;

    @NotBlank
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
