/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.luismendes.controller;

import br.com.luismendes.model.dao.BDItensVendidos;
import br.com.luismendes.model.dao.BDProdutos;
import br.com.luismendes.model.vo.ModelItensVendidos;
import br.com.luismendes.model.vo.ModelProdutos;
import br.com.luismendes.model.vo.ModelVendas;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author LuisWindows7
 */
public class ControllerItemVenda {//Controla tela

    public void novo(ModelItensVendidos novoItem, ModelVendas vendas, ArrayList<ModelItensVendidos> listaCompras,
            JTable jtItensVenda, JTable jtProdutos, JTextField jtfPrecoTotal
    ) {

        int idProduto = novoItem.getIdProduto();

        //System.out.println("idProduto = "+idProduto);
        //Busca produto para saber quantidade em estoque
        BDProdutos bdp = new BDProdutos();
        ModelProdutos produto = bdp.buscar(idProduto);

        if (produto.getEstoque() > 0 && produto != null) {

            int quantidadeEscolhida = novoItem.getQuantidadeItens();
            int quantidadeItemEstoque = produto.getEstoque();

            if (quantidadeEscolhida > 0 && quantidadeEscolhida <= quantidadeItemEstoque) {

                //atualiza quantidade do produto na tabela produtos
                produto.diminuiEstoque(quantidadeEscolhida);
                bdp.updateEstoque(produto);

                //atualiza tabela itens_vendidos
                novoItem = new ModelItensVendidos(vendas.getIdVenda(), idProduto, quantidadeEscolhida);
                BDItensVendidos bdItemVendido = new BDItensVendidos();
                bdItemVendido.inserir(novoItem);

                //adiciona item a lista de compras para poder calcular o preço total
                listaCompras = null;
                //System.out.println("Vou buscar listaCompras para id_venda = "+vendas.getIdVenda());
                listaCompras = bdItemVendido.buscarItensVendidos(vendas.getIdVenda());

                if (listaCompras != null) {

                    vendas.setPrecoTotal(listaCompras);

                    BDProdutos bdProdutos = new BDProdutos();
                    bdProdutos.preencheTabela("select * from produtos", jtProdutos);

                    //exibe todos os itens da venda
                    bdItemVendido.exibeTabelaItensCompleta(""
                            + "select * "
                            + "from itens_vendidos "
                            + "where id_venda = " + vendas.getIdVenda(), jtItensVenda);

                    jtfPrecoTotal.setText("" + vendas.getPrecoTotal());
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar lista de compras no banco de dados!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Quantidade de itens inválida!");
            }
        }//fim produto !null
    } //fim do método novo

    public void editar(
            ModelItensVendidos itemEditando,
            JTable jtItensVenda,
            ModelVendas venda,
            ArrayList<ModelItensVendidos> listaCompras,
            JTextField jtfPrecoTotal,
            JTable jtProdutos) {

        int idProduto = -1;
        int novaQuantidadeComprada = -1;
        int qtdEmEstoque = -1;;

        ModelProdutos estoqueProduto;

        BDItensVendidos bdItensVendidos;
        BDProdutos bdp;

        //recupera idProduto do jtextfield
        bdp = new BDProdutos();
        idProduto = itemEditando.getIdProduto();

        estoqueProduto = bdp.buscar(idProduto);//busca produto pelo idProduto

        if (estoqueProduto != null) {

            novaQuantidadeComprada = itemEditando.getQuantidadeItens();

            qtdEmEstoque = estoqueProduto.getEstoque();

            if (novaQuantidadeComprada >= 0 && novaQuantidadeComprada < qtdEmEstoque) {

                estoqueProduto.diminuiEstoque(novaQuantidadeComprada);//atualiza estoque

                itemEditando.setIdVenda(venda.getIdVenda());
                itemEditando.setQuantidadeItens(novaQuantidadeComprada);

                //atualiza lista de compra
                bdItensVendidos = new BDItensVendidos();
                bdItensVendidos.updateQuantidadeItens(itemEditando);

                //atualiza tabela de produtos em estoque
                bdp.preencheTabela("select * from produtos", jtProdutos);

                //JOptionPane.showMessageDialog(null, "id_venda = " + venda.getIdVenda());
                //atualiza tabela de itens vendidos
                bdItensVendidos.exibeTabelaItensCompleta("select * from itens_vendidos where id_venda = " + venda.getIdVenda(), jtItensVenda);

                listaCompras = bdItensVendidos.buscarItensVendidos(venda.getIdVenda());

                System.out.println("Nova lista apose edita quantidade de itens!");

                for (int i = 0; i < listaCompras.size(); i++) {
                    System.out.println("idProduto " + listaCompras.get(i).getIdProduto());
                    System.out.println("Quantidade do item " + listaCompras.get(i).getQuantidadeItens());
                }

                if (listaCompras != null) {
                    venda.setPrecoTotal(listaCompras);
                    jtfPrecoTotal.setText("" + venda.getPrecoTotal());
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar lista de compras após editar item!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Quantidade de itens inválida!");
            }
        }//fim validaBusca
    }//fim do método editar

    public void excluir(
            JTable jtItensVenda,
            ArrayList<ModelItensVendidos> listaCompras,
            ModelVendas vendas) {

        int linha = jtItensVenda.getSelectedRow();

        if (linha > -1) {

            BDItensVendidos bdItensVendidos = new BDItensVendidos();
            int idItemVendido = Integer.valueOf(jtItensVenda.getValueAt(linha, 0).toString());

            ModelItensVendidos itemExcluido = bdItensVendidos.buscarItemVendido(idItemVendido);

            if (itemExcluido != null) {
                //tabela antes de remover item
                bdItensVendidos.exibeTabelaItensCompleta("select * from itens_vendidos where id_venda = " + vendas.getIdVenda(), jtItensVenda);

                //remove item do bd
                bdItensVendidos.excluir("itens_vendidos", "id_item_vendidos", (int) jtItensVenda.getValueAt(linha, 0));

                //faz estorno do item_vendido de volta para o estoque
                BDProdutos brProdutos = new BDProdutos();
                ModelProdutos produtoEmEstoque = brProdutos.buscar(itemExcluido.getIdProduto());
                produtoEmEstoque.aumentaEstoque(itemExcluido.getQuantidadeItens());
                brProdutos.updateEstoque(produtoEmEstoque);
                
                //atualiza tabela itens após remover linha
                bdItensVendidos.exibeTabelaItensCompleta("select * from itens_vendidos where id_venda = " + vendas.getIdVenda(), jtItensVenda);

                //busca lista de itens da respectiva venda
                listaCompras = null;
                listaCompras = bdItensVendidos.buscarItensVendidos(vendas.getIdVenda());

                vendas.setPrecoTotal(listaCompras);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Você deve escolher uma linha da tabela!");
        }
    }//fim do método excluir
}//fim da classe ControllerItemVenda
