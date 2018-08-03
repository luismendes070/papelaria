/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.vo;

/**
 *
 * @author LuisWindows7
 */
public class ModelClientes {
    
    private String cpf, nome, endereco, telefone, loja1, loja2;
    private boolean situacao;

    public ModelClientes(String cpf, String nome, String endereco, 
            String telefone, String loja1, String loja2, boolean situacao) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.loja1 = loja1;
        this.loja2 = loja2;
        this.situacao = situacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLoja1() {
        return loja1;
    }

    public void setLoja1(String loja1) {
        this.loja1 = loja1;
    }

    public String getLoja2() {
        return loja2;
    }

    public void setLoja2(String loja2) {
        this.loja2 = loja2;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
}//fim da classe ModelClientes
