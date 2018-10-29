package com.scriptpoin.gestacaosaudavel.caderneta.ultrassonografia;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Willi on 05-Sep-17.
 */

public class UltrassonografiaHelper {

    // FORMULÁRIO
    private EditText uEtConsultaSolicitacao;
    private EditText uEtConsultaResultado;
    private TextView uTvDpData;
    private EditText uEtIgDumSemanas;
    private EditText uEtIgDumDias;
    private EditText uEtIgUsgSemanas;
    private EditText uEtIgUsgDias;
    private TextView uTvXIgDum;
    private TextView uTvXIgUsg;
    private EditText uEdPesoFetal;
    private Spinner uSpPlacenta;
    private EditText uEdLiquidoAmniotico;
    private CheckBox uCbSolicitacao;
    private CheckBox uCbResultado;

    // CADERNETA
    private TextView uEtTvConsultaResultado;
    private TextView uTvTvDpData;
    private TextView uTvTvDpIgDum;
    private TextView uTvTvDpIgUsg;
    private TextView uEdTvPesoFetal;
    private TextView uSpTvPlacenta;
    private TextView uEdTvLiquidoAmniotico;


    // CADERNETA
    private TextView uTvConsultaSolicitacao;
    private TextView uTvConsultaResultado;
    private TextView uTvData;
    private TextView uTvIgDum;
    private TextView uTvIgUsg;
    private TextView uTvPesoFetal;
    private TextView uTvPlacenta;
    private TextView uTvLiquidoAmniotico;

    // OUTROS
    private Ultrassonografia ultrassonografia;


    public UltrassonografiaHelper(Activity activity, int i, View view) {

        if (i == 1) {
            uEtConsultaSolicitacao = activity.findViewById(R.id.uEtConsultaSolicitacao);
            uCbSolicitacao = activity.findViewById(R.id.uCbSolicitacao);
            uCbResultado = activity.findViewById(R.id.uCbResultado);
            uEtConsultaResultado = activity.findViewById(R.id.uEtConsultaResultado);
            uTvDpData = activity.findViewById(R.id.uTvDpData);
            uEtIgDumSemanas = activity.findViewById(R.id.uEtIgDumSemanas);
            uEtIgDumDias = activity.findViewById(R.id.uEtIgDumDias);
            uEtIgUsgSemanas = activity.findViewById(R.id.uEtIgUsgSemanas);
            uEtIgUsgDias = activity.findViewById(R.id.uEtIgUsgDias);
            uTvXIgDum = activity.findViewById(R.id.uTvXIgDum);
            uTvXIgUsg = activity.findViewById(R.id.uTvXIgUsg);
            uEdPesoFetal = activity.findViewById(R.id.uEdPesoFetal);
            uSpPlacenta = activity.findViewById(R.id.uSpPlacenta);
            uEdLiquidoAmniotico = activity.findViewById(R.id.uEdLiquidoAmniotico);
            uEtTvConsultaResultado = activity.findViewById(R.id.uEtTvConsultaResultado);
            uTvTvDpData = activity.findViewById(R.id.uTvTvDpData);
            uTvTvDpIgDum = activity.findViewById(R.id.uTvTvDpIgDum);
            uTvTvDpIgUsg = activity.findViewById(R.id.uTvTvDpIgUsg);
            uEdTvPesoFetal = activity.findViewById(R.id.uEdTvPesoFetal);
            uSpTvPlacenta = activity.findViewById(R.id.uSpTvPlacenta);
            uEdTvLiquidoAmniotico = activity.findViewById(R.id.uEdTvLiquidoAmniotico);

        } else if (i == 2) {
            uTvConsultaSolicitacao = view.findViewById(R.id.uTvConsultaSolicitacao);
            uTvConsultaResultado = view.findViewById(R.id.uTvConsultaResultado);
            uTvData = view.findViewById(R.id.uTvData);
            uTvIgDum = view.findViewById(R.id.uTvIgDum);
            uTvIgUsg = view.findViewById(R.id.uTvIgUsg);
            uTvPesoFetal = view.findViewById(R.id.uTvPesoFetal);
            uTvPlacenta = view.findViewById(R.id.uTvPlacenta);
            uTvLiquidoAmniotico = view.findViewById(R.id.uTvLiquidoAmniotico);
        }

        this.ultrassonografia = new Ultrassonografia();
    }

