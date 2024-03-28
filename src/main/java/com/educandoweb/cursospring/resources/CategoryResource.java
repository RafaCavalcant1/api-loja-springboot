package com.educandoweb.cursospring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.cursospring.entities.Category;
import com.educandoweb.cursospring.services.CategoryService;

// essa classe vai disponibilizar um recurso web correspondente a entidade USER
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;

	@GetMapping
	// o tipo de resposta é uma <List<Category>> pois é uma lista de usuário
	public ResponseEntity<List<Category>> retornaUsuario(){
		List<Category> list = service.findAll();
		// vai retornar todos usuários
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value ="/{id}") // indica que a requisição vai aceitar um ID dentro da url
	//o @PathVariable serve para o spring aceitar o id como parametro da url
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category objeto = service.findById(id);
		return ResponseEntity.ok().body(objeto);
	}
}
