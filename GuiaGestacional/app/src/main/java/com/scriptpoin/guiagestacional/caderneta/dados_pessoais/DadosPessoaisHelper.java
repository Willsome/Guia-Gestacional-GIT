package com.scriptpoin.guiagestacional.caderneta.dados_pessoais;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Willi on 21-Aug-17.
 */

public class DadosPessoaisHelper {

    // FORMULÁRIO
    private EditText dpEtNome;
    private TextView dpTvDpDataNascimento;
    private EditText dpEtIdade;
    private EditText dpEtEndereco;
    private EditText dpEtNomeCompanheiro;

    // CADERNETA
    private TextView dpTvNome;
    private TextView dpTvDataNascimento;
    private TextView dpTvIdade;
    private TextView dpTvEndereco;
    private TextView dpTvNomeCompanheiro;

    // OUTROS
    private DadosPessoais dadosPessoais;

    public DadosPessoaisHelper(Activity activity, int i, View view) {

        if (i == 1) {
            dpEtNome = (EditText) activity.findViewById(R.id.dpEtNome);
            dpTvDpDataNascimento = (TextView) activity.findViewById(R.id.dpTvDpDataNascimento);
            dpEtIdade = (EditText) activity.findViewById(R.id.dpEtIdade);
            dpEtEndereco = (EditText) activity.findViewById(R.id.dpEtEndereco);
            dpEtNomeCompanheiro = (EditText) activity.findViewById(R.id.dpEtNomeCompanheiro);
        } else if (i == 2) {
            dpTvNome = (TextView) view.findViewById(R.id.dpTvNome);
            dpTvDataNascimento = (TextView) view.findViewById(R.id.dpTvDataNascimento);
            dpTvIdade = (TextView) view.findViewById(R.id.dpTvIdade);
            dpTvEndereco = (TextView) view.findViewById(R.id.dpTvEndereco);
            dpTvNomeCompanheiro = (TextView) view.findViewById(R.id.dpTvNomeCompanheiro);
        }

        this.dadosPessoais = new DadosPessoais();
    }

    public TextView getDpTvDpDataNascimento() {
        return dpTvDpDataNascimento;
    }

    public DadosPessoais pegaFormularioDadosPessoais(Calendar dataNascimento) throws Exception {

        DadosPessoais dadosPessoais;

        if (dpEtNome.getText().toString().equals("")
                || dpTvDpDataNascimento.getText().toString().equals("[ADICIONAR]")
                || dpEtIdade.getText().toString().equals("")
                || dpEtEndereco.getText().toString().equals("")
                || dpEtNomeCompanheiro.getText().toString().equals("")) {

            throw new Exception("Campo vazio.");

        } else {

            dadosPessoais = new DadosPessoais();
            dadosPessoais.setNome(dpEtNome.getText().toString());
            dadosPessoais.setDataNascimento(dataNascimento);
            dadosPessoais.setIdade(Integer.parseInt(dpEtIdade.getText().toString()));
            dadosPessoais.setEndereco(dpEtEndereco.getText().toString());
            dadosPessoais.setNomeCompanheiro(dpEtNomeCompanheiro.getText().toString());

            dadosPessoais.setId(this.dadosPessoais.getId());
        }

        return dadosPessoais;
    }

    public void preencheFormularioDadosPessoais(DadosPessoais dadosPessoais) {

        dpEtNome.setText(dadosPessoais.getNome());

        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        dpTvDpDataNascimento.setText(formatadorData.format(dadosPessoais.getDataNascimento().getTime()));

        dpEtIdade.setText(String.valueOf(dadosPessoais.getIdade()));
        dpEtEndereco.setText(dadosPessoais.getEndereco());
        dpEtNomeCompanheiro.setText(dadosPessoais.getNomeCompanheiro());

        this.dadosPessoais = dadosPessoais;
    }

    public void preencheDadosPessoais(DadosPessoais dadosPessoais) {

        dpTvNome.setText(dadosPessoais.getNome());

        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        dpTvDataNascimento.setText(formatadorData.format(dadosPessoais.getDataNascimento().getTime()));

        dpTvIdade.setText(dadosPessoais.getIdade() + " anos");
        dpTvEndereco.setText(dadosPessoais.getEndereco());
        dpTvNomeCompanheiro.setText(dadosPessoais.getNomeCompanheiro());
    }

}
