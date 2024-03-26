package com.educandoweb.cursospring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.cursospring.entities.User;
import com.educandoweb.cursospring.repositories.UserRepository;

// uma classe especifica de configuração para o perfil de teste
//para executar quando o programa for iniciado usa o implements CommandLineRunner
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	//para popular o BD com alguns obj, mas precisa salc=var os dados na class repository
	
	@Autowired // para associar uma instancia do userRepository no testeconfig
	private UserRepository userRepository;

	// tudo que colocar dentro desse método run(ele veio do implements) vai ser executado
	@Override
	public void run(String... args) throws Exception {
		//id está null pois vai ser gerado pelo BD
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//salvando no BD, criei uma lita para salvar os dois objetos 
		userRepository.saveAll(Arrays.asList(u1, u2));
	}

	
	
}
