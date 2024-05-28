package br.edu.brazcubas.restaurante.controller;

import br.edu.brazcubas.restaurante.model.entity.Cliente;

import java.sql.SQLException;
import java.util.List;

import br.edu.brazcubas.restaurante.model.dao.IDAO;

public class ClienteController {
    private IDAO<Cliente> dao;

    public ClienteController(IDAO<Cliente> dao){
        this.dao = dao;
    }

    public String cadastrarCliente(Cliente cliente) throws SQLException{
        dao.registrar(cliente);
        return "Cadastrado com sucesso!";
    }

    public String atualizar(Cliente cliente) throws SQLException {
        dao.atualizar(cliente);
        return "Atualizado com sucesso!";
    }

    public String excluir(Cliente cliente) throws SQLException {
        dao.excluir(cliente);
        return "Excluido com sucesso!";
    }

    public List<Cliente> retornarTodos(){
        return dao.retornarTodos();
    }

    public Cliente retornar(Cliente cliente){
        return dao.retornar(cliente);
    }

}