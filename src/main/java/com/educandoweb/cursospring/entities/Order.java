package com.educandoweb.cursospring.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.educandoweb.cursospring.entities.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//class pedido
@Table(name= "tb_order") // a tabela order é uma palavra especial no BD h2 então foi neessario renomear para não dar conflito
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;  // nnúmero de série padrao 

	//indica que o id é a PK do banco de dados 
	@Id 
	// para fazer com que o id fique autoincrementavel 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant moment; // instant é tipo DATE 
	
	// colocando o integer para dizer explicitamente que estou gravando no BD um numero inteiro
	private Integer orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "client_id") // criação da chave estrangeira e seu nome é  "client_id"
	// um pedido vem de um cliente, então puxa a classe cliente para ca
	private User client;

	public Order() {	
	}

	public Order(Long id, Instant moment,OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		// chmando o set pois nele eu coloquei  para virar um inteiro, e aqui tem que estar assim tb
		setOrderStatus(orderStatus);
		this.client = client;	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	// pegando o numero inteiro interno da classe e convertendo em orderStatus 
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
		this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
