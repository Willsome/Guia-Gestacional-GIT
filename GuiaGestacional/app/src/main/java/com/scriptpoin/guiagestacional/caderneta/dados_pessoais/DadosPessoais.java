package com.scriptpoin.guiagestacional.caderneta.dados_pessoais;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Willi on 20-Aug-17.
 */

public class DadosPessoais implements Serializable {

    private Long id;

    private String nome;
    private Calendar dataNascimento;
    private int idade;
    private String endereco;
    private String nomeCompanheiro;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNomeCompanheiro() {
        return nomeCompanheiro;
    }

    public void setNomeCompanheiro(String nomeCompanheiro) {
        this.nomeCompanheiro = nomeCompanheiro;
    }
}
