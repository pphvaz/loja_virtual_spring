package pphvaz.lojaspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pphvaz.lojaspring.model.PessoaJuridica;

@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica, Long> {

	PessoaJuridica findByCnpj(String cnpj);

	PessoaJuridica findByInscEstadual(String inscEstadual);

}