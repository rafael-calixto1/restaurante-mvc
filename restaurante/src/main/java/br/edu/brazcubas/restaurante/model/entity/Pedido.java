package br.edu.brazcubas.restaurante.model.entity;

import java.sql.Date;

public class Pedido {
    private int id;
    private String formaPagamento;
    private int idCliente;
    private int idFuncionario;
    private Date dataPedido;
    private Double totalPedido;
    private String status;
    private String nomeCliente;

    public Pedido(int id, String formaPagamento, int idCliente, int idFuncionario, Date dataPedido, Double totalPedido,
            String status) {
        this.id = id;
        this.formaPagamento = formaPagamento;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.dataPedido = dataPedido;
        this.totalPedido = totalPedido;
        this.status = status;
    }

    public Pedido(String formaPagamento, int idCliente, int idFuncionario, Date dataPedido, Double totalPedido,
            String status) {
        this.formaPagamento = formaPagamento;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.dataPedido = dataPedido;
        this.totalPedido = totalPedido;
        this.status = status;
    }

    public Pedido(int idPedido, String formaPagamento, String nomeCliente, Date dataPedido, Double totalPedido, String status) {
        this.id = idPedido;
        this.formaPagamento = formaPagamento;
        this.nomeCliente = nomeCliente;
        this.dataPedido = dataPedido;
        this.totalPedido = totalPedido;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdFuncionario() {
        return this.idFuncionario;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataPedido() {
        return this.dataPedido;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return this.formaPagamento;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Double getTotalPagamento() {
        return this.totalPedido;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}