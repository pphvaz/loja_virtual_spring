package pphvaz.lojaspring.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/* Criar autenticacao e retornar a autenticacao */

@Service
@Component
public class JWTTokenAutenticacaoService {

	/* TEMPO DE VALIDADE PARA EXPIRAR O TOKEN 11 DIAS*/
	private static final long EXPIRATION_TIME = 1999999999;
	
	private static final String SECRET = "doqhwwo-@db€e2-12dsad1!";
	
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
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
	
}