    public Ultrassonografia pegaUltrassonografia(Calendar data) throws Exception {

        Ultrassonografia ultrassonografia;

        if (uCbSolicitacao.isChecked() && !uEtConsultaSolicitacao.getText().toString().equals("")) {
            ultrassonografia = new Ultrassonografia();
            ultrassonografia.setSolicitacao(1L);
            ultrassonografia.setNumeroConsultaSolicitacao(Long.parseLong(uEtConsultaSolicitacao.getText().toString()));

            ultrassonografia.setId(this.ultrassonografia.getId());

        } else if (!uCbSolicitacao.isChecked() && !uCbResultado.isChecked()) {
            throw new Exception("Escolha uma das opções acima...");

        } else if (uCbSolicitacao.isChecked() && uEtConsultaSolicitacao.getText().toString().equals("")) {
            throw new Exception("Existem campos não preenchidos...");

        } else if (uCbResultado.isChecked() && uEtConsultaSolicitacao.getText().toString().equals("")
                || uEtConsultaResultado.getText().toString().equals("")
                || uTvDpData.getText().toString().equals("[ADICIONAR]")
                || uEtIgDumSemanas.getText().toString().equals("")
                || uEtIgDumDias.getText().toString().equals("")
                || uEtIgUsgSemanas.getText().toString().equals("")
                || uEtIgUsgDias.getText().toString().equals("")
                || uEdPesoFetal.getText().toString().equals("")
                || uSpPlacenta.getSelectedItem().toString().equals("")
                || uEdLiquidoAmniotico.getText().toString().equals("")) {

            throw new Exception("Existem campos não preenchidos...");

        } else if (Integer.valueOf(uEtIgDumSemanas.getText().toString()) <= 0
                && Integer.valueOf(uEtIgDumDias.getText().toString()) <= 0
                || Integer.valueOf(uEtIgUsgSemanas.getText().toString()) <= 0
                && Integer.valueOf(uEtIgUsgDias.getText().toString()) <= 0) {

            throw new Exception("Existem valores zerados ou negativos...");

        } else {

            ultrassonografia = new Ultrassonografia();
            ultrassonografia.setSolicitacao(0L);
            ultrassonografia.setNumeroConsultaSolicitacao(Long.parseLong(uEtConsultaSolicitacao.getText().toString()));
            ultrassonografia.setNumeroConsultaResultado(Long.parseLong(uEtConsultaResultado.getText().toString()));
            ultrassonografia.setData(data);
            ultrassonografia.setIgDumSemanas(Integer.parseInt(uEtIgDumSemanas.getText().toString()));
            ultrassonografia.setIgDumDias(Integer.parseInt(uEtIgDumDias.getText().toString()));
            ultrassonografia.setIgUsgSemanas(Integer.parseInt(uEtIgUsgSemanas.getText().toString()));
            ultrassonografia.setIgUsgDias(Integer.parseInt(uEtIgUsgDias.getText().toString()));
            ultrassonografia.setPesoFetal(Integer.parseInt(uEdPesoFetal.getText().toString()));
            ultrassonografia.setPlacenta(uSpPlacenta.getSelectedItem().toString());
            ultrassonografia.setLiquidoAmniotico(Double.parseDouble(uEdLiquidoAmniotico.getText().toString()));

            ultrassonografia.setId(this.ultrassonografia.getId());
        }

        return ultrassonografia;
    }

    public void preencheFormularioUltrassonografia(Ultrassonografia ultrassonografia, int posicao) {

        uEtConsultaSolicitacao.setEnabled(false);
        uEtConsultaSolicitacao.setTextColor(Color.rgb(183, 68, 139));

        if (ultrassonografia.getSolicitacao() == 1) {
            uEtConsultaSolicitacao.setText(String.valueOf(ultrassonografia.getNumeroConsultaSolicitacao()));

        } else {

            uEtConsultaSolicitacao.setText(String.valueOf(ultrassonografia.getNumeroConsultaSolicitacao()));
            uEtConsultaResultado.setText(String.valueOf(ultrassonografia.getNumeroConsultaResultado()));

            SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

            uTvDpData.setText(formatadorData.format(ultrassonografia.getData().getTime()));

            uEtIgDumSemanas.setText(String.valueOf(ultrassonografia.getIgDumSemanas()));
            uEtIgDumDias.setText(String.valueOf(ultrassonografia.getIgDumDias()));
            uEtIgUsgSemanas.setText(String.valueOf(ultrassonografia.getIgUsgSemanas()));
            uEtIgUsgDias.setText(String.valueOf(ultrassonografia.getIgUsgDias()));

            uEdPesoFetal.setText(String.valueOf(ultrassonografia.getPesoFetal()));

            uSpPlacenta.setSelection(posicao);

            uEdLiquidoAmniotico.setText(String.valueOf(ultrassonografia.getLiquidoAmniotico()));
        }

        this.ultrassonografia = ultrassonografia;
    }

