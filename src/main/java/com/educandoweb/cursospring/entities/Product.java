package com.educandoweb.cursospring.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_product")
// classe produto
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;  // nnúmero de série padrao 
	
	@Id
	// para fazer com que o id fique autoincrementavel 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private double price;
	private String imgUrl;
	
	// o set é para garantir que eu não vou ter um produto com mais de uma ocorrencia na mesma categoria (o msm produto não pode ter uma mesma categoria mais de uma vez)
	// instanciando para garantir que a coleção não comece nula, e sim, vazia 
	//hashSet pq o set é uma interface e não poode ser instanciado, tem que usar uma classe corresondente a essa interface
	@Transient
	private Set<Category> categories = new HashSet<>();
	
	public Product() {
	}

	// não coloca coleções em cinstrutor , no caso private Set<Category> categories = new HashSet<>();
	public Product(Long id, String name, String description, double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
