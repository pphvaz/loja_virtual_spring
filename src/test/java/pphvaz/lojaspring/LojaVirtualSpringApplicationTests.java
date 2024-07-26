package pphvaz.lojaspring;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import junit.framework.TestCase;
import pphvaz.lojaspring.model.Acesso;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = LojaVirtualSpringApplication.class)
class LojaVirtualSpringApplicationTests extends TestCase {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	@DirtiesContext
	void OperacoesCrudAcesso() { 
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN");
		
		
		// CREATE NEW ACESSO
		ResponseEntity<Void> createResponse = restTemplate
				.postForEntity("/acesso", acesso, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		// GET NEW ACESSO
		URI localizacaoNovoAcesso = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate
				.getForEntity(localizacaoNovoAcesso, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		// DELETE NEW ACESSO
		
	}

}
