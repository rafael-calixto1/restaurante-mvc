package br.edu.brazcubas.restaurante.controller;

import br.edu.brazcubas.restaurante.model.entity.ItemPedido;
import br.edu.brazcubas.restaurante.model.dao.IDAO;

import java.sql.SQLException;
import java.util.List;

public class ItemPedidoController {
    private IDAO<ItemPedido> dao;

    public ItemPedidoController(IDAO<ItemPedido> dao) {
        this.dao = dao;
    }

    public String cadastrarItemPedido(ItemPedido itemPedido) throws SQLException {
        dao.registrar(itemPedido);
        return "Item de pedido cadastrado com sucesso!";
    }

    public String atualizarItemPedido(ItemPedido itemPedido) throws SQLException {
        dao.atualizar(itemPedido);
        return "Item de pedido atualizado com sucesso!";
    }

    public String excluirItemPedido(int id) throws SQLException {
        dao.excluir(id);
        return "Item de pedido excluído com sucesso!";
    }

    public List<ItemPedido> listarItensPedido() {
        return dao.retornarTodos();
    }

    public ItemPedido buscarItemPedidoPorId(int id) {
        return dao.retornarPorId(id);
    }
}
