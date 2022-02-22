package br.com.thiago.psimanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.thiago.psimanager.security.AuthFilter;
import br.com.thiago.psimanager.security.TokenService;
import br.com.thiago.psimanager.service.UsuarioService;

@Configuration
@EnableWebSecurity
@Profile(value = {"prod", "test"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//@Autowired
	//private DataSource dataSource;
	
	@Autowired
	private UsuarioService serviceUsuario;
	
	@Autowired
	private TokenService serviceToken;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Override//Configuração de acesso aos recursos (URLs) do projeto
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/auth").permitAll()
			.antMatchers("/home/**").permitAll()
			.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
			.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
			.antMatchers(HttpMethod.DELETE, "/api/atendimentos/*").hasRole("ADM")
			.anyRequest().authenticated()
			.and()
			//.httpBasic();//No lugar de formLogin()
			
			//autenticação via formulario. Para via token, tem que desabilitar
			//.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/atendimentos/todos", true).permitAll())
			//.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/home"))
			//.csrf().disable();

			//autenticação via Token
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new AuthFilter(serviceToken, serviceUsuario), UsernamePasswordAuthenticationFilter.class);
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
		//super.configure(web);
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
