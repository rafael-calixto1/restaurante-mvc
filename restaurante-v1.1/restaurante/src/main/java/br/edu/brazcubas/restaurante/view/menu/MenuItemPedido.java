package br.edu.brazcubas.restaurante.view.menu;

import java.sql.SQLException;
import java.util.Scanner;

import br.edu.brazcubas.restaurante.controller.ItemPedidoController;
import br.edu.brazcubas.restaurante.controller.PratoController;
import br.edu.brazcubas.restaurante.model.dao.ItemPedidoDAO;
import br.edu.brazcubas.restaurante.model.dao.PratoDAO;
import br.edu.brazcubas.restaurante.model.entity.ItemPedido;
import br.edu.brazcubas.restaurante.model.entity.Pedido;
import br.edu.brazcubas.restaurante.model.entity.Prato;
import br.edu.brazcubas.restaurante.view.ItemPedidoView;
import br.edu.brazcubas.restaurante.view.PratoView;

public class MenuItemPedido {
    private Pedido pedido;
    private ItemPedidoController itemPedidoController;
    private ItemPedidoView itemPedidoView;
    private Scanner scanner;

    public MenuItemPedido(Pedido pedido){
        this.pedido = pedido;
        this.itemPedidoController = new ItemPedidoController(new ItemPedidoDAO(pedido));
        this.itemPedidoView = new ItemPedidoView();
        this.scanner = new Scanner(System.in);
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
            System.out.println("\nGERENCIAR ITENS DO PEDIDO ID "+this.pedido.getId());
            System.out.println("\nMENU:");
            System.out.println("1. Cadastrar Item");
            System.out.println("2. Atualizar Item");
            System.out.println("3. Excluir Item");
            System.out.println("4. Listar Itens");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner
            System.out.println("");

            switch (opcao) {
                case 1:
                    cadastrarItemPedido();
                    break;
                case 2:
                    atualizarItemPedido();
                    break;
                case 3:
                    excluirItemPedido();
                    break;
                case 4:
                    listarItensPedido();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public void cadastrarItemPedido(){
            String simNao = "N";
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
            itemPedido.setPedido(this.pedido);

            try{
                String mensagem = itemPedidoController.cadastrarItemPedido(itemPedido);
                System.out.println(mensagem);
                System.out.println("\n");
                scanner.nextLine();
            } catch (Exception e){
                e.printStackTrace();
            }
    }

    public void excluirItemPedido(){
        System.out.println("Digite o ID do Item de Pedido: ");
        int id = scanner.nextInt();

        ItemPedido itemPedidoProc = new ItemPedido(id);
        itemPedidoProc.setPedido(this.pedido);

        ItemPedido itemPedido = itemPedidoController.buscarItemPedido(itemPedidoProc);
        itemPedido.setPedido(pedido);

        System.out.println("");

        try {
            String mensagem = itemPedidoController.excluirItemPedido(itemPedido);
            System.out.println(mensagem);
            scanner.nextLine();
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarItemPedido(){
        System.out.println("Digite o ID do Item de Pedido: ");
        int id = scanner.nextInt();

        System.out.println("");
        scanner.nextLine();

        System.out.print("Digite a nova quantidade: ");
        int quantidade = scanner.nextInt();

        ItemPedido itemPedidoProc = new ItemPedido(id);
        itemPedidoProc.setPedido(this.pedido);

        ItemPedido itemPedido = itemPedidoController.buscarItemPedido(itemPedidoProc);
        int quantidadeAnterior = itemPedido.getQuantidade();

        itemPedido.setQuantidadeAnterior(quantidadeAnterior);
        itemPedido.setQuantidade(quantidade);
        itemPedido.setPedido(pedido);

        try {
            String mensagem = itemPedidoController.atualizarItemPedido(itemPedido);
            System.out.println("");
            System.out.println(mensagem);
            System.out.println("");
            scanner.nextLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void listarItensPedido(){
        itemPedidoView.imprimirListaItensPedido(itemPedidoController.listarItensPedido());
        System.out.println("");
        scanner.nextLine();
    }
}
