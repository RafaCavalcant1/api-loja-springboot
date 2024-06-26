package com.educandoweb.cursospring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.cursospring.entities.Order;
import com.educandoweb.cursospring.services.OrderService;

// essa classe vai disponibilizar um recurso web correspondente a entidade Order
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;

	@GetMapping
	// o tipo de resposta é uma <List<Order>> pois é uma lista de usuário
	public ResponseEntity<List<Order>> retornaUsuario(){
		List<Order> list = service.findAll();
		// vai retornar todos usuários
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value ="/{id}") // indica que a requisição vai aceitar um ID dentro da url
	//o @PathVariable serve para o spring aceitar o id como parametro da url
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order objeto = service.findById(id);
		return ResponseEntity.ok().body(objeto);
	}
}
