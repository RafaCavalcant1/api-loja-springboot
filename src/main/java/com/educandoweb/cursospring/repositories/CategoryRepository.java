package com.educandoweb.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.cursospring.entities.Category;

//os repositorios vão ser interfaces pois o JPA repository é interface
//é passado o tipo(Category) e o id (LONG)
//isso é capaz de instanciar um objeto category que vai ter varias operações que pode trabalhar com o usuário
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
