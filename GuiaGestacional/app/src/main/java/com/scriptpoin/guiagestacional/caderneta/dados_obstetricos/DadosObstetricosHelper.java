package com.scriptpoin.guiagestacional.caderneta.dados_obstetricos;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

/**
 * Created by Willi on 27-Aug-17.
 */

public class DadosObstetricosHelper {

    // FORMULÁRIO
    private TextView doTvDpDum;
    private TextView doTvDpDpp;

    // CADERNETA
    private TextView doTvDum;
    private TextView doTvDpp;

    // OUTROS
    private DadosObstetricos dadosObstetricos;


    public DadosObstetricosHelper(Activity activity, int i, View view) {

        if (i == 1) {
            doTvDpDum = (TextView) activity.findViewById(R.id.doTvDpDum);
            doTvDpDpp = (TextView) activity.findViewById(R.id.doTvDpDpp);
        } else if (i == 2) {
            doTvDum = (TextView) view.findViewById(R.id.doTvDum);
            doTvDpp = (TextView) view.findViewById(R.id.doTvDpp);
        }

        this.dadosObstetricos = new DadosObstetricos();
    }

    public TextView getDoTvDpDum() {
        return doTvDpDum;
    }

    public TextView getDoTvDpDpp() {
        return doTvDpDpp;
    }

    public DadosObstetricos pegaDadosObstetricos() throws Exception {

        DadosObstetricos dadosObstetricos;

        if (doTvDpDum.getText().toString().equals("[ADICIONAR]")
                || doTvDpDpp.getText().toString().equals("[ADICIONAR]")) {

            throw new Exception("Campo vazio.");

        } else {

            dadosObstetricos = new DadosObstetricos();
            dadosObstetricos.setDum(doTvDpDum.getText().toString());
            dadosObstetricos.setDpp(doTvDpDpp.getText().toString());

            dadosObstetricos.setId(this.dadosObstetricos.getId());
        }

        return dadosObstetricos;
    }

    public void preencheFormularioDadosObstetricos(DadosObstetricos dadosObstetricos) {

        doTvDpDum.setText(dadosObstetricos.getDum());
        doTvDpDpp.setText(dadosObstetricos.getDpp());

        this.dadosObstetricos = dadosObstetricos;
    }

    public void preencheDadosObstetricos(DadosObstetricos dadosObstetricos) {

        doTvDum.setText(dadosObstetricos.getDum());
        doTvDpp.setText(dadosObstetricos.getDpp());
    }
}
