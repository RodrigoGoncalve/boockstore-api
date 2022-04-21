package com.rodrigo.boockstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rodrigo.boockstore.domain.Categoria;
import com.rodrigo.boockstore.dtos.CategoriaDTO;
import com.rodrigo.boockstore.repositories.CategoriaRepository;
import com.rodrigo.boockstore.service.exceptions.ObjectNoutFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNoutFoundException(
				"Objeto não encontrado "+id+", Tipo " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Integer id, CategoriaDTO objDto) {
		Categoria obj = findById(id);
		obj.setNome(objDto.getNome());
		obj.setDescricao(objDto.getDescricao());
		return categoriaRepository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.rodrigo.boockstore.service.exceptions.DataIntegrityViolationException("Categoria não pode ser deletado! "
					+ "Possui livros associados.");
		}
	}

}
