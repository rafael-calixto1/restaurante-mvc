package br.edu.brazcubas.restaurante.model.entity;
import java.sql.Date;

public class Funcionario extends APessoa {
    private Date dataNasc;
    private String cargo;
    private String senha;

    public Funcionario(int id, String nome, String cpf, Date dataNasc, String cargo, String senha) {
        super(id, nome, cpf);
        this.dataNasc = dataNasc;
        this.cargo = cargo;
        this.senha = senha;
    }

    public Funcionario(String nome, String cpf, Date dataNasc, String cargo, String senha) {
        super(nome, cpf);
        this.dataNasc = dataNasc;
        this.cargo = cargo;
        this.senha = senha;
    }

    public Funcionario(int id, String nome){
        super(id, nome);
    }

    public Funcionario(String cpf) {
        super(cpf);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataNac(Date dataNasc){
        this.dataNasc = dataNasc;
    }

    public Date getDataNasc(){
        return this.dataNasc;
    }
    
    public void setCargo(String cargo){
        this.cargo = cargo;
    }

    public String getCargo(){
        return this.cargo;
    }
}