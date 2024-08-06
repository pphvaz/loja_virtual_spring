package pphvaz.lojaspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pphvaz.lojaspring.model.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long>{
	
	PessoaFisica findByCpf(String cpf);

	
}