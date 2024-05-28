package br.edu.brazcubas.restaurante;


import br.edu.brazcubas.restaurante.controller.FuncionarioLoginController;
import br.edu.brazcubas.restaurante.model.dao.FuncionarioDAO;
import br.edu.brazcubas.restaurante.view.SistemaRestaurante;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
    	FuncionarioLoginController funcLoginCon = new FuncionarioLoginController(new FuncionarioDAO());

		SistemaRestaurante restauranteApp = new SistemaRestaurante(funcLoginCon);
		restauranteApp.rodar(scan);
	}
}