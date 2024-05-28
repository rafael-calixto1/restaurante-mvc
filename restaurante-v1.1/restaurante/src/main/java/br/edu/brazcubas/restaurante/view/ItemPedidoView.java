package br.edu.brazcubas.restaurante.view;

import br.edu.brazcubas.restaurante.model.entity.ItemPedido;

import java.util.List;

public class ItemPedidoView {
    public void imprimirInformacoesItemPedido(ItemPedido itemPedido) {
        System.out.println(" - ID Item: " + itemPedido.getId());
        System.out.println(" - Nome do Prato: " + itemPedido.getPrato().getNome());
        System.out.println(" - Preco: R$ "+ itemPedido.getPrato().getPreco());
        System.out.println(" - Quantidade: " + itemPedido.getQuantidade());
    }

    public void imprimirListaItensPedido(List<ItemPedido> itensPedido) {
        for (ItemPedido itemPedido : itensPedido) {
            System.out.println("ID: " + itemPedido.getId()
                    + ", Prato: " + itemPedido.getPrato().getNome()
                    + ", Preco: R$" + itemPedido.getPrato().getPreco()
                    + ", Quantidade: " + itemPedido.getQuantidade());
        }
    }
}
