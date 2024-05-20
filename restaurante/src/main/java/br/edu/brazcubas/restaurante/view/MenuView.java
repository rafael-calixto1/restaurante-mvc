package br.edu.brazcubas.restaurante.view;

import br.edu.brazcubas.restaurante.controller.ClienteController;
import br.edu.brazcubas.restaurante.model.dao.ClienteDAO;
import br.edu.brazcubas.restaurante.model.dao.IDAO;
import br.edu.brazcubas.restaurante.model.entity.Cliente;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    private Scanner scanner;
    private ClienteController clienteController;

    public MenuView() {
        scanner = new Scanner(System.in);
        // Aqui você instancia o ClienteDAO dentro do MenuView
        IDAO<Cliente> clienteDAO = new ClienteDAO();
        clienteController = new ClienteController(clienteDAO);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Atualizar Cliente");
            System.out.println("3. Excluir Cliente");
            System.out.println("4. Listar Clientes");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    atualizarCliente();
                    break;
                case 3:
                    excluirCliente();
                    break;
                case 4:

                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        // Solicitar dados do cliente ao usuário
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do Cliente: ");
        String cpf = scanner.nextLine();

        // Criar um objeto Cliente com os dados informados
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);

        try {
            // Chamar o método cadastrarCliente() do ClienteController
            String mensagem = clienteController.cadastrarCliente(cliente);
            System.out.println(mensagem);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }


    private void atualizarCliente() {
        // Solicitar ID do cliente ao usuário
        System.out.print("ID do Cliente a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        // Buscar o cliente pelo ID
        Cliente cliente = clienteController.retornarPorId(id);
        if (cliente != null) {
            // Solicitar novos dados ao usuário
            System.out.print("Novo Nome do Cliente: ");
            String nome = scanner.nextLine();
            System.out.print("Novo CPF do Cliente: ");
            String cpf = scanner.nextLine();

            // Atualizar os dados do cliente
            cliente.setNome(nome);
            cliente.setCpf(cpf);

            try {
                // Chamar o método atualizar() do ClienteController
                String mensagem = clienteController.atualizar(cliente);
                System.out.println(mensagem);
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente não encontrado com o ID fornecido.");
        }
    }

    private void excluirCliente() {
        // Solicitar ID do cliente ao usuário
        System.out.print("ID do Cliente a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        try {
            // Chamar o método excluir() do ClienteController
            String mensagem = clienteController.excluir(id);
            System.out.println(mensagem);
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}
