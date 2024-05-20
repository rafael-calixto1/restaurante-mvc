package br.edu.brazcubas.restaurante.view;

import br.edu.brazcubas.restaurante.model.entity.Pedido;

import java.util.List;

public class PedidoView {
    public void imprimirInformacoesPedido(Pedido pedido) {
        System.out.println("INFORMAÇÕES DO PEDIDO:");
        System.out.println("ID: " + pedido.getId());
        System.out.println("Data do Pedido: " + pedido.getDataPedido());
        System.out.println("Status: " + pedido.getStatus());
        System.out.println("Valor Total: " + pedido.getValorTotal());
    }

    public void imprimirListaPedidos(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            System.out.println("ID " + pedido.getId() + " - Data do Pedido: " + pedido.getDataPedido() + " [Status: " + pedido.getStatus() + ", Valor Total: " + pedido.getValorTotal() + "]");
        }
    }
}
