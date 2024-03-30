package com.educandoweb.cursospring.entities;
//um produto pode ter varias categorias e uma categoria pode ter varios produtos 
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_category")
// classe categoria
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;  // nnúmero de série padrao 
	
	@Id
	// para fazer com que o id fique autoincrementavel 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	// o set é para garantir que eu não vou ter um produto com mais de uma ocorrencia na mesma categoria (o msm produto não pode ter uma mesma categoria mais de uma vez)
	// instanciando para garantir que a coleção não comece nula, e sim, vazia 
	//hashSet pq o set é uma interface e não poode ser instanciado, tem que usar uma classe corresondente a essa interface
	@JsonIgnore // para não ficar o loop infinito no postman
	@ManyToMany(mappedBy = "categories") // referencia ao mapeamento que está na classe de produtos
	private Set<Product> products = new HashSet<>();
	
	public Category() {
	}
	
	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	public Set<Product> getProducts() {
		return products;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}	
	
}
