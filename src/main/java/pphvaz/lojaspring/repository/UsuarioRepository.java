package pphvaz.lojaspring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pphvaz.lojaspring.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.dataAtualSenha <= current_date - 90")
	List<Usuario> usuarioSenhaVencida();
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.login = ?1")
	Usuario findUserByLogin(String login);

	@Query(value = "SELECT u FROM Usuario u WHERE u.pessoa.id = ?1 or u.login =?2")
	Usuario findByIdAndEmail(Long id, String email);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, 
		value = "INSERT into usuarios_acesso(usuario_id, acesso_id) values (?1, (SELECT id FROM acesso where descricao = 'ROLE_USER'))")
	void inserirAcessoUsuarioPj(Long id);
}