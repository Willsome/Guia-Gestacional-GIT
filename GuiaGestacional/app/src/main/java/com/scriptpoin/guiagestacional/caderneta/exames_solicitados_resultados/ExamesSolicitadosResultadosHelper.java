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

    private ExamesSolicitadosResultados examesSolicitadosResultados;

    public ExamesSolicitadosResultadosHelper(Activity activity, int i, View view) {

        if (i == 1) {
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
        } else if (i == 2) {
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

        this.examesSolicitadosResultados = new ExamesSolicitadosResultados();
    }

    public void preencheExamesSolicitadosResultados(ExamesSolicitadosResultados examesSolicitadosResultados) {

        if (examesSolicitadosResultados.getAboRh() == 1) {
            esCbAbo.setChecked(true);
        } else {
            esCbAbo.setChecked(false);
        }

        if (examesSolicitadosResultados.getGlicemiaJejum() == 1) {
            esCbGlicemiaJejum.setChecked(true);
        } else {
            esCbGlicemiaJejum.setChecked(false);
        }

        if (examesSolicitadosResultados.getToleranciaGlicose() == 1) {
            esCbToleranciaGlicose.setChecked(true);
        } else {
            esCbToleranciaGlicose.setChecked(false);
        }

        if (examesSolicitadosResultados.getSifilis() == 1) {
            esCbSifilis.setChecked(true);
        } else {
            esCbSifilis.setChecked(false);
        }

        if (examesSolicitadosResultados.getVdrl() == 1) {
            esCbVdrl.setChecked(true);
        } else {
            esCbVdrl.setChecked(false);
        }

        if (examesSolicitadosResultados.getHiv() == 1) {
            esCbHiv.setChecked(true);
        } else {
            esCbHiv.setChecked(false);
        }

        if (examesSolicitadosResultados.getHepatiteBC() == 1) {
            esCbHepatiteBC.setChecked(true);
        } else {
            esCbHepatiteBC.setChecked(false);
        }

        if (examesSolicitadosResultados.getHbsag() == 1) {
            esCbHbsag.setChecked(true);
        } else {
            esCbHbsag.setChecked(false);
        }

        if (examesSolicitadosResultados.getToxoplasmose() == 1) {
            esCbToxoplasmose.setChecked(true);
        } else {
            esCbToxoplasmose.setChecked(false);
        }

        if (examesSolicitadosResultados.getHemoglobina() == 1) {
            esCbHemoglobina.setChecked(true);
        } else {
            esCbHemoglobina.setChecked(false);
        }

        if (examesSolicitadosResultados.getUrinaEas() == 1) {
            esCbUrinaEas.setChecked(true);
        } else {
            esCbUrinaEas.setChecked(false);
        }

        if (examesSolicitadosResultados.getUrinaCultura() == 1) {
            esCbUrinaCultura.setChecked(true);
        } else {
            esCbUrinaCultura.setChecked(false);
        }

        if (examesSolicitadosResultados.getCoombs() == 1) {
            esCbCoombs.setChecked(true);
        } else {
            esCbCoombs.setChecked(false);
        }

        this.examesSolicitadosResultados = examesSolicitadosResultados;
    }

    public ExamesSolicitadosResultados pegaExamesSolicitadosResultados() {

        ExamesSolicitadosResultados examesSolicitadosResultados = new ExamesSolicitadosResultados();

        if (esCbAbo.isChecked()) {
            examesSolicitadosResultados.setAboRh(1);
        } else {
            examesSolicitadosResultados.setAboRh(0);
        }

        if (esCbGlicemiaJejum.isChecked()) {
            examesSolicitadosResultados.setGlicemiaJejum(1);
        } else {
            examesSolicitadosResultados.setGlicemiaJejum(0);
        }

        if (esCbToleranciaGlicose.isChecked()) {
            examesSolicitadosResultados.setToleranciaGlicose(1);
        } else {
            examesSolicitadosResultados.setToleranciaGlicose(0);
        }

        if (esCbSifilis.isChecked()) {
            examesSolicitadosResultados.setSifilis(1);
        } else {
            examesSolicitadosResultados.setSifilis(0);
        }

        if (esCbVdrl.isChecked()) {
            examesSolicitadosResultados.setVdrl(1);
        } else {
            examesSolicitadosResultados.setVdrl(0);
        }

        if (esCbHiv.isChecked()) {
            examesSolicitadosResultados.setHiv(1);
        } else {
            examesSolicitadosResultados.setHiv(0);
        }

        if (esCbHepatiteBC.isChecked()) {
            examesSolicitadosResultados.setHepatiteBC(1);
        } else {
            examesSolicitadosResultados.setHepatiteBC(0);
        }

        if (esCbHbsag.isChecked()) {
            examesSolicitadosResultados.setHbsag(1);
        } else {
            examesSolicitadosResultados.setHbsag(0);
        }

        if (esCbToxoplasmose.isChecked()) {
            examesSolicitadosResultados.setToxoplasmose(1);
        } else {
            examesSolicitadosResultados.setToxoplasmose(0);
        }

        if (esCbHemoglobina.isChecked()) {
            examesSolicitadosResultados.setHemoglobina(1);
        } else {
            examesSolicitadosResultados.setHemoglobina(0);
        }

        if (esCbUrinaEas.isChecked()) {
            examesSolicitadosResultados.setUrinaEas(1);
        } else {
            examesSolicitadosResultados.setUrinaEas(0);
        }

        if (esCbUrinaCultura.isChecked()) {
            examesSolicitadosResultados.setUrinaCultura(1);
        } else {
            examesSolicitadosResultados.setUrinaCultura(0);
        }

        if (esCbCoombs.isChecked()) {
            examesSolicitadosResultados.setCoombs(1);
        } else {
            examesSolicitadosResultados.setCoombs(0);
        }

        examesSolicitadosResultados.setId(this.examesSolicitadosResultados.getId());

        return examesSolicitadosResultados;
    }


}
