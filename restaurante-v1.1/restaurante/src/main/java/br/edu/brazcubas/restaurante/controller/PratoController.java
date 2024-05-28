package br.edu.brazcubas.restaurante.controller;

import br.edu.brazcubas.restaurante.model.entity.Prato;
import br.edu.brazcubas.restaurante.model.dao.IDAO;

import java.sql.SQLException;
import java.util.List;

public class PratoController {
    private IDAO<Prato> dao;

    public PratoController(IDAO<Prato> dao) {
        this.dao = dao;
    }

    public String cadastrarPrato(Prato prato) throws SQLException {
        dao.registrar(prato);
        return "Prato cadastrado com sucesso!";
    }

    public String atualizarPrato(Prato prato) throws SQLException {
        dao.atualizar(prato);
        return "Prato atualizado com sucesso!";
    }

    public String excluirPrato(Prato prato) throws SQLException {
        dao.excluir(prato);
        return "Prato excluído com sucesso!";
    }

    public List<Prato> listarPratos() {
        return dao.retornarTodos();
    }

    public Prato buscarPrato(Prato prato) {
        return dao.retornar(prato);
    }
}
