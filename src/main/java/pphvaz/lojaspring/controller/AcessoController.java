package pphvaz.lojaspring.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pphvaz.lojaspring.model.Acesso;
import pphvaz.lojaspring.service.AcessoService;


@RestController
@RequestMapping("/acesso")
public class AcessoController {

	@Autowired
	private AcessoService acessoServ;

	
	// GET ACESSO BY ID
	@GetMapping("/{idRequisitado}")
	private ResponseEntity<Acesso> findById(@PathVariable Long idRequisitado) {
		Acesso acesso = acessoServ.findById(idRequisitado);
		return ResponseEntity.ok(acesso);
	}
	
	
	// CRIAR ACESSO NO DATABASE
	@PostMapping
	private ResponseEntity<Void> salvarAcesso(@RequestBody Acesso acesso, UriComponentsBuilder ucb) { 
		
		Acesso acessoSalvo = acessoServ.save(acesso);
		
		URI localizacaoDoNovoAcesso = ucb
				.path("/acesso/{id}")
				.buildAndExpand(acessoSalvo.getId())
				.toUri();
		return ResponseEntity.created(localizacaoDoNovoAcesso).build();
	}
}
