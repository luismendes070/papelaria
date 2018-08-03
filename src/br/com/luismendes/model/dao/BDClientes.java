/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.dao;

import br.com.luismendes.model.vo.ModelClientes;
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
public class BDClientes extends BD {

    /**
     * insere uma linha no banco de dados
     *
     * @param cliente linha do banco de dados
     */
    public void inserir(ModelClientes cliente) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO clientes(`cpf_cliente`, `nome`, `endereco`, "
                    + "`telefone`, `loja_de_referencia1`, `loja_de_referencia2`, "
                    + "`situacao_cliente`) "
                    + "VALUES (?,?,?,?,?,?,?)");

            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getEndereco());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getLoja1());
            preparedStatement.setString(6, cliente.getLoja2());
            preparedStatement.setBoolean(7, cliente.isSituacao());

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Novo cliente cadastrado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente!");
        }

        desconecta();
    }//fim metodo inserir

    /**
     * atualiza uma linha no banco de dados
     *
     * @param cliente linha do banco de dados
     */
    public void atualizar(ModelClientes cliente) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "UPDATE clientes "
                    + "SET `cpf_cliente` = ?, `nome` = ?, `endereco` = ?, `telefone` = ?, `loja_de_referencia1` = ?,"
                    + " `loja_de_referencia2` = ?, `situacao_cliente` = ? "
                    + "WHERE cpf_cliente = '" + cliente.getCpf() + "'");

            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getEndereco());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getLoja1());
            preparedStatement.setString(6, cliente.getLoja2());
            preparedStatement.setBoolean(7, cliente.isSituacao());

            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Cliente atualizado!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente!");
        }

        desconecta();
    }//fim atualizar

    public void preencheTabela(String query, JTable jtableClientes) {

        ArrayList dados = new ArrayList();

        String[] columnNames;
        columnNames = new String[]{"CPF",
            "Nome",
            "Endereço",
            "Telefone",
            "Loja de referência 1",
            "Loja de referência 2",
            "Situação"};

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
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getBoolean(7) == true ? "pagador":"devedor"
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

    public ModelClientes buscar(String cpfCliente) {

        ModelClientes modelClientes = null;

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from clientes where cpf_cliente like ?");
            preparedStatement.setString(1, cpfCliente);
            
            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();

            resultSet.first();
            
            modelClientes = new ModelClientes(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getBoolean(7));

            //JOptionPane.showMessageDialog(null, "Novo cliente buscado!");

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "CPF não cadastrado!");
        }

        desconecta();

        return modelClientes;
    }//fim metodo setTabelaClientes

    

}//fim da classe BDClientes
