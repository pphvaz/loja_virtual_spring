package pphvaz.lojaspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pphvaz.lojaspring.exceptions.CustomExceptions;
import pphvaz.lojaspring.model.PessoaFisica;
import pphvaz.lojaspring.model.PessoaJuridica;
import pphvaz.lojaspring.service.PessoaService;
import pphvaz.lojaspring.util.ValidaCNPJ;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaServ;

	// ********** PESSOA JURIDICA **********
	
	@PostMapping(value = "**/salvar-pessoa-juridica")
	public ResponseEntity<PessoaJuridica> salvarPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica)
			throws CustomExceptions {

		if (pessoaJuridica == null) {
			throw new CustomExceptions("Pessoa juridica não pode ser nulo.");
		}
		
		if (!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
			throw new CustomExceptions("CNPJ inválido.");
		}

		if (pessoaJuridica.getId() == null && pessoaServ.findByCnpj(pessoaJuridica.getCnpj()) != null && pessoaServ.findByInscEstadual(pessoaJuridica.getInscEstadual()) != null) {
			throw new CustomExceptions("CNPJ ou Inscrição Estadual já cadastrada.");
		}

		pessoaServ.salvarPessoaJuridica(pessoaJuridica);

		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
	}
	
	// ********** PESSOA FISICA **********

	
	@PostMapping(value = "**/salvar-pessoa-fisica")
	public ResponseEntity<PessoaFisica> salvarPessoaFisica(@RequestBody PessoaFisica pf)
			throws CustomExceptions {

		if (pf == null) {
			throw new CustomExceptions("Pessoa fisica não pode ser nulo.");
		}
		
		//if (!ValidaCPF.isCPF(pf.getCpf())) {
		//	throw new CustomExceptions("CPF inválido.");
		//}

		if (pf.getId() == null && pessoaServ.findByCpf(pf.getCpf()) != null) {
			throw new CustomExceptions("CPF já cadastrado.");
		}

		pessoaServ.salvarPessoaFisica(pf);

		return new ResponseEntity<PessoaFisica>(pf, HttpStatus.OK);
	}
	
	
}
