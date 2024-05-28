package br.edu.brazcubas.restaurante.view;

import br.edu.brazcubas.restaurante.model.entity.ItemPedido;
import br.edu.brazcubas.restaurante.model.entity.Pedido;

import java.util.List;

public class PedidoView {
    public void imprimirInformacoesPedido(Pedido pedido) {
        System.out.println("INFORMACOES DO PEDIDO");
        System.out.println("ID: " + pedido.getId());
        System.out.println("Cliente: "+pedido.getCliente().getNome());
        System.out.println("Data do Pedido: " + pedido.getDataPedido());
        System.out.println("Forma Pgto.: "+pedido.getFormaPagamento());
        System.out.println("Valor Total: R$ " + pedido.getTotalPedido());
        System.out.println("Status: " + pedido.getStatus());

        System.out.println("\n"+pedido.getListaItens().size()+" ITENS DO PEDIDO");
        ItemPedidoView itemPedidoView = new ItemPedidoView();
        for(ItemPedido itemPedido : pedido.getListaItens()){
            itemPedidoView.imprimirInformacoesItemPedido(itemPedido);
            System.out.println("");
        }
    }

    public void imprimirListaPedidos(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            System.out.println("ID " + pedido.getId() + " - Data do Pedido: " + pedido.getDataPedido() + " [Cliente: " + pedido.getCliente().getNome()+"]");
        }
    }
}
