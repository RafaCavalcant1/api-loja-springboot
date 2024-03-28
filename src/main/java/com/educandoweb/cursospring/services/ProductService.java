package com.educandoweb.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.cursospring.entities.Product;
import com.educandoweb.cursospring.repositories.ProductRepository;

@Service //registra a classe como um componente do spring e permite que ele seja injetado 
public class ProductService {

	//para que o spring faça a injeção de dependencia de forma transparente 
	@Autowired
	private ProductRepository repository;
	
	//método para retornar todos os usuários do BD 
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	//buscar o usuário pelo id
	public Product findById(Long id) {
		//cha o repositorio pelo ID mas essa operação retorna um obj OPTIONAL do tipo USER, coloquei objeto como nome da variável
		Optional<Product> objeto = repository.findById(id);
		// a operação get do OPTIONAL retorna o obj tipo Product que estiver dentro do optional
		return objeto.get();
	}
	
}
