package com.educandoweb.cursospring.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.cursospring.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // ele que vai intercptar as exceções que acotecerem para que o objeto possa executar o tratamento
//nessa classe que vamos dar o tratamento manual aos erros
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)//apara que o metodo seja capaz de interseptar a requisição que deu exceção para ele cair aqui
	//o tipo do obj de resposta vai ser o standardError, o nome do metodo é resourceNotFound e ele tem que receber como argumento  a exceção personalizada e depois o objeto HttpServletRequest(representa uma solicitação HTTP)
	public ResponseEntity<StandardError>resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found"; // mensagem para o usuário 
		HttpStatus status = HttpStatus.NOT_FOUND;// resposta 404 que é o Not fouad
		//instanciando um obj StandardError e colocar os campos, o instante atual, o status , o erro , a menssagem que veio na exceção que ta no parametro e o caminho é o request que tb está no parametro
		StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err); // o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
	}
}
