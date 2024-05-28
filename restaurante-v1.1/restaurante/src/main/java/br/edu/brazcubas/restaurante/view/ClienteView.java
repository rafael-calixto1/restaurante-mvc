package br.edu.brazcubas.restaurante.view;

import br.edu.brazcubas.restaurante.model.entity.Cliente;

import java.util.List;

public class ClienteView {
    public void imprimirInformacoesCliente(Cliente cliente) {
        System.out.println("INFORMAÇÕES DO CLIENTE:");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
    }

    public void imprimirListaClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            System.out.println("ID " + cliente.getId() + " - " + cliente.getNome() + " [CPF: " + cliente.getCpf() + "]");
        }
    }
}
