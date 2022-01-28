package br.com.thiago.psimanager;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/home/**").permitAll()
			.anyRequest().authenticated().and()
			//.httpBasic();//No lugar de formLogin()
			.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/atendimentos/todos", true).permitAll())
			.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/home"))
			.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		
		//UserDetails user = User.builder().username("t").password(encoder.encode("t")).roles("ADM").build();
		
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder);//.withUser(user);
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
