package br.edu.brazcubas.restaurante;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.brazcubas.restaurante.controller.ClienteController;
import br.edu.brazcubas.restaurante.model.dao.ClienteDAO;
import br.edu.brazcubas.restaurante.model.dao.IDAO;
import br.edu.brazcubas.restaurante.model.entity.Cliente;

@SpringBootApplication
public class RestauranteApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(RestauranteApplication.class, args);
		//PostgresConfig.testInsert();
		Cliente cliente = new Cliente("Rafael Correa", "123.456.789-22");
		ClienteController cc = new ClienteController(new ClienteDAO());
		//cc.cadastrarCliente(cliente);

		Cliente novoCliente = new Cliente(2, "Henrique Matos", "321.654.764-43");
		//cc.atualizar(novoCliente);

		//cc.excluir(2);

		/*for(Cliente cli : cc.retornarTodos()){
			System.out.println("Nome: "+cli.getNome());
			System.out.println("CPF: "+cli.getCpf());
			System.out.println("");
		}*/

		Cliente queryCliente = cc.retornarPorId(3);
		System.out.println("Nome: "+queryCliente.getNome());
		System.out.println("CPF: "+queryCliente.getCpf());
	}
}
