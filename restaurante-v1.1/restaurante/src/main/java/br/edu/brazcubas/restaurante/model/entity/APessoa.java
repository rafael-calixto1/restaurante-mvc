package br.edu.brazcubas.restaurante.model.entity;

public abstract class APessoa{
    protected int id;
    protected String nome;
    protected String cpf;

    public APessoa(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public APessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public APessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public APessoa(String cpf) {
        this.cpf = cpf;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return this.cpf;
    }
}