package com.educandoweb.cursospring.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.cursospring.entities.Category;
import com.educandoweb.cursospring.entities.Order;
import com.educandoweb.cursospring.entities.Product;
import com.educandoweb.cursospring.entities.User;
import com.educandoweb.cursospring.entities.enums.OrderStatus;
import com.educandoweb.cursospring.repositories.CategoryRepository;
import com.educandoweb.cursospring.repositories.OrderRepository;
import com.educandoweb.cursospring.repositories.ProductRepository;
import com.educandoweb.cursospring.repositories.UserRepository;

// uma classe especifica de configuração para o perfil de teste
//para executar quando o programa for iniciado usa o implements CommandLineRunner
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	//para popular o BD com alguns obj, mas precisa salvar os dados na class repository
	
	@Autowired // para associar uma instancia do userRepository no testeconfig e usar no BD
	private UserRepository userRepository;
	
	@Autowired // para associar uma instancia do OrderRepository no testeconfig e usar no BD
	private OrderRepository orderRepository;
	
	@Autowired // para associar uma instancia do CategoryRepository no testeconfig e usar no BD
	private CategoryRepository categoryRepository;

	@Autowired // para associar uma instancia do ProductRepository no testeconfig e usar no BD
	private ProductRepository productRepository;
	
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
		
		//instanciando obj na classe Category
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers"); 
		
		//instanciando obj na classe product
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
	
		//salvando no BD, criei uma lita para salvar os dois objetos 
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		//falando qual categoria  é de cada produto 
		p1.getCategories().add(cat2);// o produto P1 dentro da coleção de categoria está o obj cat2
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		// agora precisa salvar essas associações de produto na sua categoria
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
	}

	
	
}
