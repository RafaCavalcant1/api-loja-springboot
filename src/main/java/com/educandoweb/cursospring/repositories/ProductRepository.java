package com.educandoweb.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.cursospring.entities.Product;

//os repositorios vão ser interfaces pois o JPA repository é interface
//é passado o tipo(Product) e o id (LONG)
//isso é capaz de instanciar um objeto category que vai ter varias operações que pode trabalhar com o usuário
public interface ProductRepository extends JpaRepository<Product, Long> {

}
