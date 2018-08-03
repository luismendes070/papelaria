/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.dao;

import br.com.luismendes.model.vo.ModelFornecedores;
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
public class BDFornecedores extends BD {

    /**
     * insere uma linha no banco de dados
     *
     * @param fornecedor linha do banco de dados
     */
    public void inserir(ModelFornecedores fornecedor) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO fornecedores(`cnpj`, `nome`, `endereco`, `telefone`) "
                    + "VALUES (?,?,?,?)");

            preparedStatement.setString(1, fornecedor.getCnpj());
            preparedStatement.setString(2, fornecedor.getNome());
            preparedStatement.setString(3, fornecedor.getEndereco());
            preparedStatement.setString(4, fornecedor.getTelefone());

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Novo fornecedor cadastrado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedor!");
        }

        desconecta();
    }//fim metodo inserir

    /**
     * atualiza uma linha no banco de dados
     *
     * @param fornecedor linha do banco de dados
     */
    public void atualizar(ModelFornecedores fornecedor) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "UPDATE fornecedores "
                    + "SET `cnpj` = ?, `nome` = ?, `endereco` = ?, `telefone` = ?"
                    + "WHERE cpf_cliente = '" + fornecedor.getCnpj() + "'");

            preparedStatement.setString(1, fornecedor.getCnpj());
            preparedStatement.setString(2, fornecedor.getNome());
            preparedStatement.setString(3, fornecedor.getEndereco());
            preparedStatement.setString(4, fornecedor.getTelefone());

            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Fornecedor atualizado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar fornecedor!");
        }

        desconecta();
    }//fim atualizar

    public void preencheTabela(String query, JTable jtFornecedor) {

        ArrayList dados = new ArrayList();

        String[] columnNames;
        columnNames = new String[]{"CNPJ",
            "Nome",
            "Endereço",
            "Telefone"};

        try {
            criaConexao();
            PreparedStatement pst = connection.prepareStatement(query);
            resultSet = pst.executeQuery();

            while (resultSet.next()) {

                dados.add(
                        new Object[]{
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                        }
                );
            }

            desconecta();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher tabela fornecedor!");
        }

        ModeloTabelas modeloTabelas = new ModeloTabelas(dados, columnNames);
        jtFornecedor.setModel(modeloTabelas);

    }//fim metodo 

    public ModelFornecedores buscar(String cpfCliente) {

        ModelFornecedores modelFornecedores = null;

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from clientes where cpf_cliente like ?");
            preparedStatement.setString(1, cpfCliente);

            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();

            resultSet.first();

            modelFornecedores = new ModelFornecedores(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));

            //JOptionPane.showMessageDialog(null, "Novo fornecedor buscado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "CNPJ do fornecedor é inexistente!");
        }

        desconecta();

        return modelFornecedores;
    }//fim metodo setTabelaClientes

}//fim da classe BDFornecedores
