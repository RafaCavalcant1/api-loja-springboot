package com.educandoweb.cursospring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.educandoweb.cursospring.entities.Order;

// os repositorios vão ser interfaces pois o JPA repository é interface
// é passado o tipo(Order) e o id (LONG)
// isso é capaz de instanciar um objeto repository que vai ter varias operações que pode trabalhar com o usuário
public interface OrderRepository extends JpaRepository<Order, Long>{

	// nesse caso específico não precisa criar a implementação da interface pois o o JPA tem uma implementação padrão para essa interface
}
