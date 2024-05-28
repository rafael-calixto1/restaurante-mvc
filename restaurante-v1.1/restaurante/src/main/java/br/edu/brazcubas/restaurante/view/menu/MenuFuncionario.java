package br.edu.brazcubas.restaurante.view.menu;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import br.edu.brazcubas.restaurante.controller.FuncionarioController;
import br.edu.brazcubas.restaurante.model.dao.FuncionarioDAO;
import br.edu.brazcubas.restaurante.model.entity.Funcionario;
import br.edu.brazcubas.restaurante.view.FuncionarioView;

public class MenuFuncionario {
    private FuncionarioController funcionarioController;
    private FuncionarioDAO funcionarioDAO;
    private FuncionarioView funcionarioView;
    private Scanner scanner;

    public MenuFuncionario() {
        // Aqui você instancia o FuncionarioDAO dentro do MenuView
        this.scanner = new Scanner(System.in);
        this.funcionarioDAO = new FuncionarioDAO();
        this.funcionarioController = new FuncionarioController(this.funcionarioDAO);
        this.funcionarioView = new FuncionarioView();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void imprimirCabecalho() {
        System.out.println("--- SISTEMA RESTAURANTE ---");
    }

    public void exibirMenu(String cargo) {
        if (!cargo.equals("Gerente")) {
            clearScreen();
            imprimirCabecalho();
            System.out.println("\nGERENCIAR FUNCIONARIOS");
            System.out.println("\nVoce não tem permissões. Retorne!");
            scanner.nextLine();
            return;
        }
        int opcao;
        do {
            clearScreen();
            imprimirCabecalho();
            System.out.println("\nGERENCIAR FUNCIONARIOS");
            System.out.println("\nMENU:");
            System.out.println("1. Cadastrar Funcionario");
            System.out.println("2. Atualizar Funcionario");
            System.out.println("3. Excluir Funcionario");
            System.out.println("4. Listar Funcionarios");
            System.out.println("5. Descrever Funcionario");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner
            System.out.println("");
            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    atualizarFuncionario();
                    break;
                case 3:
                    excluirFuncionario();
                    break;
                case 4:
                    try {
                        listarFuncionarios();
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case 5:
                    descreverFuncionario();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void descreverFuncionario() {
        System.out.print("CPF do Funcionario a descrever: ");
        String cpf = scanner.nextLine();

        System.out.println("");

        funcionarioView.imprimirInformacoesFuncionario(funcionarioController.buscarFuncionario(new Funcionario(cpf)));

        System.out.println("");
        scanner.nextLine();
    }

    private void cadastrarFuncionario() {
        // Solicitar dados do funcionario ao usuário
        System.out.print("Nome do Funcionario: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do Funcionario: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a Data de Nascimento: ");
        String dataNasc = scanner.nextLine();
        System.out.print("Digite o Cargo do Funcionario: ");
        String cargo = scanner.nextLine();
        System.out.print("Digite a Senha do Funcionario: ");
        String senha = scanner.nextLine();

        // Criar um objeto Funcionario com os dados informados
        Funcionario funcionario = new Funcionario(nome, cpf, Date.valueOf(dataNasc), cargo, senha);

        try {
            // Chamar o método cadastrarFuncionario() do FuncionarioController
            String mensagem = funcionarioController.cadastrarFuncionario(funcionario);
            System.out.println("");
            System.out.println(mensagem);
            System.out.println("");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionario: " + e.getMessage());
        }
    }

    private void atualizarFuncionario() {
        System.out.print("CPF do Funcionario a ser atualizado: ");
        String cpf = scanner.nextLine();

        Funcionario funcionario = funcionarioController.buscarFuncionario(new Funcionario(cpf));
        if (funcionario != null) {
            System.out.print("Novo Nome: ");
            String novoNome = scanner.nextLine();
            System.out.print("Novo CPF: ");
            String novoCpf = scanner.nextLine();
            System.out.print("Nova Data Nascimento: ");
            String novoDataNasc = scanner.nextLine();
            System.out.print("Novo Cargo: ");
            String novoCargo = scanner.nextLine();
            System.out.print("Nova Senha: ");
            String novoSenha = scanner.nextLine();

            funcionario.setNome(novoNome);
            funcionario.setCpf(novoCpf);
            funcionario.setDataNac(Date.valueOf(novoDataNasc));
            funcionario.setCargo(novoCargo);
            funcionario.setSenha(novoSenha);

            try {
                String mensagem = funcionarioController.atualizarFuncionario(funcionario);
                System.out.println(mensagem);
                System.out.println("");
                scanner.nextLine();
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar funcionario: " + e.getMessage());
            }
        } else {
            System.out.println("Funcionario não encontrado com o ID fornecido.");
        }
    }

    private void excluirFuncionario() {
        System.out.print("CPF do Funcionario a ser excluído: ");
        String cpf = scanner.nextLine();

        try {
            String mensagem = funcionarioController.excluirFuncionario(new Funcionario(cpf));
            System.out.println("");
            System.out.println(mensagem);
            System.out.println("");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionario: " + e.getMessage());
        }
    }

    void listarFuncionarios() throws SQLException {
        try {
            funcionarioView.imprimirListaFuncionarios(funcionarioController.listarFuncionarios());
            System.out.println("");
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println(e);
        }

    }



}