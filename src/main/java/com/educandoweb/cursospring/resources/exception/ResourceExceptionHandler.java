package com.educandoweb.cursospring.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.cursospring.services.exceptions.DatabaseException;
import com.educandoweb.cursospring.services.exceptions.DuplicateEmailException;
import com.educandoweb.cursospring.services.exceptions.InvalidEmailFormatException;
import com.educandoweb.cursospring.services.exceptions.InvalidPasswordLengthException;
import com.educandoweb.cursospring.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // ele que vai intercptar as exceções que acotecerem para que o objeto possa executar o tratamento
//nessa classe que vamos dar o tratamento manual aos erros
public class ResourceExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)//apara que o metodo seja capaz de interseptar a requisição que deu exceção para ele cair aqui
	//o tipo do obj de resposta vai ser o standardError, o nome do metodo é resourceNotFound e ele tem que receber como argumento  a exceção personalizada e depois o objeto HttpServletRequest(representa uma solicitação HTTP)
	public ResponseEntity<StandardError>resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String messageUser = "Usuário de id :não encontrado"; // mensagem para o usuário 
		HttpStatus status = HttpStatus.NOT_FOUND;// resposta 404 que é o Not fouad
		//instanciando um obj StandardError e colocar os campos, o instante atual, o status , o erro , a menssagem que veio na exceção que ta no parametro e o caminho é o request que tb está no parametro
		StandardError err = new StandardError(Instant.now(),status.value(), messageUser, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err); // o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
	}
	
	@ExceptionHandler(DatabaseException.class)//apara que o metodo seja capaz de interseptar a requisição que deu exceção para ele cair aqui
	//o tipo do obj de resposta vai ser o standardError, o nome do metodo é database e ele tem que receber como argumento  a exceção personalizada e depois o objeto HttpServletRequest(representa uma solicitação HTTP)
	public ResponseEntity<StandardError>database(DatabaseException e, HttpServletRequest request){
		String messageUser = "Não é possível excluir o usuário de(id tal), pois ele possui dependências"; // mensagem para o usuário 
		HttpStatus status = HttpStatus.BAD_REQUEST;// resposta 400 que é o Bad request
		//instanciando um obj StandardError e colocar os campos, o instante atual, o status , o erro , a menssagem que veio na exceção que ta no parametro e o caminho é o request que tb está no parametro
		StandardError err = new StandardError(Instant.now(),status.value(), messageUser, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err); // o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
	}
	

//	@ExceptionHandler(InvalidEmailFormatException.class)//apara que o metodo seja capaz de interseptar a requisição que deu exceção para ele cair aqui
//	//o tipo do obj de resposta vai ser o standardError, o nome do metodo é database e ele tem que receber como argumento  a exceção personalizada e depois o objeto HttpServletRequest(representa uma solicitação HTTP)
//	public ResponseEntity<StandardError>invalidEmail(InvalidEmailFormatException e, HttpServletRequest request){
//		String messageUser = "O email fornecido é inválido"; // mensagem para o usuário 
//		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;// resposta 400 que é o Bad request
//		//instanciando um obj StandardError e colocar os campos, o instante atual, o status , o erro , a menssagem que veio na exceção que ta no parametro e o caminho é o request que tb está no parametro
//		StandardError err = new StandardError(Instant.now(),status.value(), messageUser, e.getMessage(), request.getRequestURI());
//		return ResponseEntity.status(status).body(err); // o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
//	}
//	
//	@ExceptionHandler(InvalidPasswordLengthException.class)//apara que o metodo seja capaz de interseptar a requisição que deu exceção para ele cair aqui
//	//o tipo do obj de resposta vai ser o standardError, o nome do metodo é database e ele tem que receber como argumento  a exceção personalizada e depois o objeto HttpServletRequest(representa uma solicitação HTTP)
//	public ResponseEntity<StandardError>invalidPassword(InvalidPasswordLengthException e, HttpServletRequest request){
//		String messageUser = "A senha deve conter no mínimo 8 caracteres"; // mensagem para o usuário 
//		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;// resposta 400 que é o Bad request
//		//instanciando um obj StandardError e colocar os campos, o instante atual, o status , o erro , a menssagem que veio na exceção que ta no parametro e o caminho é o request que tb está no parametro
//		StandardError err = new StandardError(Instant.now(),status.value(), messageUser, e.getMessage(), request.getRequestURI());
//		return ResponseEntity.status(status).body(err); // o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
//	}
//	
//	@ExceptionHandler(DuplicateEmailException.class)//apara que o metodo seja capaz de interseptar a requisição que deu exceção para ele cair aqui
//	//o tipo do obj de resposta vai ser o standardError, o nome do metodo é database e ele tem que receber como argumento  a exceção personalizada e depois o objeto HttpServletRequest(representa uma solicitação HTTP)
//	public ResponseEntity<StandardError>duplicateEmail(DuplicateEmailException e, HttpServletRequest request){
//		String messageUser = "O email já está cadastrado"; // mensagem para o usuário 
//		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;// resposta 400 que é o Bad request
//		//instanciando um obj StandardError e colocar os campos, o instante atual, o status , o erro , a menssagem que veio na exceção que ta no parametro e o caminho é o request que tb está no parametro
//		StandardError err = new StandardError(Instant.now(),status.value(), messageUser, e.getMessage(), request.getRequestURI());
//		return ResponseEntity.status(status).body(err); // o metodo vai retornar um ResponseEntity .status é p vim com o codigo personalizado
//	}
//	
	// passei todas as classes de exceção
	@ExceptionHandler({ InvalidEmailFormatException.class, InvalidPasswordLengthException.class, DuplicateEmailException.class })
	public ResponseEntity<StandardError> handleOnlyException(Exception e, HttpServletRequest request) { // método handleCustomException que recebe Exception e (exceção lançada), e HttpServletRequest request, que representa a requisição HTTP.
	    String messageUser = e.getMessage();
	    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // o status sempre vai ser esse, 422

	    StandardError err = new StandardError(Instant.now(), status.value(), messageUser, e.getMessage(), request.getRequestURI());
	    return ResponseEntity.status(status).body(err);
	}
}
