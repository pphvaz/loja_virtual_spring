package pphvaz.lojaspring;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.TestCase;
import pphvaz.lojaspring.controller.PessoaController;
import pphvaz.lojaspring.exceptions.CustomExceptions;
import pphvaz.lojaspring.model.PessoaJuridica;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = LojaVirtualSpringApplication.class)
public class TestePessoaUsuario extends TestCase {
	
	@Autowired
	private PessoaController pessoaController;
	
	
	@Test
	public void testeCadastroPessoaJuridica() throws CustomExceptions {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setCnpj(RandomStringUtils.random(15, true, true));
		pessoaJuridica.setNome("Magazine Luiza");
		pessoaJuridica.setEmail("magalu@gmail.com");
		pessoaJuridica.setTelefone("(12)99203-1234");
		pessoaJuridica.setInscEstadual("INSC.SP124");
		pessoaJuridica.setInscMunicipal("INSCJSC124");
		pessoaJuridica.setNomeFantasia("Magazine Luiza");
		pessoaJuridica.setRazaoSocial("1.000.000,00");
		
		pessoaController.salvarPessoaJuridica(pessoaJuridica);
		
		
	}

}
