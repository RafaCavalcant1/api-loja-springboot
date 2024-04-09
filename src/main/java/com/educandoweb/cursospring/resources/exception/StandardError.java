package com.educandoweb.cursospring.resources.exception;

import java.io.Serializable;
import java.time.Instant;

//para fazer um tratamento manual das exceções com os campos tipo em json que mosra quando gera um erro 
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private Integer status;
	private String messageUser;
	private String message;
	private String path;
	
	public StandardError(){	
	}

	public StandardError(Instant timestamp, Integer status, String messageUser, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.messageUser = messageUser;
		this.message = message;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessageUser() {
		return messageUser;
	}

	public void setMessageUser(String messageUser) {
		this.messageUser = messageUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	// não precisa de hashcode pq ão interessa comparar um erro com o outro
	
}
