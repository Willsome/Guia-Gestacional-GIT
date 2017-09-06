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

    private TextView uTvDpData;
    private TextView uTvDpIgDum;
    private TextView uTvDpIgUsg;
    private EditText uEdPesoFetal;
    private Spinner uSpPlacenta;
    private EditText uEdLiquidoAmniotico;

    private TextView uTvData;
    private TextView uTvIgDum;
    private TextView uTvIgUsg;
    private TextView uTvPesoFetal;
    private TextView uTvPlacenta;
    private TextView uTvLiquidoAmniotico;

    public UltrassonografiaHelper(Activity activity, int i, View view) {

        if(i == 1) {
            uTvDpData = (TextView) activity.findViewById(R.id.uTvDpData);
            uTvDpIgDum = (TextView) activity.findViewById(R.id.uTvDpIgDum);
            uTvDpIgUsg = (TextView) activity.findViewById(R.id.uTvDpIgUsg);
            uEdPesoFetal = (EditText) activity.findViewById(R.id.uEdPesoFetal);
            uSpPlacenta = (Spinner) activity.findViewById(R.id.uSpPlacenta);
            uEdLiquidoAmniotico = (EditText) activity.findViewById(R.id.uEdLiquidoAmniotico);
        } else if(i == 2) {
            uTvData = (TextView) view.findViewById(R.id.uTvData);
            uTvIgDum = (TextView) view.findViewById(R.id.uTvIgDum);
            uTvIgUsg = (TextView) view.findViewById(R.id.uTvIgUsg);
            uTvPesoFetal = (TextView) view.findViewById(R.id.uTvPesoFetal);
            uTvPlacenta = (TextView) view.findViewById(R.id.uTvPlacenta);
            uTvLiquidoAmniotico = (TextView) view.findViewById(R.id.uTvLiquidoAmniotico);
        }
    }

    public Ultrassonografia pegaUltrassonografia() {

        Ultrassonografia ultrassonografia = new Ultrassonografia();
        ultrassonografia.setData(uTvDpData.getText().toString());
        ultrassonografia.setIgDum(uTvDpIgDum.getText().toString());
        ultrassonografia.setIgUsg(uTvDpIgUsg.getText().toString());
        ultrassonografia.setPesoFetal(Integer.parseInt(uEdPesoFetal.getText().toString()));
        ultrassonografia.setPlacenta(uSpPlacenta.getSelectedItem().toString());
        ultrassonografia.setLiquidoAmniotico(Float.parseFloat(uEdLiquidoAmniotico.getText().toString()));

        return ultrassonografia;
    }

    public void preencheFormularioUltrassonografia(Ultrassonografia ultrassonografia, int posicao) {

        uTvDpData.setText(ultrassonografia.getData());
        uTvDpIgDum.setText(ultrassonografia.getIgDum());
        uTvDpIgUsg.setText(ultrassonografia.getIgUsg());
        uEdPesoFetal.setText(String.valueOf(ultrassonografia.getPesoFetal()));

        uSpPlacenta.setSelection(posicao);

        uEdLiquidoAmniotico.setText(String.valueOf(ultrassonografia.getLiquidoAmniotico()));
    }

    public void preencheUltrassonografia(Ultrassonografia ultrassonografia) {

        uTvData.setText(ultrassonografia.getData());
        uTvIgDum.setText(ultrassonografia.getIgDum());
        uTvIgUsg.setText(ultrassonografia.getIgUsg());
        uTvPesoFetal.setText(String.valueOf(ultrassonografia.getPesoFetal()));
        uTvPlacenta.setText(ultrassonografia.getPlacenta());
        uTvLiquidoAmniotico.setText(String.valueOf(ultrassonografia.getLiquidoAmniotico()));
    }
}
