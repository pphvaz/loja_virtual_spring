package pphvaz.lojaspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pphvaz.lojaspring.model.Usuario;
import pphvaz.lojaspring.repository.UsuarioRepository;

@Service
public class TarefaAutomatizadaService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private SendEmailService sendEmail;
	
	
	// @Scheduled(initialDelay = 2000, fixedDelay = 86400000) /* Roda primeira vez qd o servidor inicia e depois a cada 24hrs */
	@Scheduled(cron = "0 0 11 * * *", zone ="America/Sao_Paulo") // Roda todos os dias às 11hrs com servidor em prod.
	public void notificarUserTrocaSenha() throws InterruptedException {
		
		List<Usuario> usuarios = usuarioRepo.usuarioSenhaVencida();
		
		
		for (Usuario usuario : usuarios) {
			
			StringBuilder msg = new StringBuilder();
			
			msg.append("Olá, ").append(usuario.getPessoa().getNome()).append("<br/>");	
			msg.append("Está na hora de trocar a senha!").append("<br/>");
			msg.append("Troque clicando no link abaixo:").append("<br/><br/>");
			msg.append("<b><u>LINK</u></b>").append("<br/><br/>");
			msg.append("Loja Virtual Corporation™");
			
			sendEmail.enviarEmailHtml("Senha expirada!", msg.toString(), usuario.getLogin());
			
			Thread.sleep(3000);
			
		}
		
	}

}
