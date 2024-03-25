package com.educandoweb.cursospring.entities;

import java.io.Serializable;
import java.util.Objects;

// criandi a classe de usuário
// o serializable é para fazer com que os objetos da classe possam ser transformados em bytes para serem armazenados ou transmitidos
public class User implements Serializable {

	private static final long serialVersionUID = 1L;  // nnúmero de série padrao 
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	//construtor vazio pois está sendo usado um framework
	public User() {	
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// implementar a comparação e o hashcode de objetos
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
