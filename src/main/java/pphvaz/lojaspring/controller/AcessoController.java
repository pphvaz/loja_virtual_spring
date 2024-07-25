package pphvaz.lojaspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import pphvaz.lojaspring.model.Acesso;
import pphvaz.lojaspring.service.AcessoService;

@Controller
public class AcessoController {

	@Autowired
	private AcessoService acessoServ;
	
	@PostMapping("/salvarAcesso")
	public Acesso salvarAcesso(Acesso acesso) {
		
		return acessoServ.save(acesso);
	}
}
