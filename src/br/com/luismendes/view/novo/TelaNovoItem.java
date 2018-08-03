/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.view.novo;

import br.com.luismendes.controller.ControllerItemVenda;
import br.com.luismendes.model.dao.BDItensVendidos;
import br.com.luismendes.model.vo.ModelItensVendidos;
import br.com.luismendes.model.vo.ModelVendas;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author LuisWindows7
 */
public class TelaNovoItem extends javax.swing.JFrame {

    private ArrayList<ModelItensVendidos> listaCompras;
    private JTable jtItensVenda;
    private ModelItensVendidos itemVendido;
    private ModelVendas vendas;
    private JTextField jtfPrecoTotal;
    private JTable jtProdutos;

    public TelaNovoItem(ArrayList<ModelItensVendidos> listaCompras,
            JTable jtItensVenda, ModelVendas vendas, JTextField jtfPrecoTotal, JTable jtProdutos) {

        this.listaCompras = listaCompras;
        this.jtItensVenda = jtItensVenda;
        this.vendas = vendas;
        this.jtfPrecoTotal = jtfPrecoTotal;
        this.jtProdutos = jtProdutos;

        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);

    }//FIM DO CONSTRUTOR

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfCodigoProduto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfQuantidadeProduto = new javax.swing.JTextField();
        jbAdicionarItem = new javax.swing.JButton();
        jbCancelAddItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar produtos");
        setResizable(false);

        jLabel1.setText("Código Produto");

        jLabel2.setText("Quantidade");

        jbAdicionarItem.setText("Adicionar");
        jbAdicionarItem.setToolTipText("Adicionar a lista de compras");
        jbAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAdicionarItemActionPerformed(evt);
            }
        });

        jbCancelAddItem.setText("Cancelar ");
        jbCancelAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelAddItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbAdicionarItem)
                        .addGap(28, 28, 28)
                        .addComponent(jbCancelAddItem)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAdicionarItem)
                    .addComponent(jbCancelAddItem))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAdicionarItemActionPerformed

        try {
            
            itemVendido = new ModelItensVendidos(
                    vendas.getIdVenda(),
                    Integer.parseInt(jtfCodigoProduto.getText()), //idProduto
                    Integer.parseInt(jtfQuantidadeProduto.getText())// qtdProduto
            );

            ControllerItemVenda controllerItemVenda = new ControllerItemVenda();
            controllerItemVenda.novo(itemVendido, vendas, listaCompras, jtItensVenda, jtProdutos, jtfPrecoTotal);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher valores de novo item!\n" + e);
        }
    }//GEN-LAST:event_jbAdicionarItemActionPerformed

    private void jbCancelAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelAddItemActionPerformed

        dispose();
    }//GEN-LAST:event_jbCancelAddItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbAdicionarItem;
    private javax.swing.JButton jbCancelAddItem;
    private javax.swing.JTextField jtfCodigoProduto;
    private javax.swing.JTextField jtfQuantidadeProduto;
    // End of variables declaration//GEN-END:variables
}
