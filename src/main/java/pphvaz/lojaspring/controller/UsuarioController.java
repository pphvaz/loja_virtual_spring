package pphvaz.lojaspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pphvaz.lojaspring.exceptions.CustomExceptions;
import pphvaz.lojaspring.model.Usuario;
import pphvaz.lojaspring.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioServ;
	
	@PostMapping(value = "**/salvar-usuario")
	private ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) throws CustomExceptions {
		
		if (usuario == null) {
			throw new CustomExceptions("Usuario não pode ser nulo.");
		}
		
		if (usuario.getLogin() != null) {
			throw new CustomExceptions("Login já cadastrado.");
		}
		
		usuarioServ.save(usuario);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
}