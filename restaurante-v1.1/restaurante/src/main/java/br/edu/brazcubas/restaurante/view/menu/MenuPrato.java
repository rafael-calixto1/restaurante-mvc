package br.edu.brazcubas.restaurante.view.menu;

import java.sql.SQLException;
import java.util.Scanner;

import br.edu.brazcubas.restaurante.controller.PratoController;
import br.edu.brazcubas.restaurante.model.dao.PratoDAO;
import br.edu.brazcubas.restaurante.model.entity.Prato;
import br.edu.brazcubas.restaurante.view.PratoView;

public class MenuPrato {
    private Scanner scanner;
    private PratoController pratoController;
    private PratoView pratoView;

    public MenuPrato() {
        this.scanner = new Scanner(System.in);
        this.pratoController = new PratoController(new PratoDAO());
        this.pratoView = new PratoView();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void imprimirCabecalho() {
        System.out.println("--- SISTEMA RESTAURANTE ---");
    }

    public void exibirMenuPratos() throws SQLException {
        int opcao;
        do {
            clearScreen();
            imprimirCabecalho();
            System.out.println("\nGERENCIAR PRATOS");
            System.out.println("\nMENU:");
            System.out.println("1. Cadastrar Prato");
            System.out.println("2. Atualizar Prato");
            System.out.println("3. Excluir Prato");
            System.out.println("4. Listar Pratos");
            System.out.println("5. Descrever Prato");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner
            System.out.println("");

            switch (opcao) {
                case 1:
                    cadastrarPrato();
                    break;
                case 2:
                    atualizarPrato();
                    break;
                case 3:
                    excluirPrato();
                    break;
                case 4:
                    listarPratos();
                    break;
                case 5:
                    descreverPrato();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }


private void descreverPrato() {
        System.out.print("ID do Prato a descrever: ");
        int id = scanner.nextInt();

        System.out.println("");

        pratoView.imprimirInformacoesPrato(pratoController.buscarPrato(new Prato(id)));

        System.out.println("");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void cadastrarPrato() {
        System.out.print("Nome do Prato: ");
        String nome = scanner.nextLine();
        System.out.print("Preço do Prato: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer do scanner
        System.out.print("Descrição do Prato: ");
        String descricao = scanner.nextLine();
        System.out.print("Avaliação do Prato: ");
        double avaliacao = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer do scanner

        Prato prato = new Prato( nome, descricao, preco, avaliacao);
        try {
            String mensagem = pratoController.cadastrarPrato(prato);
            System.out.println(mensagem);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar prato: " + e.getMessage());
        }
    }

    private void atualizarPrato() {
        System.out.print("ID do Prato a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        Prato prato = pratoController.buscarPrato(new Prato(id));
        if (prato != null) {
            System.out.print("Novo Nome do Prato: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Preço do Prato: ");
            double preco = scanner.nextDouble();
            scanner.nextLine(); // Limpar o buffer do scanner
            System.out.print("Nova Descrição do Prato: ");
            String descricao = scanner.nextLine();
            System.out.print("Nova Avaliação do Prato: ");
            double avaliacao = scanner.nextDouble();
            scanner.nextLine(); // Limpar o buffer do scanner

            prato.setNome(nome);
            prato.setPreco(preco);
            prato.setDescricao(descricao);
            prato.setAvaliacao(avaliacao);

            try {
                String mensagem = pratoController.atualizarPrato(prato);
                System.out.println(mensagem);
                System.out.println("");
                scanner.nextLine();
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar prato: " + e.getMessage());
            }
        } else {
            System.out.println("Prato não encontrado com o ID fornecido.");
        }
    }

    private void excluirPrato() {
        System.out.print("ID do Prato a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        try {
            String mensagem = pratoController.excluirPrato(new Prato(id));
            System.out.println("");
            System.out.println(mensagem);
            System.out.println("");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir prato: " + e.getMessage());
        }
    }

    private void listarPratos() throws SQLException {
        try {
            pratoView.imprimirListaPratos(pratoController.listarPratos());
            System.out.println("");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao listar pratos: " + e.getMessage());
        }
    }
}
