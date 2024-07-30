package pphvaz.lojaspring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import pphvaz.lojaspring.model.Usuario;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter{

	
	/* Configurando o gerenciador de autenticação */
	public JwtLoginFilter(String url, AuthenticationManager authenticationManager) {
		
		/* Obrigado a autenticação da URL */ 
		super(new AntPathRequestMatcher(url));
		
		/* Gerenciador de autenticação */
		setAuthenticationManager(authenticationManager);
		
	}
	

	/* Retorna o usuário ao processar a autenticação */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		/* Obtem o usuario */ 
		Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		
		/* Retorna user com login e senha */
		return getAuthenticationManager().
				authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		try {
			new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
