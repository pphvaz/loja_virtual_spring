package pphvaz.lojaspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pphvaz.lojaspring.model.Pessoa;
import pphvaz.lojaspring.model.PessoaJuridica;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	
	PessoaJuridica findByCnpj(String cnpj);
	
}