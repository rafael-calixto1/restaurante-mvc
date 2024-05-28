package br.edu.brazcubas.restaurante.controller;

import br.edu.brazcubas.restaurante.model.entity.Pedido;
import br.edu.brazcubas.restaurante.model.dao.IDAO;

import java.sql.SQLException;
import java.util.List;

public class PedidoController {
    private IDAO<Pedido> dao;

    public PedidoController(IDAO<Pedido> dao) {
        this.dao = dao;
    }

    public String cadastrarPedido(Pedido pedido) throws SQLException {
        dao.registrar(pedido);
        return "Pedido cadastrado com sucesso!";
    }

    public String atualizarPedido(Pedido pedido) throws SQLException {
        dao.atualizar(pedido);
        return "Pedido atualizado com sucesso!";
    }

    public String excluirPedido(Pedido pedido) throws SQLException {
        dao.excluir(pedido);
        return "Pedido excluído com sucesso!";
    }

    public List<Pedido> listarPedidos() {
        return dao.retornarTodos();
    }

    public Pedido buscarPedidoPorId(Pedido pedido) {
        return dao.retornar(pedido);
    }
}
