package br.edu.brazcubas.restaurante.model.entity;

import java.sql.Date;
import java.util.List;

public class Pedido {
    private int id;
    private String formaPagamento;
    private Date dataPedido;
    private Double totalPedido;
    private String status;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<ItemPedido> listaItens;

    public Pedido(int id, String formaPagamento, Date dataPedido, Double totalPedido, String status, Cliente cliente,
            Funcionario funcionario) {
        this.id = id;
        this.formaPagamento = formaPagamento;
        this.dataPedido = dataPedido;
        this.totalPedido = totalPedido;
        this.status = status;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }

    public Pedido(String formaPagamento, Date dataPedido, Double totalPedido, String status, Cliente cliente,
            Funcionario funcionario) {
        this.formaPagamento = formaPagamento;
        this.dataPedido = dataPedido;
        this.totalPedido = totalPedido;
        this.status = status;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }

    public Pedido(List<ItemPedido> listaItens) {
        this.listaItens = listaItens;
    }

    public Pedido(int id, String formaPagamento, Date dataPedido, String status, Funcionario funcionario) {
        this.id = id;
        this.formaPagamento = formaPagamento;
        this.dataPedido = dataPedido;
        this.status = status;
        this.funcionario = funcionario;
    }

    public Pedido(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public Double getTotalPedido() {
        return this.totalPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemPedido> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemPedido> listaItens) {
        this.listaItens = listaItens;
    }

    public void atualizarPedidoItens(Pedido pedido){
        for(ItemPedido itemPedido : this.listaItens){
            itemPedido.setPedido(pedido);
        }
    }
}