package br.edu.brazcubas.restaurante;


import br.edu.brazcubas.restaurante.view.MenuView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestauranteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteApplication.class, args);

		MenuView menuView = new MenuView();
		menuView.exibirMenu();
	}
}
