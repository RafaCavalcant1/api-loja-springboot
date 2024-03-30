package com.educandoweb.cursospring.entities;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.cursospring.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;  // nnúmero de série padrao 
	
	@EmbeddedId // pois é um idcomposto
	private OrderItemPK id = new OrderItemPK(); //um id composto tem q ser instanciado 
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}

	// passando aqui os dois iatributos que estão na classe ORDERITEMPK
	public OrderItem(Order order, Product product,Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) { // infromando um pedido e o método vai no ID e joga o pedido la dentro 
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {// infromando um produti e o método vai no ID e joga o produto la dentro 
		id.setProduct(product);
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
