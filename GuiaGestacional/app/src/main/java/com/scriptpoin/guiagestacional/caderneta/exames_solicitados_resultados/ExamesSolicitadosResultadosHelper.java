package com.scriptpoin.guiagestacional.caderneta.exames_solicitados_resultados;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import com.scriptpoin.guiagestacional.R;

/**
 * Created by Willi on 02-Sep-17.
 */

public class ExamesSolicitadosResultadosHelper {

    private CheckBox esCbAbo;
    private CheckBox esCbGlicemiaJejum;
    private CheckBox esCbToleranciaGlicose;
    private CheckBox esCbSifilis;
    private CheckBox esCbVdrl;
    private CheckBox esCbHiv;
    private CheckBox esCbHepatiteBC;
    private CheckBox esCbHbsag;
    private CheckBox esCbToxoplasmose;
    private CheckBox esCbHemoglobina;
    private CheckBox esCbUrinaEas;
    private CheckBox esCbUrinaCultura;
    private CheckBox esCbCoombs;

    public ExamesSolicitadosResultadosHelper(Activity activity, int i, View view) {

        if(i == 1) {
            esCbAbo = (CheckBox) activity.findViewById(R.id.esCbAboF);
            esCbGlicemiaJejum = (CheckBox) activity.findViewById(R.id.esCbGlicemiaJejumF);
            esCbToleranciaGlicose = (CheckBox) activity.findViewById(R.id.esCbToleranciaGlicoseF);
            esCbSifilis = (CheckBox) activity.findViewById(R.id.esCbSifilisF);
            esCbVdrl = (CheckBox) activity.findViewById(R.id.esCbVdrlF);
            esCbHiv = (CheckBox) activity.findViewById(R.id.esCbHivF);
            esCbHepatiteBC = (CheckBox) activity.findViewById(R.id.esCbHepatiteBCF);
            esCbHbsag = (CheckBox) activity.findViewById(R.id.esCbHbsagF);
            esCbToxoplasmose = (CheckBox) activity.findViewById(R.id.esCbToxoplasmoseF);
            esCbHemoglobina = (CheckBox) activity.findViewById(R.id.esCbHemoglobinaF);
            esCbUrinaEas = (CheckBox) activity.findViewById(R.id.esCbUrinaEasF);
            esCbUrinaCultura = (CheckBox) activity.findViewById(R.id.esCbUrinaCulturaF);
            esCbCoombs = (CheckBox) activity.findViewById(R.id.esCbCoombsF);
        } else if(i == 2) {
            esCbAbo = (CheckBox) view.findViewById(R.id.esCbAbo);
            esCbGlicemiaJejum = (CheckBox) view.findViewById(R.id.esCbGlicemiaJejum);
            esCbToleranciaGlicose = (CheckBox) view.findViewById(R.id.esCbToleranciaGlicose);
            esCbSifilis = (CheckBox) view.findViewById(R.id.esCbSifilis);
            esCbVdrl = (CheckBox) view.findViewById(R.id.esCbVdrl);
            esCbHiv = (CheckBox) view.findViewById(R.id.esCbHiv);
            esCbHepatiteBC = (CheckBox) view.findViewById(R.id.esCbHepatiteBC);
            esCbHbsag = (CheckBox) view.findViewById(R.id.esCbHbsag);
            esCbToxoplasmose = (CheckBox) view.findViewById(R.id.esCbToxoplasmose);
            esCbHemoglobina = (CheckBox) view.findViewById(R.id.esCbHemoglobina);
            esCbUrinaEas = (CheckBox) view.findViewById(R.id.esCbUrinaEas);
            esCbUrinaCultura = (CheckBox) view.findViewById(R.id.esCbUrinaCultura);
            esCbCoombs = (CheckBox) view.findViewById(R.id.esCbCoombs);
        }
    }

