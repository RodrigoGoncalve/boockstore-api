package com.rodrigo.boockstore.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.boockstore.domain.Livro;
import com.rodrigo.boockstore.dtos.LivroDTO;
import com.rodrigo.boockstore.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	@Autowired
	private LivroService livroService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id){
		Livro obj = livroService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value= "categoria", defaultValue = "0") Integer id_cat){
		
		List<Livro> list = livroService.findAll(id_cat);
		
		List<LivroDTO> listDto = list.stream()
				.map(obj -> new LivroDTO(obj))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	// Quando queremos atualizar tudo
	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro obj){
		Livro objNew = livroService.update(id, obj);
		return ResponseEntity.ok().body(objNew);
	}
	// Quando queremos atualizar somento uma informação
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro obj){
		Livro objNew = livroService.update(id, obj);
		return ResponseEntity.ok().body(objNew);
	}

}
