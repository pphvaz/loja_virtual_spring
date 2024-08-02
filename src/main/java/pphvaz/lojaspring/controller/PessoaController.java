package pphvaz.lojaspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pphvaz.lojaspring.exceptions.CustomExceptions;
import pphvaz.lojaspring.model.PessoaJuridica;
import pphvaz.lojaspring.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaServ;

	@PostMapping(value = "**/salvar-pessoa-juridica")
	public ResponseEntity<PessoaJuridica> salvarPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica)
			throws CustomExceptions {

		if (pessoaJuridica == null) {
			throw new CustomExceptions("Pessoa juridica não pode ser nulo.");
		}

		if (pessoaJuridica.getId() == null && pessoaServ.findByCnpj(pessoaJuridica.getCnpj()) != null) {
			throw new CustomExceptions("CNPJ já cadastrado.");
		}

		pessoaServ.salvarPessoaJuridica(pessoaJuridica);

		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
	}

}
