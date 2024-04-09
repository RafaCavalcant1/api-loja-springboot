package com.educandoweb.cursospring.services.exceptions;

public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) { // vai vir a mensagem que está dando no erro 
		super(msg); //para acessar coisas da classe mãe 
	}

}
