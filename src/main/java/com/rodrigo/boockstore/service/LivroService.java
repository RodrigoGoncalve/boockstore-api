package com.rodrigo.boockstore.service;

import java.util.List;
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
	
	@Autowired
	private CategoriaService categoriaService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNoutFoundException(
				"Livro "+id+" não encontrado, Tipo "+Livro.class.getName()));
	}
	
	public List<Livro> findAll(Integer id_cat){
		categoriaService.findById(id_cat);
		return livroRepository.findallByCategoria(id_cat);
	}

	public Livro update(Integer id, Livro obj) {	
		Livro objNew = findById(id);
		updateData(objNew, obj);
	
		return livroRepository.save(objNew);
	}

	private void updateData(Livro objNew, Livro obj) {
		objNew.setTitulo(obj.getTitulo());
		objNew.setNomeAutor(obj.getNomeAutor());
		objNew.setTexto(obj.getTexto());
	}
}
