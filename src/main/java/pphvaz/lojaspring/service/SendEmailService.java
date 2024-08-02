package pphvaz.lojaspring.service;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	
	private String from = "pedro.alves@pvztech.com.br";
	private String senha = "P99@j1997";
	
	@Async
	public void enviarEmailHtml(String assunto, String mensagem, String emailDestino) {
		
		Properties prop = new Properties();
		
		prop.put("mail.smtp.host", "smtppro.zoho.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");
		prop.put("mail.smtp.socketFactory.port", "587");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		// create new session with an authenticator
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, senha);
			}
		};
		
		Session session = Session.getInstance(prop, auth);
		
		try {
			
			Address[] to = InternetAddress.parse(emailDestino);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject(assunto);
			
			message.setContent(mensagem, "text/html; charset=utf-8");
			// message.setContent(message, emailDestino);
	        Transport.send(message);
	        
	        System.out.println("Mail successfully sent..");
	        
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
}