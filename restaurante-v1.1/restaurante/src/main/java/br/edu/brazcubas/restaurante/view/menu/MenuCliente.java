package br.edu.brazcubas.restaurante.view.menu;

import br.edu.brazcubas.restaurante.controller.ClienteController;
import br.edu.brazcubas.restaurante.model.dao.ClienteDAO;
import br.edu.brazcubas.restaurante.model.dao.IDAO;
import br.edu.brazcubas.restaurante.model.entity.Cliente;
import br.edu.brazcubas.restaurante.view.ClienteView;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuCliente {
    private Scanner scanner;
    private ClienteController clienteController;

    public MenuCliente() {
        scanner = new Scanner(System.in);
        // Aqui você instancia o ClienteDAO dentro do MenuView
        IDAO<Cliente> clienteDAO = new ClienteDAO();
        clienteController = new ClienteController(clienteDAO);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void imprimirCabecalho() {
        System.out.println("--- SISTEMA RESTAURANTE ---");
    }

    public void exibirMenu() {
        int opcao;
        do {
            clearScreen();
            imprimirCabecalho();
            System.out.println("\nGERENCIAR CLIENTES");
            System.out.println("\nMENU:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Atualizar Cliente");
            System.out.println("3. Excluir Cliente");
            System.out.println("4. Listar Clientes");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner
            System.out.println("");

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
                    try {
                        listarClientes();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
        Cliente cliente = new Cliente(nome, cpf);

        try {
            // Chamar o método cadastrarCliente() do ClienteController
            String mensagem = clienteController.cadastrarCliente(cliente);
            System.out.println(mensagem);
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private void atualizarCliente() {
        // Solicitar ID do cliente ao usuário
        System.out.print("CPF do Cliente a ser atualizado: ");
        String cpf = scanner.nextLine();

        // Buscar o cliente pelo ID
        Cliente cliente = clienteController.retornar(new Cliente(cpf));
        if (cliente != null) {
            // Solicitar novos dados ao usuário
            System.out.print("Novo Nome do Cliente: ");
            String novoNome = scanner.nextLine();
            System.out.print("Novo CPF do Cliente: ");
            String novoCpf = scanner.nextLine();

            // Atualizar os dados do cliente
            cliente.setNome(novoNome);
            cliente.setCpf(novoCpf);

            try {
                // Chamar o método atualizar() do ClienteController
                String mensagem = clienteController.atualizar(cliente);
                System.out.println(mensagem);
                scanner.nextLine();
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente não encontrado com o ID fornecido.");
        }
    }

    private void excluirCliente() {
        // Solicitar ID do cliente ao usuário
        System.out.print("CPF do Cliente a ser excluído: ");
        String cpf = scanner.nextLine();

        try {
            // Chamar o método excluir() do ClienteController
            String mensagem = clienteController.excluir(new Cliente(cpf));
            System.out.println(mensagem);
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    private void listarClientes() throws SQLException {
        // Solicitar ID do cliente ao usuário
        try {
            // Chamar o método excluir() do ClienteController
            ClienteView cv = new ClienteView();
            cv.imprimirListaClientes(clienteController.retornarTodos());
            System.out.println("");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}
