package br.edu.brazcubas.restaurante.model.entity;
import java.util.Date;

public class Cliente extends APessoa {
    private Date dataRegistro;

    public void setDataRegistro(Date dataRegistro){
        this.dataRegistro = dataRegistro;
    } 

    public Date getDataRegistro(){
        return this.dataRegistro;
    }
}