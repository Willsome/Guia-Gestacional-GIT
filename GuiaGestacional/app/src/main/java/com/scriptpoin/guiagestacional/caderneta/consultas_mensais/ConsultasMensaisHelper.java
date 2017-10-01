package com.scriptpoin.guiagestacional.caderneta.consultas_mensais;


import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

public class ConsultasMensaisHelper {

    // FORMULÁRIO
    EditText cmEtNumeroConsulta;
    TextView cmTvFormDataConsulta;
    EditText cmEtQueixa;
    EditText cmEtIg;
    EditText cmEtPeso;
    EditText cmEtImc;
    EditText cmEtEdema;
    EditText cmEtPaI, cmEtPaII;
    EditText cmEtAlturaUterina;
    Spinner cmSpPosicaoFetal;
    EditText cmEtBcf;
    EditText cmEtMovFetal;
    EditText cmEtNomeDoProfissional;

    // CADERNETA
    TextView cmTvNumeroConsulta;
    TextView cmTvDataConsulta;
    TextView cmTvQueixa;
    TextView cmTvIg;
    TextView cmTvPeso;
    TextView cmTvImc;
    TextView cmTvEdema;
    TextView cmTvPa;
    TextView cmTvAlturaUterina;
    TextView cmTvPosicaoFetal;
    TextView cmTvBcf;
    TextView cmTvMovFetal;
    TextView cmTvNomeDoProfissional;

    // OUTROS
    private ConsultasMensais consultasMensais;

    public ConsultasMensaisHelper(Activity activity, int i, View view) {

        if (i == 1) {
            cmEtNumeroConsulta = (EditText) activity.findViewById(R.id.cmEtNumeroConsulta);
            cmTvFormDataConsulta = (TextView) activity.findViewById(R.id.cmTvFormDataConsulta);
            cmEtQueixa = (EditText) activity.findViewById(R.id.cmEtQueixa);
            cmEtIg = (EditText) activity.findViewById(R.id.cmEtIg);
            cmEtPeso = (EditText) activity.findViewById(R.id.cmEtPeso);
            cmEtImc = (EditText) activity.findViewById(R.id.cmEtImc);
            cmEtEdema = (EditText) activity.findViewById(R.id.cmEtEdema);
            cmEtPaI = (EditText) activity.findViewById(R.id.cmEtPaI);
            cmEtPaII = (EditText) activity.findViewById(R.id.cmEtPaII);
            cmEtAlturaUterina = (EditText) activity.findViewById(R.id.cmEtAlturaUterina);
            cmSpPosicaoFetal = (Spinner) activity.findViewById(R.id.cmSpPosicaoFetal);
            cmEtBcf = (EditText) activity.findViewById(R.id.cmEtBcf);
            cmEtMovFetal = (EditText) activity.findViewById(R.id.cmEtMovFetal);
            cmEtNomeDoProfissional = (EditText) activity.findViewById(R.id.cmEtNomeDoProfissional);

        } else if (i == 2) {
            cmTvNumeroConsulta = (TextView) view.findViewById(R.id.cmTvNumeroConsulta);
            cmTvDataConsulta = (TextView) view.findViewById(R.id.cmTvDataConsulta);
            cmTvQueixa = (TextView) view.findViewById(R.id.cmTvQueixa);
            cmTvIg = (TextView) view.findViewById(R.id.cmTvIg);
            cmTvPeso = (TextView) view.findViewById(R.id.cmTvPeso);
            cmTvImc = (TextView) view.findViewById(R.id.cmTvImc);
            cmTvEdema = (TextView) view.findViewById(R.id.cmTvEdema);
            cmTvPa = (TextView) view.findViewById(R.id.cmTvPa);
            cmTvAlturaUterina = (TextView) view.findViewById(R.id.cmTvAlturaUterina);
            cmTvPosicaoFetal = (TextView) view.findViewById(R.id.cmTvPosicaoFetal);
            cmTvBcf = (TextView) view.findViewById(R.id.cmTvBcf);
            cmTvMovFetal = (TextView) view.findViewById(R.id.cmTvMovFetal);
            cmTvNomeDoProfissional = (TextView) view.findViewById(R.id.cmTvNomeDoProfissional);
        }

        this.consultasMensais = new ConsultasMensais();
    }


