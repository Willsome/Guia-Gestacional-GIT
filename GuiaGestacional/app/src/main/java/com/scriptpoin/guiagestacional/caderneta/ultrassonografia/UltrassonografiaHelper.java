package com.scriptpoin.guiagestacional.caderneta.ultrassonografia;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

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
    private TextView uTvDpIgDum;
    private TextView uTvDpIgUsg;
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
            uEtConsultaSolicitacao = (EditText) activity.findViewById(R.id.uEtConsultaSolicitacao);
            uCbSolicitacao = (CheckBox) activity.findViewById(R.id.uCbSolicitacao);
            uCbResultado = (CheckBox) activity.findViewById(R.id.uCbResultado);
            uEtConsultaResultado = (EditText) activity.findViewById(R.id.uEtConsultaResultado);
            uTvDpData = (TextView) activity.findViewById(R.id.uTvDpData);
            uTvDpIgDum = (TextView) activity.findViewById(R.id.uTvDpIgDum);
            uTvDpIgUsg = (TextView) activity.findViewById(R.id.uTvDpIgUsg);
            uEdPesoFetal = (EditText) activity.findViewById(R.id.uEdPesoFetal);
            uSpPlacenta = (Spinner) activity.findViewById(R.id.uSpPlacenta);
            uEdLiquidoAmniotico = (EditText) activity.findViewById(R.id.uEdLiquidoAmniotico);

            uEtTvConsultaResultado = (TextView) activity.findViewById(R.id.uEtTvConsultaResultado);
            uTvTvDpData = (TextView) activity.findViewById(R.id.uTvTvDpData);
            uTvTvDpIgDum = (TextView) activity.findViewById(R.id.uTvTvDpIgDum);
            uTvTvDpIgUsg = (TextView) activity.findViewById(R.id.uTvTvDpIgUsg);
            uEdTvPesoFetal = (TextView) activity.findViewById(R.id.uEdTvPesoFetal);
            uSpTvPlacenta = (TextView) activity.findViewById(R.id.uSpTvPlacenta);
            uEdTvLiquidoAmniotico = (TextView) activity.findViewById(R.id.uEdTvLiquidoAmniotico);

        } else if (i == 2) {
            uTvConsultaSolicitacao = (TextView) view.findViewById(R.id.uTvConsultaSolicitacao);
            uTvConsultaResultado = (TextView) view.findViewById(R.id.uTvConsultaResultado);
            uTvData = (TextView) view.findViewById(R.id.uTvData);
            uTvIgDum = (TextView) view.findViewById(R.id.uTvIgDum);
            uTvIgUsg = (TextView) view.findViewById(R.id.uTvIgUsg);
            uTvPesoFetal = (TextView) view.findViewById(R.id.uTvPesoFetal);
            uTvPlacenta = (TextView) view.findViewById(R.id.uTvPlacenta);
            uTvLiquidoAmniotico = (TextView) view.findViewById(R.id.uTvLiquidoAmniotico);
        }

        this.ultrassonografia = new Ultrassonografia();
    }

    public Ultrassonografia pegaUltrassonografia(Calendar data, Calendar dataIgDum, Calendar dataIgUsg) throws Exception {

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
                || uTvDpIgDum.getText().toString().equals("[ADICIONAR]")
                || uTvDpIgUsg.getText().toString().equals("[ADICIONAR]")
                || uEdPesoFetal.getText().toString().equals("")
                || uSpPlacenta.getSelectedItem().toString().equals("")
                || uEdLiquidoAmniotico.getText().toString().equals("")) {

            throw new Exception("Existem campos não preenchidos...");

        } else {

            ultrassonografia = new Ultrassonografia();
            ultrassonografia.setSolicitacao(0L);
            ultrassonografia.setNumeroConsultaSolicitacao(Long.parseLong(uEtConsultaSolicitacao.getText().toString()));
            ultrassonografia.setNumeroConsultaResultado(Long.parseLong(uEtConsultaResultado.getText().toString()));
            ultrassonografia.setData(data);
            ultrassonografia.setIgDum(dataIgDum);
            ultrassonografia.setIgUsg(dataIgUsg);
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
            uTvDpIgDum.setText(formatadorData.format(ultrassonografia.getIgDum().getTime()));
            uTvDpIgUsg.setText(formatadorData.format(ultrassonografia.getIgUsg().getTime()));
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
        uTvIgDum.setText(formatadorData.format(ultrassonografia.getIgDum().getTime()));
        uTvIgUsg.setText(formatadorData.format(ultrassonografia.getIgUsg().getTime()));
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
            uTvDpIgDum.setVisibility(View.VISIBLE);
            uTvDpIgUsg.setVisibility(View.VISIBLE);
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
            uTvDpIgDum.setVisibility(View.GONE);
            uTvDpIgUsg.setVisibility(View.GONE);
            uEdPesoFetal.setVisibility(View.GONE);
            uSpPlacenta.setVisibility(View.GONE);
            uEdLiquidoAmniotico.setVisibility(View.GONE);
        }
    }

}