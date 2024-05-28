package br.edu.brazcubas.restaurante.view;

import java.sql.SQLException;
import java.util.Scanner;

import br.edu.brazcubas.restaurante.controller.FuncionarioLoginController;
import br.edu.brazcubas.restaurante.model.entity.Funcionario;
import br.edu.brazcubas.restaurante.view.menu.MenuCliente;
import br.edu.brazcubas.restaurante.view.menu.MenuFuncionario;
import br.edu.brazcubas.restaurante.view.menu.MenuPedido;
import br.edu.brazcubas.restaurante.view.menu.MenuPrato;

public class SistemaRestaurante {
    private FuncionarioLoginController funcLoginCon;

    public SistemaRestaurante(FuncionarioLoginController funcLoginCon) {
        this.funcLoginCon = funcLoginCon;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void imprimirCabecalho(){
        System.out.println("--- SISTEMA RESTAURANTE ---");
    }

    public void rodar(Scanner scan) throws SQLException {

        boolean logado = false;
        String nome = "";
        String senha = "";
        clearScreen();
        imprimirCabecalho();
        System.out.println("FAÇA LOGIN PARA ENTRAR");
        while (logado == false) {            
            System.out.print("Nome: ");
            nome = scan.nextLine();
            System.out.print("Senha: ");
            senha = scan.nextLine();
            logado = funcLoginCon.logar(nome, senha);
            if(logado == false){
                clearScreen();
                imprimirCabecalho();
                System.out.println("NOME OU SENHA INCORRETOS!");
            }
        }

        Funcionario funcLogado = funcLoginCon.retornarFunc(nome, senha);
        int opPrincipal = 0;
        do{
            clearScreen();
            System.out.println("--- SISTEMA RESTAURANTE ---\n");
            System.out.println("BEM VINDO AO SISTEMA, "+funcLogado.getNome()+"!\n");
            System.out.println("MENU:");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Pedidos");
            System.out.println("3 - Gerenciar Pratos");
            System.out.println("4 - Gerenciar Funcionarios");
            System.out.println("5 - Sair");
            System.out.print("\n> ");
            opPrincipal = scan.nextInt();

            switch(opPrincipal){
                case 1:
                    clearScreen();
                    MenuCliente menuCliente = new MenuCliente();
                    menuCliente.exibirMenu();
                    break;
                case 2:
                    MenuPedido menuPedido = new MenuPedido(funcLogado);
                    menuPedido.exibirMenuPedidos();
                    break;
                case 3:
                    MenuPrato menuPrato = new MenuPrato();
                    menuPrato.exibirMenuPratos();
                    break;
                case 4:
                    MenuFuncionario menuFuncionario = new MenuFuncionario();
                    menuFuncionario.exibirMenu(funcLogado.getCargo());
                    break;
                default: 
                    break;
            }
        }while(opPrincipal != 5);

        rodar(scan);
    }

}
