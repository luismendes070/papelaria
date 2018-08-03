/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author LuisWindows7
 */
public abstract class BD {
    
    public Statement statement;
    public ResultSet resultSet;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String caminho = "jdbc:mysql://localhost/papelaria2015";
    private final String usuario = "root";
    private final String senha = "1113l";
    /**Objeto que realiza conexão na classe BD*/
    public Connection connection;
    
    public void criaConexao(){
        
        try {
            System.setProperty("jdbc.Drivers", driver);
            connection = DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão!\n Erro:" + e.getMessage());
        }
    }//fim do método
    
    public void desconecta(){
        try {
            connection.close();
            //JOptionPane.showMessageDialog(null, "Desconectado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão!\n Erro:" + e.getMessage());
        }
    }
   
    /**
     *
     * @param nomeTabela nome tabela do banco de dados
     * @param chave nome da chave primária
     * @param stringSQL valor da chave primária 
     */
    public void excluir(String nomeTabela, String chave, String stringSQL){
        try {
            criaConexao();
            
            stringSQL = "delete from "+nomeTabela+" where "+chave+" like '"+stringSQL+"'";
            
            JOptionPane.showMessageDialog(null, "SQL Delete: "+stringSQL);
            
            PreparedStatement pst = connection.prepareStatement(stringSQL);
            //pst.setString(1, stringSQL);
            pst.execute();
            desconecta();
            //JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        }
    }//fim excluir
    
        /**
     *
     * @param nomeTabela nome tabela do banco de dados
     * @param chave nome da chave primária
     * @param idTabela valor da chave primária 
     */
    public void excluir(String nomeTabela, String chave, int idTabela){
        try {
            criaConexao();
            
            String sql = "delete from "+nomeTabela+" where "+chave+"  = "+idTabela;
            
            //JOptionPane.showMessageDialog(null, sql);
            
            PreparedStatement pst = connection.prepareStatement(sql); 
            pst.execute();
            desconecta();
            //JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!");
        }
    }//fim excluir
    
    //    
    public void executaSQL(String sql){
        
        try {
            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro em executaSQL");
        }
        
    }//fim metodo executaSQL
}//fim da classe abstrata BD
