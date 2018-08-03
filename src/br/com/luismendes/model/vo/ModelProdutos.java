/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.vo;

import javax.swing.JOptionPane;

/**
 *
 * @author LuisWindows7
 */
public class ModelProdutos {

    private int idProduto;
    private String nomeProduto;
    private String cnpjFornecedor;
    private float preco;
    private int estoque;

    /**
     * AJUDA busca produto e 
        setPreco();
     */
    public ModelProdutos(int idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.estoque = quantidade;

    }

    public ModelProdutos(String nomeProduto, String cnpjFornecedor, float preco, int estoque) {
        this.nomeProduto = nomeProduto;
        this.cnpjFornecedor = cnpjFornecedor;
        this.preco = preco;
        this.estoque = estoque;
    }
    
    public ModelProdutos(int idProduto, String nomeProduto, String cnpjFornecedor, float preco, int estoque) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.cnpjFornecedor = cnpjFornecedor;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        
        if(estoque == 0){
            JOptionPane.showMessageDialog(null, "Estoque vazio!");
        }
        
        return estoque;
    }
    
    public void diminuiEstoque(int novaQuantidade) {
        
        if(estoque > 0){
        this.estoque = estoque - novaQuantidade;
        }else{
            this.estoque = 0;
            JOptionPane.showMessageDialog(null, "Estoque vazio!");
        }
    }//fim metodo setQuantidadeItens(int, int)
    
    public void aumentaEstoque(int quantidadeExcluida) {
        
        if(estoque > 0){
        this.estoque = estoque + quantidadeExcluida;
        }else{
            this.estoque = 0;
            JOptionPane.showMessageDialog(null, "Estoque vazio!");
        }
    }//fim metodo setQuantidadeItens(int, int)

}//fim da classe ModelProdutos
