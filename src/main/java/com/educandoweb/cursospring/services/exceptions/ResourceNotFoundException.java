package com.educandoweb.cursospring.services.exceptions;

// classe para tratar as exceções
//recurso não encontrado 404
public class ResourceNotFoundException extends RuntimeException {// RuntimeException exceção que o compilador não te obriga a tratar

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) { // ou seja, vou passar o id do objeto que eu tentei encontrar e eu não encontrei e h=gera um recurso não encontrado
		super("Resource not found. Id " + id);  // essa vai ser a mensagem de erro que vai dar
	}

}
