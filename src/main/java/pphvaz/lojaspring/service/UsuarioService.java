package pphvaz.lojaspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pphvaz.lojaspring.model.Usuario;
import pphvaz.lojaspring.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	public Usuario save(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	public Usuario findById(Long idRequisitado) {
		return usuarioRepo.findById(idRequisitado).orElse(null);
	}

	public void deleteById(Long id) {
		usuarioRepo.deleteById(id);
	}

	public Iterable<Usuario> findAll() {
		return usuarioRepo.findAll();
	}
}