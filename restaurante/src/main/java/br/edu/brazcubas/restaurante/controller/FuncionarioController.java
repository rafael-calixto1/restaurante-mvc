package br.edu.brazcubas.restaurante.controller;

import br.edu.brazcubas.restaurante.model.entity.Funcionario;
import br.edu.brazcubas.restaurante.model.dao.IDAO;


import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    private IDAO<Funcionario> dao;

    public FuncionarioController(IDAO<Funcionario> dao) {
        this.dao = dao;
    }

    public String cadastrarFuncionario(Funcionario funcionario) throws SQLException {
        dao.registrar(funcionario);
        return "Funcionário cadastrado com sucesso!";
    }

    public String atualizarFuncionario(Funcionario funcionario) throws SQLException {
        dao.atualizar(funcionario);
        return "Funcionário atualizado com sucesso!";
    }

    public String excluirFuncionario(int id) throws SQLException {
        dao.excluir(id);
        return "Funcionário excluído com sucesso!";
    }

    public List<Funcionario> listarFuncionarios() {
        return dao.retornarTodos();
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        return dao.retornarPorId(id);
    }
}
