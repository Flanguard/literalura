package com.literaluraReo.literalura;

import com.literaluraReo.literalura.principal.Principal;
import com.literaluraReo.literalura.repository.IAuthorRepository;
import com.literaluraReo.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	ILibroRepository libroRepository;

	@Autowired
	IAuthorRepository autorRepository;


	public static void main(String[] args)  {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository,autorRepository);
		principal.menu();
	}
}
