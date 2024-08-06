package pphvaz.lojaspring;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.TestCase;
import pphvaz.lojaspring.controller.PessoaController;
import pphvaz.lojaspring.enums.TipoEndereco;
import pphvaz.lojaspring.exceptions.CustomExceptions;
import pphvaz.lojaspring.model.Endereco;
import pphvaz.lojaspring.model.PessoaFisica;
import pphvaz.lojaspring.model.PessoaJuridica;
import pphvaz.lojaspring.service.PessoaService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LojaVirtualSpringApplication.class)
public class TestePessoaUsuario extends TestCase {

	@Autowired
	private PessoaController pessoaController;
	
	@Autowired
	private PessoaService pessoaServ;

	@Test
	public void testeCadastroPessoaJuridica() throws CustomExceptions {

		String random = RandomStringUtils.random(4, true, true);

		PessoaJuridica pessoaJuridica = new PessoaJuridica();

		pessoaJuridica.setCnpj(random);
		pessoaJuridica.setNome("Loja " + random);
		pessoaJuridica.setEmail(random + "mu@gmail.com");
		pessoaJuridica.setTelefone("(12)99203-1234");
		pessoaJuridica.setInscEstadual("INSC.SP124");
		pessoaJuridica.setInscMunicipal("INSCJSC124");
		pessoaJuridica.setNomeFantasia("Magazine Luiza");
		pessoaJuridica.setRazaoSocial("1.000.000,00");
		pessoaJuridica.setTipoPessoa("Juridica");
		pessoaJuridica.setCategoria("TI");

		Endereco endereco1 = new Endereco();

		endereco1.setBairro("Bairro 2");
		endereco1.setCidade("Cidade 2");
		endereco1.setCep("CEP 2");
		endereco1.setComplemento("Complemento 2");
		endereco1.setEmpresa(pessoaJuridica);
		endereco1.setNumero("23");
		endereco1.setPessoa(pessoaJuridica);
		endereco1.setRuaLogra("Rua Aleatoria 2");
		endereco1.setTipoEndereco(TipoEndereco.COMERCIAL);
		endereco1.setUf("SP");

		Endereco endereco2 = new Endereco();

		endereco2.setBairro("Bairro");
		endereco2.setCidade("Cidade");
		endereco2.setCep("CEP");
		endereco2.setComplemento("Complemento");
		endereco2.setEmpresa(pessoaJuridica);
		endereco2.setNumero("23");
		endereco2.setPessoa(pessoaJuridica);
		endereco2.setRuaLogra("Rua Aleatoria");
		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco2.setUf("SP");
		
		pessoaJuridica.getEnderecos().add(endereco2);
		pessoaJuridica.getEnderecos().add(endereco1);

		pessoaJuridica = pessoaController.salvarPessoaJuridica(pessoaJuridica).getBody();
		
		assertEquals(true, pessoaJuridica.getId() > 0);
		
		for (Endereco endereco : pessoaJuridica.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}
		
		assertEquals(2, pessoaJuridica.getEnderecos().size());

	}
	
	@Test
	public void testeCadastroPessoaFisica() throws CustomExceptions {

		PessoaJuridica pj = pessoaServ.findByCnpj("124.1324.1234/0000");
		
		String random = RandomStringUtils.random(3, true, true);

		PessoaFisica pf = new PessoaFisica();

		pf.setCpf(random + "568-93");
		pf.setNome("Loja " + random);
		pf.setEmail("pedrohvs.alves@gmail.com");
		pf.setTelefone("(12)99203-1234");
		pf.setTipoPessoa("Fisica");
		pf.setEmpresa(pj);

		Endereco endereco1 = new Endereco();

		endereco1.setBairro("Bairro 2");
		endereco1.setCidade("Cidade 2");
		endereco1.setCep("CEP 2");
		endereco1.setComplemento("Complemento 2");
		endereco1.setNumero("23");
		endereco1.setPessoa(pf);
		endereco1.setRuaLogra("Rua Aleatoria 2");
		endereco1.setTipoEndereco(TipoEndereco.COMERCIAL);
		endereco1.setUf("SP");
		endereco1.setEmpresa(pj);

		Endereco endereco2 = new Endereco();

		endereco2.setBairro("Bairro");
		endereco2.setCidade("Cidade");
		endereco2.setCep("CEP");
		endereco2.setComplemento("Complemento");
		endereco2.setNumero("23");
		endereco2.setPessoa(pf);
		endereco2.setRuaLogra("Rua Aleatoria");
		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco2.setUf("RJ");
		endereco2.setEmpresa(pj);
		
		pf.getEnderecos().add(endereco2);
		pf.getEnderecos().add(endereco1);

		pf = pessoaController.salvarPessoaFisica(pf).getBody();
		
		assertEquals(true, pf.getId() > 0);
		
		for (Endereco endereco : pf.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}
		
		assertEquals(2, pf.getEnderecos().size());

	}
	

}
