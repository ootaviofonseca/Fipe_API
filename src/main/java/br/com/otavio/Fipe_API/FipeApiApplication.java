package br.com.otavio.Fipe_API;

import br.com.otavio.Fipe_API.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeApiApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}

}
