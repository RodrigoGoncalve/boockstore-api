package com.rodrigo.boockstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigo.boockstore.domain.Categoria;
import com.rodrigo.boockstore.domain.Livro;
import com.rodrigo.boockstore.repositories.CategoriaRepository;
import com.rodrigo.boockstore.repositories.LivroRepository;

@SpringBootApplication
public class BoockstoreApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;

	public static void main(String[] args) {
		SpringApplication.run(BoockstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "informática", "livros de TI");
		Livro li = new Livro(null, "Clean Code", "Robert Martin", "Loren ipsum", cat1);
		cat1.getLivros().addAll(Arrays.asList(li));
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1));
		this.livroRepository.saveAll(Arrays.asList(li));
		

	}

}
