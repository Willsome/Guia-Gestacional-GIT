package com.scriptpoin.guiagestacional.caderneta.ultrassonografia;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

/**
 * Created by Willi on 05-Sep-17.
 */

public class UltrassonografiaHelper {

    // FORMULÁRIO
    private TextView uTvDpData;
    private TextView uTvDpIgDum;
    private TextView uTvDpIgUsg;
    private EditText uEdPesoFetal;
    private Spinner uSpPlacenta;
    private EditText uEdLiquidoAmniotico;

    // CADERNETA
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
            uTvDpData = (TextView) activity.findViewById(R.id.uTvDpData);
            uTvDpIgDum = (TextView) activity.findViewById(R.id.uTvDpIgDum);
            uTvDpIgUsg = (TextView) activity.findViewById(R.id.uTvDpIgUsg);
            uEdPesoFetal = (EditText) activity.findViewById(R.id.uEdPesoFetal);
            uSpPlacenta = (Spinner) activity.findViewById(R.id.uSpPlacenta);
            uEdLiquidoAmniotico = (EditText) activity.findViewById(R.id.uEdLiquidoAmniotico);
        } else if (i == 2) {
            uTvData = (TextView) view.findViewById(R.id.uTvData);
            uTvIgDum = (TextView) view.findViewById(R.id.uTvIgDum);
            uTvIgUsg = (TextView) view.findViewById(R.id.uTvIgUsg);
            uTvPesoFetal = (TextView) view.findViewById(R.id.uTvPesoFetal);
            uTvPlacenta = (TextView) view.findViewById(R.id.uTvPlacenta);
            uTvLiquidoAmniotico = (TextView) view.findViewById(R.id.uTvLiquidoAmniotico);
        }

        this.ultrassonografia = new Ultrassonografia();
    }

    public Ultrassonografia pegaUltrassonografia() throws Exception {

        Ultrassonografia ultrassonografia;

        if (uTvDpData.getText().toString().equals("[ADICIONAR]")
                || uTvDpIgDum.getText().toString().equals("[ADICIONAR]")
                || uTvDpIgUsg.getText().toString().equals("[ADICIONAR]")
                || uEdPesoFetal.getText().toString().equals("")
                || uSpPlacenta.getSelectedItem().toString().equals("")
                || uEdLiquidoAmniotico.getText().toString().equals("")) {

            throw new Exception("Campo vazio.");

        } else {

            ultrassonografia = new Ultrassonografia();
            ultrassonografia.setData(uTvDpData.getText().toString());
            ultrassonografia.setIgDum(uTvDpIgDum.getText().toString());
            ultrassonografia.setIgUsg(uTvDpIgUsg.getText().toString());
            ultrassonografia.setPesoFetal(Integer.parseInt(uEdPesoFetal.getText().toString()));
            ultrassonografia.setPlacenta(uSpPlacenta.getSelectedItem().toString());
            ultrassonografia.setLiquidoAmniotico(Double.parseDouble(uEdLiquidoAmniotico.getText().toString()));

            ultrassonografia.setId(this.ultrassonografia.getId());
        }

        return ultrassonografia;
    }

    public void preencheFormularioUltrassonografia(Ultrassonografia ultrassonografia, int posicao) {

        uTvDpData.setText(ultrassonografia.getData());
        uTvDpIgDum.setText(ultrassonografia.getIgDum());
        uTvDpIgUsg.setText(ultrassonografia.getIgUsg());
        uEdPesoFetal.setText(String.valueOf(ultrassonografia.getPesoFetal()));

        uSpPlacenta.setSelection(posicao);

        uEdLiquidoAmniotico.setText(String.valueOf(ultrassonografia.getLiquidoAmniotico()));

        this.ultrassonografia = ultrassonografia;
    }

    public void preencheUltrassonografia(Ultrassonografia ultrassonografia) {

        uTvData.setText(ultrassonografia.getData());
        uTvIgDum.setText(ultrassonografia.getIgDum());
        uTvIgUsg.setText(ultrassonografia.getIgUsg());
        uTvPesoFetal.setText(ultrassonografia.getPesoFetal() + " g");
        uTvPlacenta.setText(ultrassonografia.getPlacenta());
        uTvLiquidoAmniotico.setText(ultrassonografia.getLiquidoAmniotico() + " ml");
    }
}
