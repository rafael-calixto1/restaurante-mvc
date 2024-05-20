package br.edu.brazcubas.restaurante.view;

import br.edu.brazcubas.restaurante.model.entity.ItemPedido;

import java.util.List;

public class ItemPedidoView {
    public void imprimirInformacoesItemPedido(ItemPedido itemPedido) {
        System.out.println("INFORMAÇÕES DO ITEM DE PEDIDO:");
        System.out.println("ID: " + itemPedido.getId());
        System.out.println("ID do Pedido: " + itemPedido.getIdPedido());
        System.out.println("ID do Prato: " + itemPedido.getIdPrato());
        System.out.println("Quantidade: " + itemPedido.getQuantidade());
    }

    public void imprimirListaItensPedido(List<ItemPedido> itensPedido) {
        for (ItemPedido itemPedido : itensPedido) {
            System.out.println("ID: " + itemPedido.getId()
                    + ", ID do Pedido: " + itemPedido.getIdPedido()
                    + ", ID do Prato: " + itemPedido.getIdPrato()
                    + ", Quantidade: " + itemPedido.getQuantidade());
        }
    }
}
