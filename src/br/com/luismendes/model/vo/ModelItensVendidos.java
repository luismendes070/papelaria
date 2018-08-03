/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.vo;

import java.util.ArrayList;

/**
 *
 * @author LuisWindows7
 */
public class ModelItensVendidos {

    private int idItemVendido;
    private int idVenda;
    private int idProduto;
    private int quantidadeItens;

    public ModelItensVendidos(int idVenda) {
        this.idVenda = idVenda;
    }
    
    public ModelItensVendidos(int idProduto, int quantidadeItens) {
        this.idProduto = idProduto;
        this.quantidadeItens = quantidadeItens;
    }

    public ModelItensVendidos(int idVenda, int idProduto, int quantidade) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.quantidadeItens = quantidade;
    }

    public ModelItensVendidos(int idItemVendido, int idVenda, int idProduto, int quantidadeItens) {
        this.idItemVendido = idItemVendido;
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.quantidadeItens = quantidadeItens;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public int getIdItemVendido() {
        return idItemVendido;
    }

    public void setIdItemVendido(int id_item_vendido) {
        this.idItemVendido = id_item_vendido;
    }
   
}//fim da classe ModelItensVendidos
