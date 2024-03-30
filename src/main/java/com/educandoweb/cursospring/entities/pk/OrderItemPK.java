package com.educandoweb.cursospring.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.cursospring.entities.Order;
import com.educandoweb.cursospring.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//não tem uma chave primaria própria, essa classe é o intermedio entre proditos e pedidos, pois necessita de uma qunatidade e preço
@Embeddable
public class OrderItemPK implements Serializable {

	private static final long serialVersionUID = 1L;  // nnúmero de série padrao 
	// ela vai ter uma referencia para o produto e uma referencia para o pedido
	
	@ManyToOne// relacionamento muitos para 1
	@JoinColumn(name = "order_id") // nome da chave estrangeira 
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")// nome da chave estrangeira 
	private Product product;
	
	// não tem construtores, apenas get e set
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	
	
	
	

}
