package com.educandoweb.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educandoweb.cursospring.entities.User;
import com.educandoweb.cursospring.repositories.UserRepository;

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
		// a operação get do OPTIONAL retorna o obj tipo User que estiver dentro do optional
		return objeto.get();
	}
	
	//retorna o usuario salvo 
	public User insert(User obj) {
		return repository.save(obj);  // o save ele por padrão d=ja retona o obj salvo 
	}
	
	// deletar um usuario
	public void delete(Long id) {
		repository.deleteById(id); // vai deletar o usuario por meio do ID
	}
	
	//atualizar um dado do usuario
	@Transactional
	public User update(Long id, User obj) { // recebe o id e o novo obj
		User entity = repository.getReferenceById(id); // getReferenceById(id) prepara o obj monitorado para mexer e dps fazer uma operação com o BD
		updateData(entity, obj);
		return repository.save(entity);
	}

	// atualizar os dados do entity do que cheb=gou com o obj 
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
		// não vai atualizar o id nem a senha 
	}
	
}
