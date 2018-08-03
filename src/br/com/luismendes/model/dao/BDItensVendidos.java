/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.dao;

import br.com.luismendes.model.vo.ModelItensVendidos;
import br.com.luismendes.model.vo.ModeloTabelas;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author LuisWindows7
 */
public class BDItensVendidos extends BD {

    /**
     * insere uma linha no banco de dados
     *
     * @param item linha do banco de dados
     */
    public void inserir(ModelItensVendidos item) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO itens_vendidos(id_venda, id_produto, quantidade) "
                    + "VALUES (?,?,?)");

            preparedStatement.setInt(1, item.getIdVenda());
            preparedStatement.setInt(2, item.getIdProduto());
            preparedStatement.setInt(3, item.getQuantidadeItens());

            preparedStatement.executeUpdate();

            // JOptionPane.showMessageDialog(null, "Novo item vendido!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar item!");
        }

        desconecta();
    }//fim metodo inserir

    public void preencheTabelaItens(String query, JTable jtItens) {

        ArrayList dados = new ArrayList();

        String[] columnNames;
        columnNames = new String[]{
            "Código do produto",
            "Quantidade"};

        try {
            criaConexao();
            PreparedStatement pst = connection.prepareStatement(query);
            resultSet = pst.executeQuery();

            while (resultSet.next()) {

                dados.add(
                        new Object[]{
                            resultSet.getInt(3),
                            resultSet.getInt(4)
                        }
                );
            }//fim while

            desconecta();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher tabela de itens vendidos!");
        }

        ModeloTabelas modeloTabelas = new ModeloTabelas(dados, columnNames);
        jtItens.setModel(modeloTabelas);

    }//fim metodo 

    public void exibeTabelaItensCompleta(String query, JTable jtItens) {

        ArrayList dados = new ArrayList();

        String[] columnNames;
        columnNames = new String[]{
            "Código do item",
            "Código da venda",
            "Código do produto",
            "Quantidade"};

        try {
            criaConexao();
            PreparedStatement pst = connection.prepareStatement(query);
            resultSet = pst.executeQuery();

            while (resultSet.next()) {

                dados.add(
                        new Object[]{
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4)
                        }
                );
            }//fim while

            desconecta();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher tabela de itens vendidos!");
        }

        ModeloTabelas modeloTabelas = new ModeloTabelas(dados, columnNames);
        jtItens.setModel(modeloTabelas);

    }//fim metodo 

    public void updateQuantidadeItens(ModelItensVendidos miv) {

        criaConexao();

        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(""
                            + "UPDATE itens_vendidos "
                            + "SET quantidade = ? "
                            + "WHERE id_item_vendidos = ? and id_venda = ? and id_produto = ?;");

            preparedStatement.setInt(1, miv.getQuantidadeItens());
            preparedStatement.setInt(2, miv.getIdItemVendido());
            preparedStatement.setInt(3, miv.getIdVenda());
            preparedStatement.setInt(4, miv.getIdProduto());

            preparedStatement.execute();

            //JOptionPane.showMessageDialog(null, "Quantidade de itens atualizada na tabela itens_vendidos!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque de produto!\n\n" + ex);
        }

        desconecta();

    }//fim updateQualidade

    public ArrayList<ModelItensVendidos> buscarItensVendidos(int idVenda) {//busca compras de um cliente

        ArrayList listaItens = null;

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "select * from itens_vendidos where id_venda = ?");
            preparedStatement.setInt(1, idVenda);

            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();

            resultSet.first();
            listaItens = new ArrayList<ModelItensVendidos>();

            do {

                listaItens.add(
                        new ModelItensVendidos(
                                resultSet.getInt(1),
                                resultSet.getInt(2),
                                resultSet.getInt(3),
                                resultSet.getInt(4)
                        )
                );

                //JOptionPane.showMessageDialog(null, "Itens buscados com sucesso!");
            } while (resultSet.next());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em buscar itens vendidos!\n" + ex);
        }

        desconecta();

        return listaItens;
    }//fim metodo setTabelaClientes

    public ModelItensVendidos buscarItemVendido(int idItemVendido) {//busca compras de um cliente

        ModelItensVendidos itemVendido = null;

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "select * from itens_vendidos where id_item_vendidos = ?");
            preparedStatement.setInt(1, idItemVendido);

            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();

            resultSet.first();
            itemVendido
                    = new ModelItensVendidos(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getInt(3),
                            resultSet.getInt(4)
                    );

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em buscar itens vendidos!\n" + ex);
        }

        desconecta();

        return itemVendido;
    }//fim metodo setTabelaClientes

    public void alterarID(ModelItensVendidos miv) {

        criaConexao();

        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(""
                            + "UPDATE itens_vendidos "
                            + "SET id_item_vendidos = ? "
                            + "WHERE id_venda = ?;");

            preparedStatement.setInt(1, miv.getIdItemVendido());
            preparedStatement.setInt(2, miv.getIdVenda());

            preparedStatement.execute();

            //JOptionPane.showMessageDialog(null, "Quantidade de itens atualizada na tabela itens_vendidos!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque de produto!\n\n" + ex);
        }

        desconecta();

    }//fim do método alterarID
}//fim da classe BDItensVendidos
