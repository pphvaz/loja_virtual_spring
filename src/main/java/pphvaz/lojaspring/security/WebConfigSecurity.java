package pphvaz.lojaspring.security;

import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pphvaz.lojaspring.service.ImplementacaoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener{
	
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.disable()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/index").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			
			/* redireciona ou da um retorno para o index quando desloga */
			.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
			
			/* mapeia o logout do sistema */
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			
			/*  Filtra as requisições para login de JWT */
			.and().addFilterAfter(new JwtLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			
			.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
	/* Irá consultar o user no banco com o Spring Security*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementacaoUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
		
	@Override
	public void configure(WebSecurity web) throws Exception {
	// Ignorando URL no momento para não autenticar:
	// web.ignoring()
	//	.antMatchers(HttpMethod.GET, "/acesso/{id}", "/acesso", "/acesso/buscarPorDescricao/{descricao}")
	//	.antMatchers(HttpMethod.POST, "/loja_virtual/acesso", "/acesso")
	//	.antMatchers(HttpMethod.DELETE, "/acesso/{id}", "/acesso");
	}
}

