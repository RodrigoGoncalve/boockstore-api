package com.rodrigo.boockstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.boockstore.domain.Livro;
import com.rodrigo.boockstore.repositories.LivroRepository;
import com.rodrigo.boockstore.service.exceptions.ObjectNoutFoundException;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNoutFoundException(
				"Livro "+id+" não encontrado, Tipo "+Livro.class.getName()));
	}
}
