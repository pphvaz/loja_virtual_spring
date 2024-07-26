package pphvaz.lojaspring;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import junit.framework.TestCase;
import pphvaz.lojaspring.model.Acesso;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = LojaVirtualSpringApplication.class)
class LojaVirtualSpringApplicationTests extends TestCase {
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	@DirtiesContext
	void deveCriarUmAcesso() {
		
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
		
		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
		Integer id = documentContext.read("$.id");
		String descricao = documentContext.read("$.descricao");
		
		assertThat(id).isNotNull();
		assertThat(descricao).isEqualTo(acesso.getDescricao());
		
		// DELETAR ACESSO CRIADO
		
		ResponseEntity<Void> response = restTemplate
			.exchange(localizacaoNovoAcesso, HttpMethod.DELETE, null, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
			
		// Make sure it was deleted
		ResponseEntity<String> getAnotherResponse = restTemplate
			.getForEntity(localizacaoNovoAcesso, String.class);
		assertThat(getAnotherResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	/*
	@Test
	void deveRetornarUmAcessoPorId() {
		ResponseEntity<String> response = restTemplate
			.getForEntity("/acesso/2", String.class);
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		
		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Integer id = documentContext.read("$.id");
		assertThat(id).isEqualTo(1);

		String descricao = documentContext.read("$.descricao");
		assertThat(descricao).isEqualTo("ROLE_ADMIN"); 
		
	}
	@Test
	void deveDeletarUmAcessoPorId() {
		
	
	}
	*/
	

}
