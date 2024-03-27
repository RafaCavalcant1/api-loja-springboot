package com.educandoweb.cursospring.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// criando a classe de usuário
// o serializable é para fazer com que os objetos da classe possam ser transformados em bytes para serem armazenados ou transmitidos
@Entity
@Table(name= "tb_user") // a tabela user é uma palavra especial no BD h2 então foi neessario renomear para não dar conflito
public class User implements Serializable {

	private static final long serialVersionUID = 1L;  // nnúmero de série padrao 
	
	//indica que o id é a PK do banco de dados 
	@Id 
	// para fazer com que o id fique autoincrementavel 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@JsonIgnore //para não ficar um loop infinito 
	// o muitospara 1 na classe pedido está mapeado  por client
	@OneToMany(mappedBy = "client")
	// um cliente pode ter vários pedidos  então aqui vem uma lista da classe pedidos e já está instanciada
	private List<Order> orders = new ArrayList<Order>();
	
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
	
	public List<Order> getOrders() {
		return orders;
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
