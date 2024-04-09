package com.educandoweb.cursospring.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educandoweb.cursospring.entities.User;
import com.educandoweb.cursospring.repositories.UserRepository;
import com.educandoweb.cursospring.services.exceptions.DatabaseException;
import com.educandoweb.cursospring.services.exceptions.DuplicateEmailException;
import com.educandoweb.cursospring.services.exceptions.InvalidEmailFormatException;
import com.educandoweb.cursospring.services.exceptions.InvalidPasswordLengthException;
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
		//acha o repositorio pelo ID mas essa operação retorna um obj OPTIONAL do tipo USER, coloquei objeto como nome da variável
		Optional<User> objeto = repository.findById(id);
		//o orElseTrhrow ele tenta da o get, se n tiver usiario ele lça a exceção
		return objeto.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	//retorna o usuario salvo 
	public User insert(User obj) {
		// parte de verificar se o email é valido usando expressão regular 
		 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	     Pattern pattern = Pattern.compile(emailRegex); // criando um padrão para o regex
	     Matcher matcher = pattern.matcher(obj.getEmail()); // a partir desse padrão validar 

	     // se n for, gera esse erro 
	     if (!matcher.matches()) {// ta vendo se de fato o match
	    	 throw new InvalidEmailFormatException("Invalid e-mail format: " + obj.getEmail());
         }
	     
		// se o email ja exixtir, lance essa exceção 
		if (repository.existsByEmail(obj.getEmail())) {
            throw new DuplicateEmailException("E-mail already exists: " + obj.getEmail());
        }
		
		// verifica se a quantidade de caracteres da senha é diferente de 8, se for, gera uma exceção 
		if (obj.getPassword().length() != 8) {
            throw new InvalidPasswordLengthException("Password must have 8 characters.");
        }
		
		return repository.save(obj);  // o save ele por padrão ja retona o obj salvo 	
			
	}
	

	// deletar um usuario
	public void delete(Long id) {
		// vai ver se existe 
		if (!repository.existsById(id)) {
	        throw new ResourceNotFoundException(id); // gera o erro caso n exista
	    }
	    try { // verificar se tem algum pedido com ele 
	        repository.deleteById(id);
	    } catch (DataIntegrityViolationException e) {
	        throw new DatabaseException(e.getMessage());
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
		// se o email for diferente de nulo 
		if (obj.getEmail() != null) {
	        entity.setEmail(obj.getEmail()); // atualiza o email 
	    } 
	    // se o telef for diferente de nulo 
	    if (obj.getPhone() != null ) {
	        entity.setPhone(obj.getPhone()); // atualiza o telefone
	    }
	    if(obj.getName() != null) {
	    	entity.setName(obj.getName());
	    }
	    
		// não vai atualizar o id nem a senha 
	}
	
}
