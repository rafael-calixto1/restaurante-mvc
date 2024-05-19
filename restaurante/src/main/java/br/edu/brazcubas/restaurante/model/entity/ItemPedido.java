package br.edu.brazcubas.restaurante.model.entity;

public class ItemPedido {
    private int id;
    private int idPedido;
    private int idPrato;
    private int quantidade;
    private String nomePrato;

    public ItemPedido(int id, int idPedido, int idPrato, int quantidade) {
        this.id = id;
        this.idPedido = idPedido;
        this.idPrato = idPrato;
        this.quantidade = quantidade;
    }

    public ItemPedido(int idPedido, int idPrato, int quantidade) {
        this.idPedido = idPedido;
        this.idPrato = idPrato;
        this.quantidade = quantidade;
    }

    

    public ItemPedido(int idPedido, int quantidade, String nomePedido) {
        this.idPedido = idPedido;
        this.quantidade = quantidade;
        this.nomePrato = nomePedido;
    }

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

    public int getQuantidade(){
        return this.quantidade;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePedido) {
        this.nomePrato = nomePedido;
    }
}