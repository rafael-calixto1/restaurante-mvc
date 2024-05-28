package br.edu.brazcubas.restaurante.controller;

import java.sql.SQLException;

import br.edu.brazcubas.restaurante.model.dao.FuncionarioDAO;
import br.edu.brazcubas.restaurante.model.entity.Funcionario;

public class FuncionarioLoginController {
    private FuncionarioDAO dao;

    public FuncionarioLoginController(FuncionarioDAO dao) {
        this.dao = dao;
    }

    public boolean logar(String nome, String senha) throws SQLException {
        return dao.logar(nome, senha);
    }

    public Funcionario retornarFunc(String nome, String senha) throws SQLException {
        try {
            return dao.retornarPorNomeSenha(nome, senha);
        } catch (Exception e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
            return null;
        }
    }
}
