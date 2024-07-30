package pphvaz.lojaspring.security;

import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pphvaz.lojaspring.service.ImplementacaoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener{
	
	
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	/* Irá consultar o user no banco com o Spring Security*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementacaoUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
		
	@Override
	public void configure(WebSecurity web) throws Exception {
	// Ignorando URL no momento para não autenticar:
	web.ignoring()
		.antMatchers(HttpMethod.GET, "/acesso/{id}", "/acesso", "/acesso/buscarPorDescricao/{descricao}")
		.antMatchers(HttpMethod.POST, "/loja_virtual/acesso", "/acesso")
		.antMatchers(HttpMethod.DELETE, "/acesso/{id}", "/acesso");
	}
}

