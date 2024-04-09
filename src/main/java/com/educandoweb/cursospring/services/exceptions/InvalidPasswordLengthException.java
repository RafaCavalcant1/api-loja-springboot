package com.educandoweb.cursospring.services.exceptions;

public class InvalidPasswordLengthException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidPasswordLengthException(String msg) {
		super(msg);
	}

}
