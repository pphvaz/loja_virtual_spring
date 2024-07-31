package pphvaz.lojaspring.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pphvaz.lojaspring.exceptions.CustomExceptions;
import pphvaz.lojaspring.model.Acesso;
import pphvaz.lojaspring.service.AcessoService;

@RestController
@RequestMapping("/acesso")
public class AcessoController {

	@Autowired
	private AcessoService acessoServ;

	// GET ACESSO BY ID
	@GetMapping("**/{idRequisitado}")
	private ResponseEntity<Acesso> findById(@PathVariable Long idRequisitado) {
		Acesso acesso = acessoServ.findById(idRequisitado);

		if (acesso != null) {
			return ResponseEntity.ok(acesso);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// GET ALL
	@GetMapping
	private ResponseEntity<List<Acesso>> findAll() {
		
		List<Acesso> acessos = acessoServ.findAll();
		
		return ResponseEntity.ok(acessos);
	}

	// BUSCAR ACESSO POR DESCRICAO
	@GetMapping("**/buscarPorDescricao/{descricao}")
	private ResponseEntity<List<Acesso>> buscarPorDescricao(@PathVariable String descricao) {

		List<Acesso> acessos = acessoServ.buscarAcessoPorDesc(descricao);
		return ResponseEntity.ok(acessos);

	}

	// CRIAR ACESSO NO DATABASE
	@PostMapping
	private ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso, UriComponentsBuilder ucb) throws CustomExceptions {

		if (acesso.getId() == null) {
			List<Acesso> acessos = acessoServ.buscarAcessoPorDesc(acesso.getDescricao());
			
			if (!acessos.isEmpty()) {
				throw new CustomExceptions("Acesso já cadastrado: " + acesso.getDescricao());
			}
		}

		Acesso acessoSalvo = acessoServ.save(acesso);

		URI localizacaoDoNovoAcesso = ucb.path("/acesso/{id}").buildAndExpand(acessoSalvo.getId()).toUri();
		return ResponseEntity.created(localizacaoDoNovoAcesso).body(acessoSalvo);
	}

	// DELETAR ACESSO NO DATABASE
	@DeleteMapping("/{id}")
	private ResponseEntity<Acesso> deletarAcesso(@PathVariable Long id) {

		Acesso acesso = acessoServ.findById(id);

		if (acessoServ.findById(id) != null) {
			acessoServ.deleteById(id);
			return ResponseEntity.ok(acesso);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
