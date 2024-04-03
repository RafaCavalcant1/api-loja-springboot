package com.educandoweb.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educandoweb.cursospring.entities.User;
import com.educandoweb.cursospring.repositories.UserRepository;
import com.educandoweb.cursospring.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //registra a classe como um componente do spring e permite que ele seja injetado 
public class UserService {

	//para que o spring faça a injeção de dependencia de forma transparente 
	@Autowired
	private UserRepository repository;
	
	//método para retornar todos os usuários do BD 
	public List<User> findAll(){
		return repository.findAll();
	}
	
	//buscar o usuário pelo id
	public User findById(Long id) {
		//cha o repositorio pelo ID mas essa operação retorna um obj OPTIONAL do tipo USER, coloquei objeto como nome da variável
		Optional<User> objeto = repository.findById(id);
		//o orElseTrhrow ele tenta da o get, se n tiver usiario ele lça a exceção
		return objeto.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	//retorna o usuario salvo 
	public User insert(User obj) {
		return repository.save(obj);  // o save ele por padrão d=ja retona o obj salvo 
	}
	
	// deletar um usuario
	public void delete(Long id) {
		try {
		repository.deleteById(id); // vai deletar o usuario por meio do ID
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	//atualizar um dado do usuario
	@Transactional//para garantir que as operações sejam executadas dentro de uma transação
	public User update(Long id, User obj) { // recebe o id e o novo obj
		try {
			User entity = repository.getReferenceById(id); // getReferenceById(id) prepara o obj monitorado para mexer e dps fazer uma operação com o BD
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {  // primeiro eu vi com o RuntimeException qual exceção especifica gerou, que foi EntityNotFoundException
			throw new ResourceNotFoundException(id);
		}
	}

	// atualizar os dados do entity do que chegou com o obj 
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
		// não vai atualizar o id nem a senha 
	}
	
}
