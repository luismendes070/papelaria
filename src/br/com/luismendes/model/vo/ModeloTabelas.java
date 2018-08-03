/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.model.vo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LuisWindows7
 */
public class ModeloTabelas extends AbstractTableModel{

    private ArrayList linhas;
    private String[] colunas;

    public ModeloTabelas(ArrayList linhas, String[] colunas) {
        setLinhas(linhas);
        setColunas(colunas);
    }//fim construtor
    
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int numeroLinha, int numeroColuna) {
        
        Object[] linha = (Object[]) getLinhas().get(numeroLinha);
        return linha[numeroColuna];
    }

    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    
    @Override
    public String getColumnName(int numeroColuna){
        return colunas[numeroColuna];
    }
}//fim classe ModeloTabelas
