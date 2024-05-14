package br.edu.brazcubas.restaurante.controller;

import br.edu.brazcubas.restaurante.model.entity.Cliente;

import br.edu.brazcubas.restaurante.model.dao.IDAO;

public class ClienteController {
    private IDAO<Cliente> dao;

    public ClienteController(IDAO<Cliente> dao){
        this.dao = dao;
    }

    public String cadastrarCliente(Cliente cliente){
        dao.registrar(cliente);
        return "Cadastrado com sucesso!";
    }
}