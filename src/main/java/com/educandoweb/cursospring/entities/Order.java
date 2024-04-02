package com.educandoweb.cursospring.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.cursospring.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	
	@OneToMany(mappedBy = "id.order") // um para muitos, coloca id.order pois no OrderItem eu tenho o ID e o ID por sua vez que tem o pedido
	 // Ignorar a serialização da coleção items na classe OrderItem
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // NO CADO DO 1 P UM está sendo mapeada as duas entidades para ter o mesmo ID
	private Payment payment;  //um pedido tem um pagamento
	
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

	public Set<OrderItem> getItems(){
		return items;
	}	
	
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem x : items) {  // varre os itens e para todo item adiciona o valor na soma.
			sum += x.getSubTotal();
		}
		return sum;
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", items=" + items + "]";
	}
	
	
	
	
}
