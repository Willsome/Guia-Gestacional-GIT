package com.scriptpoin.gestacaosaudavel.caderneta.exames_solicitados_resultados;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Willi on 06-Nov-17.
 */

public class Exame implements Serializable, Comparable<Exame> {

    private Long id;

    private String nomeDoExame;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDoExame() {
        return nomeDoExame;
    }

    public void setNomeDoExame(String nomeDoExame) {
        this.nomeDoExame = nomeDoExame;
    }

    @Override
    public int compareTo(@NonNull Exame outroExame) {
        if (this.id > outroExame.getId()) return 1;
        if (this.id < outroExame.getId()) return -1;
        return 0;
    }
}
