package pphvaz.lojaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "pphvaz.lojaspring.model")
@ComponentScan(basePackages = {"pphvaz.*"})
@EnableJpaRepositories(basePackages = {"pphvaz.lojaspring.repository"})
@EnableTransactionManagement
public class LojaVirtualSpringApplication {

	public static void main(String[] args) {
		
		//System.out.println(new BCryptPasswordEncoder().encode("123"));
		
		SpringApplication.run(LojaVirtualSpringApplication.class, args);
	}

}