    public void preencheUltrassonografiaResultado(Ultrassonografia ultrassonografia) {


        uTvConsultaSolicitacao.setText(ultrassonografia.getNumeroConsultaSolicitacao() + "ª");
        uTvConsultaResultado.setText(ultrassonografia.getNumeroConsultaResultado() + "ª");

        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

        uTvData.setText(formatadorData.format(ultrassonografia.getData().getTime()));

        String igDum = "";
        if (ultrassonografia.getIgDumSemanas() > 0 && ultrassonografia.getIgDumDias() > 0) {
            igDum = ultrassonografia.getIgDumSemanas() + " semanas";
            igDum = igDum + " e " + ultrassonografia.getIgDumDias() + " dias";
        } else if (ultrassonografia.getIgDumSemanas() > 0) {
            igDum = ultrassonografia.getIgDumSemanas() + " semanas";
        } else if (ultrassonografia.getIgDumDias() > 0) {
            igDum = ultrassonografia.getIgDumDias() + " dias";
        }
        uTvIgDum.setText(igDum);

        String igUsg = "";
        if (ultrassonografia.getIgUsgSemanas() > 0 && ultrassonografia.getIgUsgDias() > 0) {
            igUsg = ultrassonografia.getIgUsgSemanas() + " semanas";
            igUsg = igUsg + " e " + ultrassonografia.getIgUsgDias() + " dias";
        } else if (ultrassonografia.getIgUsgSemanas() > 0) {
            igUsg = ultrassonografia.getIgUsgSemanas() + " semanas";
        } else if (ultrassonografia.getIgUsgDias() > 0) {
            igUsg = ultrassonografia.getIgUsgDias() + " dias";
        }
        uTvIgUsg.setText(igUsg);

        uTvPesoFetal.setText(ultrassonografia.getPesoFetal() + " g");
        uTvPlacenta.setText(ultrassonografia.getPlacenta());
        uTvLiquidoAmniotico.setText(ultrassonografia.getLiquidoAmniotico() + " ml");
    }

    public void preencheUltrassonografiaSolicitacao(Ultrassonografia ultrassonografia) {
        uTvConsultaSolicitacao.setText(ultrassonografia.getNumeroConsultaSolicitacao() + "ª");
    }

    public void marcaResultado(boolean ativo) {
        if (ativo) {
            uCbSolicitacao.setChecked(false);
            uEtTvConsultaResultado.setVisibility(View.VISIBLE);
            uTvTvDpData.setVisibility(View.VISIBLE);
            uTvTvDpIgDum.setVisibility(View.VISIBLE);
            uTvTvDpIgUsg.setVisibility(View.VISIBLE);
            uEdTvPesoFetal.setVisibility(View.VISIBLE);
            uSpTvPlacenta.setVisibility(View.VISIBLE);
            uEdTvLiquidoAmniotico.setVisibility(View.VISIBLE);
            uEtConsultaResultado.setVisibility(View.VISIBLE);
            uTvDpData.setVisibility(View.VISIBLE);
            uEtIgDumSemanas.setVisibility(View.VISIBLE);
            uEtIgDumDias.setVisibility(View.VISIBLE);
            uEtIgUsgSemanas.setVisibility(View.VISIBLE);
            uEtIgUsgDias.setVisibility(View.VISIBLE);
            uTvXIgDum.setVisibility(View.VISIBLE);
            uTvXIgUsg.setVisibility(View.VISIBLE);
            uEdPesoFetal.setVisibility(View.VISIBLE);
            uSpPlacenta.setVisibility(View.VISIBLE);
            uEdLiquidoAmniotico.setVisibility(View.VISIBLE);

        } else {
            uEtTvConsultaResultado.setVisibility(View.GONE);
            uTvTvDpData.setVisibility(View.GONE);
            uTvTvDpIgDum.setVisibility(View.GONE);
            uTvTvDpIgUsg.setVisibility(View.GONE);
            uEdTvPesoFetal.setVisibility(View.GONE);
            uSpTvPlacenta.setVisibility(View.GONE);
            uEdTvLiquidoAmniotico.setVisibility(View.GONE);
            uEtConsultaResultado.setVisibility(View.GONE);
            uTvDpData.setVisibility(View.GONE);
            uEtIgDumSemanas.setVisibility(View.GONE);
            uEtIgDumDias.setVisibility(View.GONE);
            uEtIgUsgSemanas.setVisibility(View.GONE);
            uEtIgUsgDias.setVisibility(View.GONE);
            uTvXIgDum.setVisibility(View.GONE);
            uTvXIgUsg.setVisibility(View.GONE);
            uEdPesoFetal.setVisibility(View.GONE);
            uSpPlacenta.setVisibility(View.GONE);
            uEdLiquidoAmniotico.setVisibility(View.GONE);
        }
    }

}