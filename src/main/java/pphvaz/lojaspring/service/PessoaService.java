package pphvaz.lojaspring.service;

import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pphvaz.lojaspring.model.PessoaFisica;
import pphvaz.lojaspring.model.PessoaJuridica;
import pphvaz.lojaspring.model.Usuario;
import pphvaz.lojaspring.repository.PessoaFisicaRepository;
import pphvaz.lojaspring.repository.PessoaJuridicaRepository;
import pphvaz.lojaspring.repository.UsuarioRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaJuridicaRepository pjRepo;

	@Autowired
	private PessoaFisicaRepository pfRepo;

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private SendEmailService sendEmailServ;

	/*
	 * APLICAVEL A AMBAS
	 */

	public void deleteById(Long id) {
		pjRepo.deleteById(id);
	}

	/*
	 * PESSOA JURIDICA
	 */
	
	
	/* FIND PESSOA JURIDICA BY ID */
	public PessoaJuridica pessoaJuridicaFindById(Long idRequisitado) {
		return (PessoaJuridica) pjRepo.findById(idRequisitado).orElse(null);
	}

	/* SALVAR PESSOA JURIDICA */
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) {

		for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
			pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
			pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
		}

		pessoaJuridica = pjRepo.save(pessoaJuridica);

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

			usuarioRepo.inserirAcessoUsuario(usuario.getId());

			StringBuilder mensagemHtml = new StringBuilder();

			mensagemHtml.append("<b>Segue abaixo seus dados de acesso para a Loja Virtual </b><br/>");
			mensagemHtml.append("<b>Login: </b>" + pessoaJuridica.getEmail() + "<br/>");
			mensagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			mensagemHtml.append("Obrigado!");

			try {
				sendEmailServ.enviarEmailHtml("Acesso Gerado para Loja Virtual", mensagemHtml.toString(),
						pessoaJuridica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return pessoaJuridica;
	}
	
	/* FIND PESSOA JURIDICA BY CNPJ*/
	public PessoaJuridica findByCnpj(String cnpj) {
		return pjRepo.findByCnpj(cnpj);
	}

	/* FIND PESSOA FISICA BY INSCRICAO ESTADUAL */
	public PessoaJuridica findByInscEstadual(String inscEstadual) {
		return pjRepo.findByInscEstadual(inscEstadual);
	}

	/*
	 * PESSOA FISICA
	 */

	
	
	/*  FIND PESSOA FISICA BY ID */
	public PessoaFisica pessoaFisicaFindById(Long idRequisitado) {
		return (PessoaFisica) pfRepo.findById(idRequisitado).orElse(null);
	}
	
	/*  SALVAR PESSOA FISICA */
	public PessoaFisica salvarPessoaFisica(PessoaFisica pf) {
		
		
		for (int i = 0; i < pf.getEnderecos().size(); i++) {
			pf.getEnderecos().get(i).setPessoa(pf);
			//pf.getEnderecos().get(i).setEmpresa(pj);
		}

		pf = pfRepo.save(pf);

		Usuario usuario = usuarioRepo.findByIdAndEmail(pf.getId(), pf.getEmail());

		if (usuario == null) {
			usuario = new Usuario();

			usuario.setDataAtualSenha(Calendar.getInstance().getTime());
			usuario.setEmpresa(pf.getEmpresa());
			usuario.setPessoa(pf);
			usuario.setLogin(pf.getEmail());

			String senha = RandomStringUtils.random(10, true, true);
			String senhaCript = new BCryptPasswordEncoder().encode(senha);

			usuario.setSenha(senhaCript);

			usuarioRepo.save(usuario);

			usuarioRepo.inserirAcessoUsuario(usuario.getId());

			StringBuilder mensagemHtml = new StringBuilder();

			mensagemHtml.append("<b>Segue abaixo seus dados de acesso para a Loja Virtual </b><br/>");
			mensagemHtml.append("<b>Login: </b>" + pf.getEmail() + "<br/>");
			mensagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			mensagemHtml.append("Obrigado!");

			try {
				sendEmailServ.enviarEmailHtml("Acesso Gerado para Loja Virtual", mensagemHtml.toString(),
						pf.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return pf;
	}
	
	/*  FIND PESSOA FISICA BY CPF */
	public PessoaFisica findByCpf(String cpf) {
		return pfRepo.findByCpf(cpf);
	}

}
