package br.edu.brazcubas.restaurante.model.entity;
import java.util.Date;

public class Pedido{
    private int id;
    private int idCliente;
    private int idFuncionario;
    private Date dataPedido;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }


    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }

    public int getIdCliente(){
        return this.idCliente;
    }


    public void setIdFuncionario(int idFuncionario){
        this.idFuncionario = idFuncionario;
    }

    public int getIdFuncionario(){
        return this.idFuncionario;
    }


    public void setDataPedido(Date dataPedido){
        this.dataPedido = dataPedido;
    }

    public Date getDataPedido(){
        return this.dataPedido;
    }
}