/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.dao;

import br.com.luismendes.model.vo.ModelFuncionarios;
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
public class BDFuncionarios extends BD {

    /**
     * insere uma linha no banco de dados
     *
     * @param funcionario linha do banco de dados
     */
    public void inserir(ModelFuncionarios funcionario) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO funcionarios(`cpf_funcionario`, `nome_funcionario`, `endereco_funcionario`, `telefone_funcionario`)"
                    + "VALUES (?,?,?,?)");

            preparedStatement.setString(1, funcionario.getCpf());
            preparedStatement.setString(2, funcionario.getNome());
            preparedStatement.setString(3, funcionario.getEndereco());
            preparedStatement.setString(4, funcionario.getTelefone());

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Novo funcionário cadastrado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário!");
        }

        desconecta();
    }//fim metodo inserir

    /**
     * atualiza uma linha no banco de dados
     *
     * @param funcionario linha do banco de dados
     */
    public void atualizar(ModelFuncionarios funcionario) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "UPDATE funcionarios "
                    + "SET `cpf_funcionario` = ?, `nome_funcionario` = ?, `endereco_funcionario` = ?, `telefone_funcionario` = ?"
                    + "WHERE cpf_funcionario = '" + funcionario.getCpf() + "'");

            preparedStatement.setString(1, funcionario.getCpf());
            preparedStatement.setString(2, funcionario.getNome());
            preparedStatement.setString(3, funcionario.getEndereco());
            preparedStatement.setString(4, funcionario.getTelefone());

            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Funcionário atualizado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário!");
        }

        desconecta();
    }//fim atualizar

    public void preencheTabela(String query, JTable jtableClientes) {

        ArrayList dados = new ArrayList();

        String[] columnNames;
        columnNames = new String[]{"CPF",
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
            JOptionPane.showMessageDialog(null, "Erro ao preencher cliente!");
        }

        ModeloTabelas modeloTabelas = new ModeloTabelas(dados, columnNames);
        jtableClientes.setModel(modeloTabelas);

    }//fim metodo 

    public ModelFuncionarios buscar(String cpfFuncionario) {

        ModelFuncionarios modelFuncionarios = null;

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from funcionarios where cpf_funcionario like ?");
            preparedStatement.setString(1, cpfFuncionario);

            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();

            resultSet.first();

            modelFuncionarios = new ModelFuncionarios(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));

            //JOptionPane.showMessageDialog(null, "Novo funcionário buscado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Funcionário não cadastrado!");
        }

        desconecta();

        return modelFuncionarios;
    }//fim metodo setTabelaClientes

}//fim da classe BDFuncionarios
