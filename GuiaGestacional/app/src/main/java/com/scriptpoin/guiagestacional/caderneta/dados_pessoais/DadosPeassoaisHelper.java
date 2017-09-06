package com.scriptpoin.guiagestacional.caderneta.dados_pessoais;

import android.app.Activity;
import android.widget.EditText;

import com.scriptpoin.guiagestacional.R;

/**
 * Created by Willi on 21-Aug-17.
 */

public class DadosPeassoaisHelper {

    private EditText dpNome;
    private EditText dpDataNascimento;
    private EditText dpIdade;
    private EditText dpEndereco;
    private EditText dpNomeComapnheiro;
    private DadosPessoais dadosPessoais;

    public DadosPeassoaisHelper(Activity activity) {

        dpNome = (EditText) activity.findViewById(R.id.dpNome);
        dpDataNascimento = (EditText) activity.findViewById(R.id.dpDataNascimento);
        dpIdade = (EditText) activity.findViewById(R.id.dpIdade);
        dpEndereco = (EditText) activity.findViewById(R.id.dpEndereco);
        dpNomeComapnheiro = (EditText) activity.findViewById(R.id.dpNomeComapnheiro);
        this.dadosPessoais = new DadosPessoais();
    }

    public DadosPessoais pegaDadosPessoais() {

        DadosPessoais dadosPessoais = new DadosPessoais();
        dadosPessoais.setNome(dpNome.getText().toString());
        dadosPessoais.setDataNascimento(dpDataNascimento.getText().toString());
        dadosPessoais.setIdade(Integer.parseInt(dpIdade.getText().toString()));
        dadosPessoais.setEndereco(dpEndereco.getText().toString());
        dadosPessoais.setNomeCompanheiro(dpNomeComapnheiro.getText().toString());

        return dadosPessoais;
    }

    public void preencheDadosPessoais(DadosPessoais dadosPessoais) {

        dpNome.setText(dadosPessoais.getNome());
        dpDataNascimento.setText(dadosPessoais.getDataNascimento());
        dpIdade.setText(dadosPessoais.getIdade());
        dpEndereco.setText(dadosPessoais.getEndereco());
        dpNomeComapnheiro.setText(dadosPessoais.getNomeCompanheiro());
        this.dadosPessoais = dadosPessoais;
    }

}
