package pphvaz.lojaspring.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import pphvaz.lojaspring.ApplicationContextLoad;
import pphvaz.lojaspring.model.Usuario;
import pphvaz.lojaspring.repository.UsuarioRepository;

/* Criar autenticacao e retornar a autenticacao */

@Service
@Component
public class JWTTokenAutenticacaoService {

	/* TEMPO DE VALIDADE PARA EXPIRAR O TOKEN 11 DIAS*/
	private static final long EXPIRATION_TIME = 950400000; 
	
	private static final String SECRET = "doqhwwsad1!";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/* Gera o token e da a resposta para o client*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception{
		
		/* Montagem do TOKEN */
		String JWT = Jwts.builder().setSubject(username)
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		
		String token = TOKEN_PREFIX + " " + JWT;
		
		/* Dá a reposta para tela e para o client */
		response.addHeader(HEADER_STRING, token);
		
		liberacaoCors(response);
		
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
	
	
	/* Retorna o usuario validado com token ou retorna null*/
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		String token = request.getHeader(HEADER_STRING);
		
		try {
			
			if (token != null) {
				
				String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
				
				/* Faz a validacao do token do usuario e obtem o user */
				
				String user = Jwts.parser()
						.setSigningKey(SECRET)
						.parseClaimsJws(tokenLimpo)
						.getBody().getSubject();
				
				if (user != null) {
					
					Usuario usuario = ApplicationContextLoad
							.getApplicationContext()
							.getBean(UsuarioRepository.class).findUserByLogin(user);
					
					if(usuario != null) {
						return new UsernamePasswordAuthenticationToken(
								usuario.getLogin(), 
								usuario.getSenha(), 
								usuario.getAuthorities());
					} 
				}
			}
		} catch (SignatureException e) {
			response.getWriter().write("Token inválido");
		} catch (ExpiredJwtException e){
			response.getWriter().write("Token expirado, efetue login novamente");
		} finally {			
			liberacaoCors(response);
		}
		
		return null;
	}
	
	/* Fazendo liberação contra erro de CORS no navegador*/
	private void liberacaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
	
	
}
