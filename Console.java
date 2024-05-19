import java.util.Scanner;

public class Console {
    public static void login(Scanner scan){

        System.out.println("--------LOGIN---------");
        System.out.println("Digite o nome: ");
        String nome = scan.nextLine();
        System.out.printf("Digite a senha: ");
        String senha = scan.nextLine();
    }
    public static void menuPrincipal(Scanner scan){
        System.out.println("===SISTEMA iFAT====");
        System.out.println("MENU");
        System.out.println("1- Gerenciar pratos:");
        System.out.printf("2- Gerenciar cliente");
        System.out.println("3- Gerenciar pedidos");
        System.out.println("4- Gerenciar funcionarios");
        System.out.println("Digite a opcao: ");
        int op = scan.nextInt();
        System.out.println("hello world");
    }
    public static void menuPratos(Scanner scan){
        System.out.println("===GERENCIAMENTO DE PRATOS==="); System.out.println("MENU");
        System.out.println("1- Adicionar prato");
        System.out.printf("2- Remover prato");
        System.out.println("3- Listar pratos");
        System.out.println("Digite a opcao: ");
        int op = scan.nextInt();
        System.out.println("hello world");
    }
    public static void menuCliente(Scanner scan){
        System.out.println("===GERENCIAMENTO DE CLIENTES==="); System.out.println("MENU");
        System.out.println("1- Adicionar cliente");
        System.out.printf("2- Remover cliente");
        System.out.println("3- Listar clientes");
        System.out.println("Digite a opcao: ");
        int op = scan.nextInt();
        System.out.println("hello world");
    }
    public static void menuPedidos(Scanner scan){
        System.out.println("===GERENCIAMENTO DE PEDIDOS==="); System.out.println("MENU");
        System.out.println("1- Adicionar pedidos");
        System.out.printf("2- Remover pedidos");
        System.out.println("3- Listar pedidos");
        System.out.println("Digite a opcao: ");
        int op = scan.nextInt();
        System.out.println("hello world");
    }
    public static void menuFuncionarios(Scanner scan){
        System.out.println("===GERENCIAMENTO DE FUNCIONARIOS==="); System.out.println("MENU");
        System.out.println("1- Adicionar funcionario");
        System.out.printf("2- Remover funcionario");
        System.out.println("3- Listar funcionarios");
        System.out.println("Digite a opcao: ");
        int op = scan.nextInt();
        System.out.println("hello world");
    }
}
