package pphvaz.lojaspring.service;

import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pphvaz.lojaspring.model.PessoaFisica;
import pphvaz.lojaspring.model.PessoaJuridica;
import pphvaz.lojaspring.model.Usuario;
import pphvaz.lojaspring.repository.PessoaRepository;
import pphvaz.lojaspring.repository.UsuarioRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private SendEmailService sendEmailServ;

	/*
	 * APLICAVEL A AMBAS
	 */

	public void deleteById(Long id) {
		pessoaRepo.deleteById(id);
	}

	/*
	 * PESSOA JURIDICA
	 */

	public PessoaJuridica pessoaJuridicaFindById(Long idRequisitado) {
		return (PessoaJuridica) pessoaRepo.findById(idRequisitado).orElse(null);
	}

	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) {
		
		for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
			pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
			pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
		}
		
		pessoaJuridica = pessoaRepo.save(pessoaJuridica);
		
		Usuario usuario = usuarioRepo.findByIdAndEmail(pessoaJuridica.getId(), pessoaJuridica.getEmail());
		
		if (usuario == null) {
			usuario = new Usuario();
			
			usuario.setDataAtualSenha(Calendar.getInstance().getTime());
			usuario.setEmpresa(pessoaJuridica);
			usuario.setPessoa(pessoaJuridica);
			usuario.setLogin(pessoaJuridica.getEmail());
			
			String senha = RandomStringUtils.random(10, true, true);
			String senhaCript = new BCryptPasswordEncoder().encode(senha);
			
			usuario.setSenha(senhaCript);
			
			usuarioRepo.save(usuario);
			
			usuarioRepo.inserirAcessoUsuarioPj(usuario.getId());
			
			StringBuilder mensagemHtml = new StringBuilder();
			
			mensagemHtml.append("<b>Segue abaixo seus dados de acesso para a Loja Virtual </b><br/>");
			mensagemHtml.append("<b>Login: </b>" + pessoaJuridica.getEmail() + "<br/>");
			mensagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			mensagemHtml.append("Obrigado!");
			
			try {				
				sendEmailServ.enviarEmailHtml("Acesso Gerado para Loja Virtual", mensagemHtml.toString(), pessoaJuridica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return pessoaJuridica;
	}

	public PessoaJuridica findByCnpj(String cnpj) {
		return (PessoaJuridica) pessoaRepo.findByCnpj(cnpj);
	}

	/*
	 * PESSOA FISICA
	 */

	public PessoaFisica pessoaFisicaFindById(Long idRequisitado) {
		return (PessoaFisica) pessoaRepo.findById(idRequisitado).orElse(null);
	}

}
