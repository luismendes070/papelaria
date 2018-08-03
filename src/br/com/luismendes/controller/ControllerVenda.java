/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.controller;

import br.com.luismendes.model.dao.BD;
import br.com.luismendes.model.dao.BDVendas;
import br.com.luismendes.model.vo.ModelVendas;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LuisWindows7
 */
public class ControllerVenda {//Controle tela Venda

    public void novo() {

    }//fim

    public void editar() {

    }//fim

    /**
     * Exclui vendas com preco_total = 0
     */
    public void cancelaVendas(ModelVendas venda) {

        BDVendas bdv = new BDVendas();
        PreparedStatement pst;

        String consulta = "select * from vendas \n"
                + "\n"
                + "inner join itens_vendidos on vendas.id_venda = itens_vendidos.id_venda  \n"
                + "\n"
                + "inner join produtos on itens_vendidos.id_produto = produtos.id_produto\n"
                + "\n";
        //+ "where vendas.preco_total = 0";

        bdv.criaConexao();

        try {

            bdv.executaSQL(consulta);

            bdv.resultSet.first();

            do {

                try {
                    int qtdProd = bdv.resultSet.getInt("estoque");
                    int qtdVend = bdv.resultSet.getInt("quantidade");
                    int soma = qtdProd + qtdVend;

                    pst = bdv.connection.prepareStatement("update produtos set estoque = ? where id_produto = ?");
                    pst.setInt(1, soma);
                    pst.setInt(2, bdv.resultSet.getInt("id_produto"));
                    pst.execute();
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(null, "Erro ao estornar itens no estoque! \n " + e);
                }

                try {
                    pst = bdv.connection.prepareStatement("delete from itens_vendidos where id_venda = ?");
                    pst.setInt(1, bdv.resultSet.getInt("id_venda"));
                    pst.execute();
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(null, "Erro ao apagar itens da lista de compras! \n " + e);
                }

            } while (bdv.resultSet.next());

            try {
                pst = bdv.connection.prepareStatement("delete from vendas where id_venda = ?");
                pst.setInt(1, venda.getIdVenda());
                pst.execute();

                //JOptionPane.showMessageDialog(null,"Venda cancelada com sucesso!");
                
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null, "Erro ao cancelar código de venda! \n " + e);
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro ao cancelar venda! \n " + e);
        }

        bdv.desconecta();
    }//fim
}//fim da classe ControllerVenda
