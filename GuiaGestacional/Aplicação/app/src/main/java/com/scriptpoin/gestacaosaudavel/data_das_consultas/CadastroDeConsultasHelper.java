package com.scriptpoin.gestacaosaudavel.data_das_consultas;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.scriptpoin.gestacaosaudavel.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Willi on 06-Oct-17.
 */

public class CadastroDeConsultasHelper {

    private EditText dcEtNumeroConsulta;
    private TextView dcTvDataConsulta;
    private TextView dcTvHoraConsulta;
    private Spinner dcSpTipoProfissional;
    private EditText dcEtNomeProfissional;


    private Consulta consulta;

    public CadastroDeConsultasHelper(Activity activity) {
        dcEtNumeroConsulta = (EditText) activity.findViewById(R.id.dcEtNumeroConsulta);
        dcTvDataConsulta = (TextView) activity.findViewById(R.id.dcTvDataConsulta);
        dcTvHoraConsulta = (TextView) activity.findViewById(R.id.dcTvHoraConsulta);
        dcSpTipoProfissional = (Spinner) activity.findViewById(R.id.dcSpTipoProfissional);
        dcEtNomeProfissional = (EditText) activity.findViewById(R.id.dcEtNomeProfissional);

        this.consulta = new Consulta();
    }

    public Consulta pegaConsulta(Calendar dataDaConsulta) throws Exception {

        Consulta consulta;

        if (dcEtNumeroConsulta.getText().toString().equals("")
                || dcTvDataConsulta.getText().toString().equals("[ADICIONAR]")
                || dcTvHoraConsulta.getText().toString().equals("[ADICIONAR]")
                || dcSpTipoProfissional.getSelectedItem().toString().equals("")
                || dcEtNomeProfissional.getText().toString().equals("")) {

            throw new Exception("Campo vazio.");

        } else {

            consulta = new Consulta();
            consulta.setNumeroConsulta(Integer.parseInt(dcEtNumeroConsulta.getText().toString()));
            consulta.setDataHoraConsulta(dataDaConsulta);
            consulta.setTipoProfissional(dcSpTipoProfissional.getSelectedItem().toString());
            consulta.setNomeProfissional(dcEtNomeProfissional.getText().toString());

            consulta.setId(this.consulta.getId());
        }

        return consulta;
    }

    public void preencheFormularioConsulta(Consulta consulta, int posicaoTipoProfissional) {

        dcEtNumeroConsulta.setText(String.valueOf(consulta.getNumeroConsulta()));

        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        dcTvDataConsulta.setText(formatadorData.format(consulta.getDataHoraConsulta().getTime()));

        SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
        dcTvHoraConsulta.setText(formatadorHora.format(consulta.getDataHoraConsulta().getTime()));

        dcSpTipoProfissional.setSelection(posicaoTipoProfissional);

        dcEtNomeProfissional.setText(consulta.getNomeProfissional());

        this.consulta = consulta;
    }
}
