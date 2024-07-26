package pphvaz.lojaspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pphvaz.lojaspring.model.Acesso;
import pphvaz.lojaspring.repository.AcessoRepository;

@Service
public class AcessoService {
	
	@Autowired
	private AcessoRepository acessoRepo;

	public Acesso save(Acesso acesso) {
		return acessoRepo.save(acesso);
	}

	public Acesso findById(Long idRequisitado) {
        return acessoRepo.findById(idRequisitado)
                .orElse(null);
    }

	public void deleteById(Long id) {
		acessoRepo.deleteById(id);
	}

	public List<Acesso> buscarAcessoPorDesc(String desc) {
		return acessoRepo.buscarAcessoPorDesc(desc.trim().toUpperCase());
	}
}