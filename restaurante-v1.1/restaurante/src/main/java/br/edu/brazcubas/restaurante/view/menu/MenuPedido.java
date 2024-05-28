package br.edu.brazcubas.restaurante.view.menu;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.brazcubas.restaurante.controller.ClienteController;
import br.edu.brazcubas.restaurante.controller.PedidoController;
import br.edu.brazcubas.restaurante.controller.PratoController;
import br.edu.brazcubas.restaurante.model.dao.ClienteDAO;
import br.edu.brazcubas.restaurante.model.dao.PedidoDAO;
import br.edu.brazcubas.restaurante.model.dao.PratoDAO;
import br.edu.brazcubas.restaurante.model.entity.Cliente;
import br.edu.brazcubas.restaurante.model.entity.Funcionario;
import br.edu.brazcubas.restaurante.model.entity.ItemPedido;
import br.edu.brazcubas.restaurante.model.entity.Pedido;
import br.edu.brazcubas.restaurante.model.entity.Prato;
import br.edu.brazcubas.restaurante.view.ClienteView;
import br.edu.brazcubas.restaurante.view.PedidoView;
import br.edu.brazcubas.restaurante.view.PratoView;

public class MenuPedido {
    private Scanner scanner;
    private PedidoView pedidoView;
    private PedidoController pedidoController;
    private Funcionario funcLogado;

