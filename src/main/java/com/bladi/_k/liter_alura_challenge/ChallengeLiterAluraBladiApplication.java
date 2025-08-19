package com.bladi._k.liter_alura_challenge;

import com.bladi._k.liter_alura_challenge.main.MainApplication;
import com.bladi._k.liter_alura_challenge.repository.AuthorRepository;
import com.bladi._k.liter_alura_challenge.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiterAluraBladiApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraBladiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MainApplication mainApplication = new MainApplication(bookRepository, authorRepository);
		mainApplication.showMenu();
	}
}