    public ConsultasMensais pegaConsultasMensais() throws Exception {

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
                || cmEtNomeDoProfissional.getText().toString().equals("")) {

            throw new Exception("Campo vazio.");

        } else {

            consultasMensais = new ConsultasMensais();
            consultasMensais.setNumeroConsulta(Integer.parseInt(cmEtNumeroConsulta.getText().toString()));
            consultasMensais.setDataConsulta(cmTvFormDataConsulta.getText().toString());
            consultasMensais.setQueixa(cmEtQueixa.getText().toString());
            consultasMensais.setIg(Double.parseDouble(cmEtIg.getText().toString()));
            consultasMensais.setPeso(Double.parseDouble(cmEtPeso.getText().toString()));
            consultasMensais.setImc(Double.parseDouble(cmEtImc.getText().toString()));
            consultasMensais.setEdema(cmEtEdema.getText().toString());
            consultasMensais.setPaI(Double.parseDouble(cmEtPaI.getText().toString()));
            consultasMensais.setPaII(Double.parseDouble(cmEtPaII.getText().toString()));
            consultasMensais.setAlturaUterina(Integer.parseInt(cmEtAlturaUterina.getText().toString()));
            consultasMensais.setPosicaoFetal(cmSpPosicaoFetal.getSelectedItem().toString());
            consultasMensais.setBcf(Integer.parseInt(cmEtBcf.getText().toString()));
            consultasMensais.setMovFetal(cmEtMovFetal.getText().toString());
            consultasMensais.setNomeDoProfissional(cmEtNomeDoProfissional.getText().toString());

            consultasMensais.setId(this.consultasMensais.getId());
        }


        return consultasMensais;
    }

    public void preencheFormularioConsultasMensais(ConsultasMensais consultasMensais, int posicao) {

        cmEtNumeroConsulta.setText(String.valueOf(consultasMensais.getNumeroConsulta()));
        cmTvFormDataConsulta.setText(consultasMensais.getDataConsulta());
        cmEtQueixa.setText(consultasMensais.getQueixa());
        cmEtIg.setText(String.valueOf(consultasMensais.getIg()));
        cmEtPeso.setText(String.valueOf(consultasMensais.getPeso()));
        cmEtImc.setText(String.valueOf(consultasMensais.getImc()));
        cmEtEdema.setText(consultasMensais.getEdema());
        cmEtPaI.setText(String.valueOf(consultasMensais.getPaI()));
        cmEtPaII.setText(String.valueOf(consultasMensais.getPaII()));
        cmEtAlturaUterina.setText(String.valueOf(consultasMensais.getAlturaUterina()));
        cmSpPosicaoFetal.setSelection(posicao);
        cmEtBcf.setText(String.valueOf(consultasMensais.getBcf()));
        cmEtMovFetal.setText(consultasMensais.getMovFetal());
        cmEtNomeDoProfissional.setText(consultasMensais.getNomeDoProfissional());

        this.consultasMensais = consultasMensais;
    }

    public void preencheConsultasMensais(ConsultasMensais consultasMensais) {

        cmTvNumeroConsulta.setText(consultasMensais.getNumeroConsulta() + "ª");
        cmTvDataConsulta.setText(consultasMensais.getDataConsulta());
        cmTvQueixa.setText(consultasMensais.getQueixa());
        cmTvIg.setText(consultasMensais.getIg() + " semanas");
        cmTvPeso.setText(consultasMensais.getPeso() + " kg");
        cmTvImc.setText(String.valueOf(consultasMensais.getImc()));
        cmTvEdema.setText(consultasMensais.getEdema());
        cmTvPa.setText(consultasMensais.getPaI() + " x " + consultasMensais.getPaII());
        cmTvAlturaUterina.setText(consultasMensais.getAlturaUterina() + " cm");
        cmTvPosicaoFetal.setText(consultasMensais.getPosicaoFetal());
        cmTvBcf.setText(consultasMensais.getBcf() + " bpm");
        cmTvMovFetal.setText(consultasMensais.getMovFetal());
        cmTvNomeDoProfissional.setText(consultasMensais.getNomeDoProfissional());
    }

}

