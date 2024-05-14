package br.edu.brazcubas.restaurante.model.entity;

public class ItemPedido {
    private int id;
    private int idPedido;
    private int idPrato;
    private int quantidade;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }


    public void setIdPedido(int idPedido){
        this.idPedido = idPedido;
    }

    public int getIdPedido(){
        return this.idPedido;
    }


    public void setIdPrato(int idPrato){
        this.idPrato = idPrato;
    }

    public int getIdPrato(){
        return this.idPrato;
    }


    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public int getQuntidade(){
        return this.quantidade;
    }
}