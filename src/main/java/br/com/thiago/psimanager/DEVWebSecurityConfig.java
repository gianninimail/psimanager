package br.com.thiago.psimanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.thiago.psimanager.service.UsuarioService;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class DEVWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService serviceUsuario;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Override//Configuração de acesso aos recursos (URLs) do projeto
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/api/**").permitAll()
			.and()
			.csrf().disable();
	}
	
	@Override//Metodo para configurar o modulo de autenticação
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//Para usuário em memória
		//UserDetails user = User.builder().username("t").password(encoder.encode("t")).roles("ADM").build();
		
		//Para usar JDBC manualmente
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		//auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder);//.withUser(user);
		
		//Para usar serviço de autenticação automática do Spring
		auth.userDetailsService(serviceUsuario).passwordEncoder(new BCryptPasswordEncoder(16));
	}
	
	@Override//Metodo para configurações aos recusos estáticos (js, css, imagens, etc...)
	public void configure(WebSecurity web) throws Exception {

		web.ignoring()
        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}

	//Para utilização de usuários em memória
	/*@Bean
	@Override
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation")
		UserDetails user = User.withDefaultPasswordEncoder().username("t").password("t").roles("ADM").build();

		return new InMemoryUserDetailsManager(user);
	}*/
}
