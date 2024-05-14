package br.edu.brazcubas.restaurante.model.entity;

public class Prato {
    private int id;
    private String nome;
    private String descricao;
    private Double preco;
    private Double avaliacao;

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


    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }


    public void setPreco(Double preco){
        this.preco = preco;
    }

    public Double getPreco(){
        return this.preco;
    }


    public void setAvaliacao(Double avaliacao){
        this.avaliacao = avaliacao;
    }

    public Double getAvaliacao(){
        return this.avaliacao;
    }
}

