package pphvaz.lojaspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pphvaz.lojaspring.controller.AcessoController;
import pphvaz.lojaspring.model.Acesso;

@SpringBootTest(classes = LojaVirtualSpringApplication.class)
class LojaVirtualSpringApplicationTests {
	
	@Autowired
	private AcessoController acessoController;
	
	@Test
	void deveAdicionarUmAcesso() { 
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_ADMIN");
		
		acessoController.salvarAcesso(acesso);
	}

}
