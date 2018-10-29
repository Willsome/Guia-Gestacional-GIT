package com.scriptpoin.gestacaosaudavel.caderneta.consultas_mensais;


import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConsultasMensaisHelper {

    // FORMULÁRIO
    private EditText cmEtNumeroConsulta;
    private TextView cmTvFormDataConsulta;
    private EditText cmEtQueixa;
    private EditText cmEtIg;
    private EditText cmEtPeso;
    private EditText cmEtImc;
    private EditText cmEtEdema;
    private EditText cmEtPaI, cmEtPaII;
    private EditText cmEtAlturaUterina;
    private Spinner cmSpPosicaoFetal;
    private EditText cmEtBcf;
    private EditText cmEtMovFetal;
    private Spinner cmSpTipoProfissional;
    private EditText cmEtNomeDoProfissional;

    // CADERNETA
    private TextView cmTvNumeroConsulta;
    private TextView cmTvDataConsulta;
    private TextView cmTvQueixa;
    private TextView cmTvIg;
    private TextView cmTvPeso;
    private TextView cmTvImc;
    private TextView cmTvEdema;
    private TextView cmTvPa;
    private TextView cmTvAlturaUterina;
    private TextView cmTvPosicaoFetal;
    private TextView cmTvBcf;
    private TextView cmTvMovFetal;
    private TextView cmTvNomeDoProfissional;

    public ConsultasMensaisHelper(Activity activity, int i, View view) {

        if (i == 1) {
            cmEtNumeroConsulta = activity.findViewById(R.id.cmEtNumeroConsulta);
            cmTvFormDataConsulta = activity.findViewById(R.id.cmTvFormDataConsulta);
            cmEtQueixa = activity.findViewById(R.id.cmEtQueixa);
            cmEtIg = activity.findViewById(R.id.cmEtIg);
            cmEtPeso = activity.findViewById(R.id.cmEtPeso);
            cmEtImc = activity.findViewById(R.id.cmEtImc);
            cmEtEdema = activity.findViewById(R.id.cmEtEdema);
            cmEtPaI = activity.findViewById(R.id.cmEtPaI);
            cmEtPaII = activity.findViewById(R.id.cmEtPaII);
            cmEtAlturaUterina = activity.findViewById(R.id.cmEtAlturaUterina);
            cmSpPosicaoFetal = activity.findViewById(R.id.cmSpPosicaoFetal);
            cmEtBcf = activity.findViewById(R.id.cmEtBcf);
            cmEtMovFetal = activity.findViewById(R.id.cmEtMovFetal);
            cmSpTipoProfissional = activity.findViewById(R.id.cmSpTipoProfissional);
            cmEtNomeDoProfissional = activity.findViewById(R.id.cmEtNomeDoProfissional);

        } else if (i == 2) {
            cmTvNumeroConsulta = view.findViewById(R.id.cmTvNumeroConsulta);
            cmTvDataConsulta = view.findViewById(R.id.cmTvDataConsulta);
            cmTvQueixa = view.findViewById(R.id.cmTvQueixa);
            cmTvIg = view.findViewById(R.id.cmTvIg);
            cmTvPeso = view.findViewById(R.id.cmTvPeso);
            cmTvImc = view.findViewById(R.id.cmTvImc);
            cmTvEdema = view.findViewById(R.id.cmTvEdema);
            cmTvPa = view.findViewById(R.id.cmTvPa);
            cmTvAlturaUterina = view.findViewById(R.id.cmTvAlturaUterina);
            cmTvPosicaoFetal = view.findViewById(R.id.cmTvPosicaoFetal);
            cmTvBcf = view.findViewById(R.id.cmTvBcf);
            cmTvMovFetal = view.findViewById(R.id.cmTvMovFetal);
            cmTvNomeDoProfissional = view.findViewById(R.id.cmTvNomeDoProfissional);
        }
    }


    public ConsultasMensais pegaConsultasMensais(Calendar dataConsulta) throws Exception {

        ConsultasMensais consultasMensais;

        if (cmEtNumeroConsulta.getText().toString().equals("")
                || cmTvFormDataConsulta.getText().toString().equals("[ADICIONAR]")
                || cmEtQueixa.getText().toString().equals("")
                || cmEtIg.getText().toString().equals("")
                || cmEtPeso.getText().toString().equals("")
                || cmEtImc.getText().toString().equals("")
                || cmEtEdema.getText().toString().equals("")
                || cmEtPaI.getText().toString().equals("")
                || cmEtPaII.getText().toString().equals("")
                || cmEtAlturaUterina.getText().toString().equals("")
                || cmSpPosicaoFetal.getSelectedItem().toString().equals("")
                || cmEtBcf.getText().toString().equals("")
                || cmEtMovFetal.getText().toString().equals("")
                || cmSpTipoProfissional.getSelectedItem().toString().equals("")
                || cmEtNomeDoProfissional.getText().toString().equals("")) {

            throw new Exception("Campo vazio.");

        } else {

            consultasMensais = new ConsultasMensais();
            consultasMensais.setNumeroConsulta(Long.parseLong(cmEtNumeroConsulta.getText().toString()));
            consultasMensais.setDataConsulta(dataConsulta);
            consultasMensais.setQueixa(cmEtQueixa.getText().toString());
            consultasMensais.setIg(Double.parseDouble(cmEtIg.getText().toString()));
            consultasMensais.setPeso(Double.parseDouble(cmEtPeso.getText().toString()));
            consultasMensais.setImc(Double.parseDouble(cmEtImc.getText().toString()));
            consultasMensais.setEdema(cmEtEdema.getText().toString());
            consultasMensais.setPaI(Double.parseDouble(cmEtPaI.getText().toString()));
            consultasMensais.setPaII(Double.parseDouble(cmEtPaII.getText().toString()));
            consultasMensais.setAlturaUterina((cmEtAlturaUterina.getText().toString()));
            consultasMensais.setPosicaoFetal(cmSpPosicaoFetal.getSelectedItem().toString());
            consultasMensais.setBcf(Integer.parseInt(cmEtBcf.getText().toString()));
            consultasMensais.setMovFetal(cmEtMovFetal.getText().toString());
            consultasMensais.setTipoProfissional(cmSpTipoProfissional.getSelectedItem().toString());
            consultasMensais.setNomeDoProfissional(cmEtNomeDoProfissional.getText().toString());
        }


        return consultasMensais;
    }

    public void preencheFormularioConsultasMensais(ConsultasMensais consultasMensais, int posicaoPosicaoFetal, int posicaoTipoProfissional) {

        cmEtNumeroConsulta.setText(String.valueOf(consultasMensais.getNumeroConsulta()));
        cmEtNumeroConsulta.setEnabled(false);
        cmEtNumeroConsulta.setTextColor(Color.rgb(183, 68, 139));

        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        cmTvFormDataConsulta.setText(formatadorData.format(consultasMensais.getDataConsulta().getTime()));

        cmEtQueixa.setText(consultasMensais.getQueixa());
        cmEtIg.setText(String.valueOf(consultasMensais.getIg()));
        cmEtPeso.setText(String.valueOf(consultasMensais.getPeso()));
        cmEtImc.setText(String.valueOf(consultasMensais.getImc()));
        cmEtEdema.setText(consultasMensais.getEdema());
        cmEtPaI.setText(String.valueOf(consultasMensais.getPaI()));
        cmEtPaII.setText(String.valueOf(consultasMensais.getPaII()));
        cmEtAlturaUterina.setText(consultasMensais.getAlturaUterina());
        cmSpPosicaoFetal.setSelection(posicaoPosicaoFetal);
        cmEtBcf.setText(String.valueOf(consultasMensais.getBcf()));
        cmEtMovFetal.setText(consultasMensais.getMovFetal());
        cmSpTipoProfissional.setSelection(posicaoTipoProfissional);
        cmEtNomeDoProfissional.setText(consultasMensais.getNomeDoProfissional());
    }

    public void preencheConsultasMensais(ConsultasMensais consultasMensais) {

        cmTvNumeroConsulta.setText(consultasMensais.getNumeroConsulta() + "ª");

        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        cmTvDataConsulta.setText(formatadorData.format(consultasMensais.getDataConsulta().getTime()));

        cmTvQueixa.setText(consultasMensais.getQueixa());
        cmTvIg.setText(consultasMensais.getIg() + " semanas");
        cmTvPeso.setText(consultasMensais.getPeso() + " kg");
        cmTvImc.setText(String.valueOf(consultasMensais.getImc()));
        cmTvEdema.setText(consultasMensais.getEdema());
        cmTvPa.setText(consultasMensais.getPaI() + " x " + consultasMensais.getPaII());

        if (consultasMensais.getAlturaUterina().equals("-")) {
            cmTvAlturaUterina.setText(consultasMensais.getAlturaUterina());
        } else {
            cmTvAlturaUterina.setText(consultasMensais.getAlturaUterina() + " cm");
        }

        cmTvPosicaoFetal.setText(consultasMensais.getPosicaoFetal());
        cmTvBcf.setText(consultasMensais.getBcf() + " bpm");
        cmTvMovFetal.setText(consultasMensais.getMovFetal());
        cmTvNomeDoProfissional.setText(consultasMensais.getTipoProfissional() + " " + consultasMensais.getNomeDoProfissional());
    }

}

