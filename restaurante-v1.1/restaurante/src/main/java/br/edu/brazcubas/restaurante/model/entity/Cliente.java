package br.edu.brazcubas.restaurante.model.entity;

public class Cliente extends APessoa {
    public Cliente(int id, String nome, String cpf) {
        super(id, nome, cpf);
    }
    
    public Cliente(String nome, String cpf) {
        super(nome, cpf);
    }

    public Cliente(int id, String nome) {
        super(id, nome);
    }

    public Cliente(String cpf){
        super(cpf);
    }
}