    public void preencheExamesSolicitadosResultados(ExamesSolicitadosResultados es) {

        if(es.getAboRh() == 1) {
            esCbAbo.setChecked(true);
        } else {
            esCbAbo.setChecked(false);
        }

        if(es.getGlicemiaJejum() == 1) {
            esCbGlicemiaJejum.setChecked(true);
        } else {
            esCbGlicemiaJejum.setChecked(false);
        }

        if(es.getToleranciaGlicose() == 1) {
            esCbToleranciaGlicose.setChecked(true);
        } else {
            esCbToleranciaGlicose.setChecked(false);
        }

        if(es.getSifilis() == 1) {
            esCbSifilis.setChecked(true);
        } else {
            esCbSifilis.setChecked(false);
        }

        if(es.getVdrl() == 1) {
            esCbVdrl.setChecked(true);
        } else {
            esCbVdrl.setChecked(false);
        }

        if(es.getHiv() == 1) {
            esCbHiv.setChecked(true);
        } else {
            esCbHiv.setChecked(false);
        }

        if(es.getHepatiteBC() == 1) {
            esCbHepatiteBC.setChecked(true);
        } else {
            esCbHepatiteBC.setChecked(false);
        }

        if(es.getHbsag() == 1) {
            esCbHbsag.setChecked(true);
        } else {
            esCbHbsag.setChecked(false);
        }

        if(es.getToxoplasmose() == 1) {
            esCbToxoplasmose.setChecked(true);
        } else {
            esCbToxoplasmose.setChecked(false);
        }

        if(es.getHemoglobina() == 1) {
            esCbHemoglobina.setChecked(true);
        } else {
            esCbHemoglobina.setChecked(false);
        }

        if(es.getUrinaEas() == 1) {
            esCbUrinaEas.setChecked(true);
        } else {
            esCbUrinaEas.setChecked(false);
        }

        if(es.getUrinaCultura() == 1) {
            esCbUrinaCultura.setChecked(true);
        } else {
            esCbUrinaCultura.setChecked(false);
        }

        if(es.getCoombs() == 1) {
            esCbCoombs.setChecked(true);
        } else {
            esCbCoombs.setChecked(false);
        }
    }

    public ExamesSolicitadosResultados pegaExamesSolicitadosResultados() {

        ExamesSolicitadosResultados es = new ExamesSolicitadosResultados();

        if(esCbAbo.isChecked()) {
            es.setAboRh(1);
        } else {
            es.setAboRh(0);
        }

        if(esCbGlicemiaJejum.isChecked()) {
            es.setGlicemiaJejum(1);
        } else {
            es.setGlicemiaJejum(0);
        }

        if(esCbToleranciaGlicose.isChecked()) {
            es.setToleranciaGlicose(1);
        } else {
            es.setToleranciaGlicose(0);
        }

        if(esCbSifilis.isChecked()) {
            es.setSifilis(1);
        } else {
            es.setSifilis(0);
        }

        if(esCbVdrl.isChecked()) {
            es.setVdrl(1);
        } else {
            es.setVdrl(0);
        }

        if(esCbHiv.isChecked()) {
            es.setHiv(1);
        } else {
            es.setHiv(0);
        }

        if(esCbHepatiteBC.isChecked()) {
            es.setHepatiteBC(1);
        } else {
            es.setHepatiteBC(0);
        }

        if(esCbHbsag.isChecked()) {
            es.setHbsag(1);
        } else {
            es.setHbsag(0);
        }

        if(esCbToxoplasmose.isChecked()) {
            es.setToxoplasmose(1);
        } else {
            es.setToxoplasmose(0);
        }

        if(esCbHemoglobina.isChecked()) {
            es.setHemoglobina(1);
        } else {
            es.setHemoglobina(0);
        }

        if(esCbUrinaEas.isChecked()) {
            es.setUrinaEas(1);
        } else {
            es.setUrinaEas(0);
        }

        if(esCbUrinaCultura.isChecked()) {
            es.setUrinaCultura(1);
        } else {
            es.setUrinaCultura(0);
        }

        if(esCbCoombs.isChecked()) {
            es.setCoombs(1);
        } else {
            es.setCoombs(0);
        }

        return es;
    }

    

}
