package com.educandoweb.cursospring.services.exceptions;

public class InvalidEmailFormatException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidEmailFormatException(String msg) {
		super(msg);
	}

}