    public MenuPedido(Funcionario funcLogado) {
        this.scanner = new Scanner(System.in);
        this.pedidoView = new PedidoView();
        this.pedidoController = new PedidoController(new PedidoDAO());
        this.funcLogado = funcLogado;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void imprimirCabecalho() {
        System.out.println("--- SISTEMA RESTAURANTE ---");
    }

    public void exibirMenuPedidos() throws SQLException {
        int opcao;
        do {
            clearScreen();
            imprimirCabecalho();
            System.out.println("\nGERENCIAR PEDIDOS");
            System.out.println("\nMENU:");
            System.out.println("1. Cadastrar Pedido");
            System.out.println("2. Atualizar Pedido");
            System.out.println("3. Excluir Pedido");
            System.out.println("4. Listar Pedidos");
            System.out.println("5. Descrever Pedido");
            System.out.println("6. Gerenciar Itens do Pedido");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner
            System.out.println("");

            switch (opcao) {
                case 1:
                    cadastrarPedido();
                    break;
                case 2:
                    atualizarPedido();
                    break;
                case 3:
                    excluirPedido();
                    break;
                case 4:
                    listarPedidos();
                    break;
                case 5:
                    descreverPedido();
                    break;
                case 6:
                    gerenciarItemPedido();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void gerenciarItemPedido(){
        Pedido pedido = null;
        String simNao = "N";
        do {
            clearScreen();
            imprimirCabecalho();
            int idPedido = 0;

            System.out.println("Digite o ID do pedido: ");
            idPedido = scanner.nextInt();
            
            System.out.println("");

            pedido = pedidoController.buscarPedidoPorId(new Pedido(idPedido));
            pedidoView.imprimirInformacoesPedido(pedido);
            do {
                System.out.println("\nEstá correto? (s/n)");
                simNao = scanner.next();
                simNao = simNao.toUpperCase();
            } while (!simNao.toUpperCase().equals("S") && !simNao.toUpperCase().equals("N"));
        } while (!simNao.equals("S"));
        MenuItemPedido menuItemPedido = new MenuItemPedido(pedido);
        menuItemPedido.exibirMenu();
    }

    private void excluirPedido() {
        System.out.println("Digite o ID do pedido: ");
        int id = scanner.nextInt();

        System.out.println("");

        try {
            String mensagem = pedidoController.excluirPedido(new Pedido(id));
            System.out.println(mensagem);
            scanner.nextLine();
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarPedido() {
        System.out.println("Digite o ID do pedido: ");
        int id = scanner.nextInt();

        System.out.println("");
        scanner.nextLine();

        System.out.print("Digite a nova data: ");
        String data = scanner.nextLine();

        System.out.print("Digite a nova forma de pagamento: ");
        String pagamento = scanner.nextLine();

        System.out.print("Digite o novo status: ");
        String status = scanner.nextLine();

        Pedido pedido = new Pedido(id, pagamento, Date.valueOf(data), status, funcLogado);
        try {
            String mensagem = pedidoController.atualizarPedido(pedido);
            System.out.println("");
            System.out.println(mensagem);
            System.out.println("");
            scanner.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void descreverPedido() {
        System.out.println("Digite o ID do pedido: ");
        int id = scanner.nextInt();

        System.out.println("");
        pedidoView.imprimirInformacoesPedido(pedidoController.buscarPedidoPorId(new Pedido(id)));
        scanner.nextLine();
        scanner.nextLine();
    }

    private void listarPedidos() {
        pedidoView.imprimirListaPedidos(pedidoController.listarPedidos());
        System.out.println("");
        scanner.nextLine();
    }

    private void cadastrarPedido() {
        Double total = 0.00;
        List<ItemPedido> listaItens = new ArrayList<>();
        Pedido pedido = new Pedido(0, null, null, total, null, null, null);
        ClienteController clienteController = new ClienteController(new ClienteDAO());
        ClienteView clienteView = new ClienteView();
        String simNao;
        Cliente cliente = null;
        int primeiro = 0;

        do {
            clearScreen();
            imprimirCabecalho();
            String cpf = "";

            System.out.println("Digite o CPF do cliente: ");
            if(primeiro == 0){
                cpf = scanner.nextLine();
                primeiro = 1;
            } else {
                scanner.nextLine();
                cpf = scanner.nextLine();
            }
            
            System.out.println("");

            cliente = clienteController.retornar(new Cliente(cpf));
            clienteView.imprimirInformacoesCliente(cliente);
            do {
                System.out.println("\nEstá correto? (s/n)");
                simNao = scanner.next();
                simNao = simNao.toUpperCase();
            } while (!simNao.toUpperCase().equals("S") && !simNao.toUpperCase().equals("N"));
        } while (!simNao.equals("S"));

        System.out.println("");

        System.out.print("Quantos pratos diferentes o cliente deseja? ");
        int quantidade = scanner.nextInt();

        for (int i = 0; i < quantidade; i++) {
            simNao = "N";
            Prato prato = null;
            ItemPedido itemPedido = new ItemPedido(0, null);

            do {
                clearScreen();
                imprimirCabecalho();
                System.out.print("Digite o ID do prato: ");
                int idPrato = scanner.nextInt();
                System.out.println("");
                PratoController pratoController = new PratoController(new PratoDAO());
                PratoView pratoView = new PratoView();
                prato = pratoController.buscarPrato(new Prato(idPrato));
                pratoView.imprimirInformacoesPrato(prato);
                do {
                    System.out.println("\nEstá correto? (s/n)");
                    simNao = scanner.next();
                    simNao = simNao.toUpperCase();
                } while (!simNao.toUpperCase().equals("S") && !simNao.toUpperCase().equals("N"));
            } while (!simNao.equals("S"));

            System.out.print("\nQuantidade de pratos: ");
            int quantidadePratos = scanner.nextInt();

            itemPedido.setPrato(prato);
            itemPedido.setQuantidade(quantidadePratos);
            listaItens.add(itemPedido);
            total += (prato.getPreco() * quantidadePratos);
        }

        clearScreen();
        imprimirCabecalho();
        System.out.println("O total do pedido é: R$ " + total);
        scanner.nextLine();

        System.out.println("");

        System.out.println("Qual a forma de pagamento?");
        String formaPagamento = scanner.nextLine();

        pedido.setCliente(cliente);
        pedido.setFuncionario(this.funcLogado);
        pedido.setDataPedido(Date.valueOf(LocalDate.now().toString()));
        pedido.setTotalPedido(total);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setListaItens(listaItens);
        pedido.setStatus("Novo");

        try {
            String mensagem = pedidoController.cadastrarPedido(pedido);
            System.out.println("\n" + mensagem);
            scanner.nextLine();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
        }
    }
}
