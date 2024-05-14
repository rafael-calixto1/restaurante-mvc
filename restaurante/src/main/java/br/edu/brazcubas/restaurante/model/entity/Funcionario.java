package br.edu.brazcubas.restaurante.model.entity;
import java.util.Date;

public class Funcionario extends APessoa {
    private Date dataNasc;
    private String cargo;
    
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