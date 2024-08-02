package pphvaz.lojaspring;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;
import pphvaz.lojaspring.model.Acesso;
import pphvaz.lojaspring.repository.AcessoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = LojaVirtualSpringApplication.class)
class LojaVirtualSpringApplicationTests extends TestCase {
	
	@Autowired
	AcessoRepository acessoRepo;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	private WebApplicationContext wac;
	
	ObjectMapper objMapper = new ObjectMapper();
	
	@Test
	void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_COMPRADOR");
		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.post("/acesso")
				.content(objMapper.writeValueAsString(acesso))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno API: " + retornoApi.andReturn().getResponse().getContentAsString());
		
		Acesso objetoRetorno = objMapper
				.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
		
		assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());

	}
	
	@Test
	void testRestApiDeletarAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setId(1000L);
		acesso.setDescricao("ROLE_COMPRADOR_DELETADO");
		
		acesso = acessoRepo.save(acesso);
		
		ResultActions retornoApi = mockMvc
			.perform(MockMvcRequestBuilders.delete("/acesso/{id}", acesso.getId())
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON));
		
		System.out.println("Retorno API: " + retornoApi.andReturn().getResponse().getContentAsString());
		
		Acesso objetoRetorno = objMapper
				.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
		
		assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
	}
	
	@Test
	void testRestApiListaDeAcessos() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Acesso acesso = new Acesso();
		acesso.setId(1001L);
		acesso.setDescricao("ROLE_COMPRADOR_LISTADO");
		
		acesso = acessoRepo.save(acesso);
		
		ResultActions retornoApi = mockMvc
			.perform(MockMvcRequestBuilders.get("/acesso/buscarPorDescricao/listado")
			.content(objMapper.writeValueAsString(acesso))
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON));
		
		List<Acesso> retornoApiList = objMapper
				.readValue(retornoApi
							.andReturn()
							.getResponse()
							.getContentAsString(),
						new TypeReference<List<Acesso>>() {});
		
		assertEquals(1, retornoApiList.size());
		assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());
		
		acessoRepo.deleteById(acesso.getId());
	}
	
	
	/*
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
