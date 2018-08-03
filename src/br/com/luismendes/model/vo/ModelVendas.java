/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.vo;

import br.com.luismendes.model.dao.BDProdutos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LuisWindows7
 */
public class ModelVendas {

    private int idVenda;
    private String cpfFuncionario;
    private String cpfCliente;
    private float precoTotal;
    private String dataVenda;
    private String horaVenda;

    public ModelVendas() {
    }

    public ModelVendas(int idVenda, String cpfFuncionario, String cpfCliente, float precoTotal, String dataVenda, String horaVenda) {
        this.idVenda = idVenda;
        this.cpfFuncionario = cpfFuncionario;
        this.cpfCliente = cpfCliente;
        this.precoTotal = precoTotal;
        this.dataVenda = dataVenda;
        this.horaVenda = horaVenda;
    }

    public void setPrecoTotal(ArrayList<ModelItensVendidos> listaCompras) {

//        System.out.println("-------------------------------------------------------");
//        System.out.println("\n\nEssa é a lista que chega no método setPrecoTotal()");
//        
//        for (int i = 0; i < listaCompras.size(); i++) {
//            
//            System.out.print("\nid_item_vendido = " + listaCompras.get(i).getIdItemVendido());
//            System.out.print(" id Venda = " + listaCompras.get(i).getIdVenda());
//            System.out.print(" idProduto = " + listaCompras.get(i).getIdProduto());
//            System.out.println("QTD itens= " + listaCompras.get(i).getQuantidadeItens());
//            
//        }
//        System.out.println("-------------------------------------------------------");
        
        
        ModelItensVendidos item;
        BDProdutos bdp;
        ModelProdutos produto;

        float valor = 0;

        for (int i = 0; i < listaCompras.size(); i++) {

            item = listaCompras.get(i);

            bdp = new BDProdutos();
            produto = bdp.buscar(item.getIdProduto());

            if (produto != null) {
                valor = valor + produto.getPreco() * listaCompras.get(i).getQuantidadeItens();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao calcular preço da venda.\nProduto não existe!");
            }
        }//fim for
        
        this.precoTotal = valor;
    }//fim setPrecoVenda

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

//    /**Mais um indicio de que listaCompras pode ser um campo da classe ModelVendas com set e get.*/
//    public void updateQuantidadeListaCompras(int idItem, int novaQuantidade, ArrayList<ModelItensVendidos> listaCompras) {
//
//        for (int i = 0; i < listaCompras.size(); i++) {
//            if (idItem == listaCompras.get(i).getIdItemVendido()) {
//                listaCompras.get(i).setQuantidadeItens(novaQuantidade);
//                
//                JOptionPane.showMessageDialog(null, "Quantidade de itens em lista compras atualizada com sucesso!");
//                
//            }//fim if
//        }//fim for
//    }//fim do método updateListaCompras

    public String getHoraVenda() {
        return horaVenda;
    }

    public void setHoraVenda(String horaVenda) {
        this.horaVenda = horaVenda;
    }
    
    
}//fim da classe Vendas
