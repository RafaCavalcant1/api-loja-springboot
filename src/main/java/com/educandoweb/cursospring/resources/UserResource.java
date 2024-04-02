package com.educandoweb.cursospring.resources;

import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.cursospring.entities.User;
import com.educandoweb.cursospring.services.UserService;

// essa classe vai disponibilizar um recurso web correspondente a entidade USER
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@GetMapping
	// o tipo de resposta é uma <List<User>> pois é uma lista de usuário
	public ResponseEntity<List<User>> retornaUsuario(){
		List<User> list = service.findAll();
		// vai retornar todos usuários
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value ="/{id}") // indica que a requisição vai aceitar um ID dentro da url
	//o @PathVariable serve para o spring aceitar o id como parametro da url
	public ResponseEntity<User> findById(@PathVariable Long id){
		User objeto = service.findById(id);
		return ResponseEntity.ok().body(objeto);
	}
	
	// inserir no banco de dados um novo obj do tipu user 
	@PostMapping //usado para inserir um novo recurso 
	//definimos o método insert que recebe um objeto do tipo User como parâmetro
	public ResponseEntity<User>insert(@RequestBody User obj){ //requestBody épara dizer que o obj vai chegar no tipo JSON 
		obj = service.insert(obj); //chama um serviço da classe UserService  para inserir o objeto User no banco de dados.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();	// iisso serve para que quando coloque no postman a resposta seja 201 ceated e não 200ok
		return ResponseEntity.created(uri).body(obj);
	}
	
	
	@DeleteMapping(value ="/{id}")
	//void ja que a resposta da requisição não vai retornar nenhum corpo
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build(); // Após a exclusão, retornamos uma resposta HTTP com status 204 (No Content) utilizando ResponseEntity.noContent().
		//Esse status indica que a operação de deleção foi realizada com sucesso e que não há conteúdo para ser retornado como resposta.
	}
	
	//para atualizar um recurso
	@PutMapping(value ="/{id}")
	public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User obj ){ //os paremetros sao: o id que vai chegar na url e vai recev=ber o obj contendo os dados para atualizar
		obj = service.update(id, obj); // atualizou 
		return ResponseEntity.ok().body(obj); 
	}
	
	
	
}
