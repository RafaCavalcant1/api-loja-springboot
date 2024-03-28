package com.educandoweb.cursospring.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.cursospring.entities.Order;
import com.educandoweb.cursospring.entities.User;
import com.educandoweb.cursospring.entities.enums.OrderStatus;
import com.educandoweb.cursospring.repositories.OrderRepository;
import com.educandoweb.cursospring.repositories.UserRepository;

// uma classe especifica de configuração para o perfil de teste
//para executar quando o programa for iniciado usa o implements CommandLineRunner
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	//para popular o BD com alguns obj, mas precisa salc=var os dados na class repository
	
	@Autowired // para associar uma instancia do userRepository no testeconfig
	private UserRepository userRepository;
	
	@Autowired // para associar uma instancia do OrderRepository no testeconfig
	private OrderRepository orderRepository;

	// tudo que colocar dentro desse método run(ele veio do implements) vai ser executado
	@Override
	public void run(String... args) throws Exception {
		//id está null pois vai ser gerado pelo BD
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//instanciando obj na classe order
		// essa string do instant está no formato iso 0681
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING_PAGAMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WAITING_PAGAMENT, u1);
		
		//salvando no BD, criei uma lita para salvar os dois objetos 
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}

	
	
}
