package com.william.Biblioteca;

import com.william.Biblioteca.repository.AutorRepository;
import com.william.Biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Main main = new Main(autorRepository,libroRepository);
		main.mostrarMenu();
	}
}
