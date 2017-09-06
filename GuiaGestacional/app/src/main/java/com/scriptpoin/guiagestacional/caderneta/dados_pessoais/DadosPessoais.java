package com.scriptpoin.guiagestacional.caderneta.dados_pessoais;

/**
 * Created by Willi on 20-Aug-17.
 */

public class DadosPessoais {

    private String nome;
    private String dataNascimento;
    private int idade;
    private String endereco;
    private String nomeCompanheiro;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
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
