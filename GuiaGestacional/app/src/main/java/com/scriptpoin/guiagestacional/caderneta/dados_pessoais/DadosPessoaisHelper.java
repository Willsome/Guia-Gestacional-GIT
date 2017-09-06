package com.scriptpoin.guiagestacional.caderneta.dados_pessoais;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.scriptpoin.guiagestacional.R;

/**
 * Created by Willi on 21-Aug-17.
 */

public class DadosPessoaisHelper {

    private EditText dpEtNome;
    private TextView dpTvDpDataNascimento;
    private EditText dpEtIdade;
    private EditText dpEtEndereco;
    private EditText dpEtNomeCompanheiro;

    private TextView dpTvNome;
    private TextView dpTvDataNascimento;
    private TextView dpTvIdade;
    private TextView dpTvEndereco;
    private TextView dpTvNomeCompanheiro;

    private DadosPessoais dadosPessoais;

    public DadosPessoaisHelper(Activity activity, int i, View view) {

        if(i == 1) {
            dpEtNome = (EditText) activity.findViewById(R.id.dpEtNome);
            dpTvDpDataNascimento = (TextView) activity.findViewById(R.id.dpTvDpDataNascimento);
            dpEtIdade = (EditText) activity.findViewById(R.id.dpEtIdade);
            dpEtEndereco = (EditText) activity.findViewById(R.id.dpEtEndereco);
            dpEtNomeCompanheiro = (EditText) activity.findViewById(R.id.dpEtNomeCompanheiro);
        } else if(i == 2) {
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

    public DadosPessoais pegaDadosPessoais() {

        DadosPessoais dadosPessoais = new DadosPessoais();
        dadosPessoais.setNome(dpEtNome.getText().toString());
        dadosPessoais.setDataNascimento(dpTvDpDataNascimento.getText().toString());
        dadosPessoais.setIdade(Integer.parseInt(dpEtIdade.getText().toString()));
        dadosPessoais.setEndereco(dpEtEndereco.getText().toString());
        dadosPessoais.setNomeCompanheiro(dpEtNomeCompanheiro.getText().toString());

        return dadosPessoais;
    }

    public void preencheFormularioDadosPessoais(DadosPessoais dadosPessoais) {

        dpEtNome.setText(dadosPessoais.getNome());
        dpTvDpDataNascimento.setText(dadosPessoais.getDataNascimento());
        dpEtIdade.setText(String.valueOf(dadosPessoais.getIdade()));
        dpEtEndereco.setText(dadosPessoais.getEndereco());
        dpEtNomeCompanheiro.setText(dadosPessoais.getNomeCompanheiro());

        this.dadosPessoais = dadosPessoais;
    }

    public void preencheDadosPessoais(DadosPessoais dadosPessoais) {

        dpTvNome.setText(dadosPessoais.getNome());
        dpTvDataNascimento.setText(dadosPessoais.getDataNascimento());
        dpTvIdade.setText(String.valueOf(dadosPessoais.getIdade()));
        dpTvEndereco.setText(dadosPessoais.getEndereco());
        dpTvNomeCompanheiro.setText(dadosPessoais.getNomeCompanheiro());

        this.dadosPessoais = dadosPessoais;
    }

}
