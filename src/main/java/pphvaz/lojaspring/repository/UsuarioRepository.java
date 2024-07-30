package pphvaz.lojaspring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pphvaz.lojaspring.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.login = ?!")
	Usuario findUserByLogin(String login);
}
