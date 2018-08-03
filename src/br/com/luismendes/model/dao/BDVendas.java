/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.dao;

import br.com.luismendes.model.vo.ModelClientes;
import br.com.luismendes.model.vo.ModelFuncionarios;
import br.com.luismendes.model.vo.ModelVendas;
import br.com.luismendes.model.vo.ModeloTabelas;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author LuisWindows7
 */
public class BDVendas extends BD {

    /**
     * insere uma linha no banco de dados
     *
     * @param v linha do banco de dados
     */
    public void inserirVenda(ModelVendas v) {

        criaConexao();

        try {

            GregorianCalendar gc = new GregorianCalendar();

            PreparedStatement pst
                    = connection.prepareStatement(
                            "insert into vendas(cpf_funcionario, cpf_cliente, preco_total, data_venda, hora_venda)"
                            + "values(?,?,?,?,?) ");

            v.setDataVenda(geraDataVenda());
            v.setHoraVenda(geraHoraVenda());

            //JOptionPane.showMessageDialog(null, "Hora da venda: " + v.getHoraVenda());

            pst.setString(1, v.getCpfFuncionario());
            pst.setString(2, v.getCpfCliente());
            pst.setFloat(3, v.getPrecoTotal());
            pst.setString(4, v.getDataVenda());
            pst.setString(5, v.getHoraVenda());

            pst.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir venda! \n"+ex);
        }

        desconecta();
    }//fim metodo inserirVenda

    /**
     * atualiza uma linha no banco de dados
     *
     * @param venda linha do banco de dados
     */
    public void atualizar(ModelVendas venda) {

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "UPDATE `papelaria2015`.`vendas` "
                    + "SET `id_venda` = ?, `cpf_funcionario` = ?, `cpf_cliente` = ?, `preco_total` = ?, `data_venda` = ? , hora_venda = ?"
                    + "WHERE `vendas`.`id_venda` = ?");

            preparedStatement.setInt(1, venda.getIdVenda());
            preparedStatement.setString(2, venda.getCpfFuncionario());
            preparedStatement.setString(3, venda.getCpfCliente());
            preparedStatement.setFloat(4, venda.getPrecoTotal());
            preparedStatement.setString(5, geraDataVenda());
            preparedStatement.setString(6, geraHoraVenda());
            preparedStatement.setInt(7, venda.getIdVenda());

            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Venda atualizada!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar venda!\n\n" + ex);
        }

        desconecta();
    }//fim atualizar

    public void preencheTabela(String query, JTable jtVendas) {

        ArrayList dados = new ArrayList();

        String[] columnNames;
        columnNames = new String[]{
            "Código da venda",
            "Funcionário",
            "Cliente",
            "Preço total",
            "Data",
            "Hora"};

        try {
            criaConexao();
            PreparedStatement pst = connection.prepareStatement(query);
            resultSet = pst.executeQuery();

            while (resultSet.next()) {

                BDFuncionarios bdf = new BDFuncionarios();
                ModelFuncionarios mf = bdf.buscar(resultSet.getString(2));

                BDClientes bdc = new BDClientes();
                ModelClientes mc = bdc.buscar(resultSet.getString(3));

                if (mf != null && mc != null) {

                    String cpfFuncionario = mf.getNome();
                    String cpfCliente = mc.getNome();

                    dados.add(
                            new Object[]{
                                resultSet.getInt(1),
                                cpfFuncionario,
                                cpfCliente,
                                resultSet.getFloat(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                            }
                    );
                } else {
                    //JOptionPane.showMessageDialog(null, "Erro ao preencher tabela de vendas: funcionário ou cliente não existe!");
                }
            }

            desconecta();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher tabela de vendas!");
        }

        ModeloTabelas modeloTabelas = new ModeloTabelas(dados, columnNames);
        jtVendas.setModel(modeloTabelas);

    }//fim metodo 

    //retorna uma venda
    public ModelVendas buscarVenda(int idVenda) {//busca compras de um cliente

        ModelVendas modelVendas = null;

        criaConexao();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vendas where id_venda = ?");
            preparedStatement.setInt(1, idVenda);

            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();

            resultSet.first();

            modelVendas = new ModelVendas(
                    idVenda,
                    resultSet.getString(2), //cpf funcionário
                    resultSet.getString(3), //cpf cliente
                    resultSet.getFloat(4), //preco
                    resultSet.getString(5), // data
                    resultSet.getString(6) // hora
            );

           // JOptionPane.showMessageDialog(null, "Nova venda buscada!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Venda inexistente!");
        }

        desconecta();

        return modelVendas;
    }//fim metodo setTabelaVendas

    private String geraDataVenda() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date hoje = new Date();

        return sdf.format(hoje);
    }//fim do metodo data

    private String geraHoraVenda() {

        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        String hora = format.format(new Date());
        
        return hora;
    }//fim do metodo data
}//fim da classe BDVendas
