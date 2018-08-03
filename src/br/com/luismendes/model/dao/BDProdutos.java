/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.dao;

import br.com.luismendes.model.vo.ModelProdutos;
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
public class BDProdutos extends BD {

    /**
     * insere uma linha no banco de dados
     *
     * @param produto linha do banco de dados
     */
    public void inserir(ModelProdutos produto) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO produtos(`nome_produto`, `cnpj_fornecedor`, "
                    + "`preco`, `estoque`) "
                    + "VALUES (?,?,?,?)");

            preparedStatement.setString(1, produto.getNomeProduto());
            preparedStatement.setString(2, produto.getCnpjFornecedor());
            preparedStatement.setFloat(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getEstoque());

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Novo produto cadastrado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!");
        }

        desconecta();
    }//fim metodo inserir

    /**
     * atualiza uma linha no banco de dados
     *
     * @param produto linha do banco de dados
     */
    public void atualizar(ModelProdutos produto) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "UPDATE `papelaria2015`.`produtos` "
                    + "SET `nome_produto` = ?, `cnpj_fornecedor` = ?, `preco` = ?, `estoque` = ? "
                    + "WHERE `produtos`.`id_produto` = ?;");

            preparedStatement.setString(1, produto.getNomeProduto());
            preparedStatement.setString(2, produto.getCnpjFornecedor());
            preparedStatement.setFloat(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getEstoque());
            preparedStatement.setInt(5, produto.getIdProduto());

            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Produto atualizado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto!" + ex);
        }

        desconecta();
    }//fim atualizar
    
    public void updateEstoque(ModelProdutos produto){
        
        criaConexao();

        try {
            PreparedStatement preparedStatement = 
                    connection.prepareStatement("UPDATE produtos SET estoque = ? WHERE id_produto = ?;");

            preparedStatement.setInt(1, produto.getEstoque());
            preparedStatement.setInt(2, produto.getIdProduto());

            preparedStatement.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque de produto!\n\n" + ex);
        }

        desconecta();
        
    }//fim updateQualidade

    public void preencheTabela(String query, JTable jtProdutos) {

        ArrayList dados = new ArrayList();

        String[] columnNames;
        columnNames = new String[]{"Código",
            "Nome",
            "CNPJ",
            "Preço",
            "Estoque"};

        try {
            criaConexao();
            PreparedStatement pst = connection.prepareStatement(query);
            resultSet = pst.executeQuery();

            while (resultSet.next()) {

                dados.add(
                        new Object[]{
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getFloat(4),
                            resultSet.getInt(5)
                        }
                );
            }

            desconecta();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher tabela de produtos!");
        }

        ModeloTabelas modeloTabelas = new ModeloTabelas(dados, columnNames);
        jtProdutos.setModel(modeloTabelas);

    }//fim metodo 

    public ModelProdutos buscar(int idProduto) {

        ModelProdutos modelProdutos = null;

        criaConexao();

        try {
            
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "select * from produtos where id_produto like ?");
            preparedStatement.setInt(1, idProduto);

            statement = connection.createStatement(
                    resultSet.TYPE_SCROLL_INSENSITIVE,
                    resultSet.CONCUR_READ_ONLY);

            resultSet = preparedStatement.executeQuery();

            resultSet.first();

            modelProdutos = new ModelProdutos(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getFloat(4),
                    resultSet.getInt(5));

            //JOptionPane.showMessageDialog(null, "Novo produto buscado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Produto inexistente!");
        }

        desconecta();

        return modelProdutos;
    }//fim metodo setTabelaClientes
}//fim da classe BDProdutos
