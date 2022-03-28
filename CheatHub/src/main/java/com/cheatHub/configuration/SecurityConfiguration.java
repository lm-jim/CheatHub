package com.cheatHub.configuration;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	RepositoryUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/iniciarSesion").permitAll(); // CAMBIAR O BORRAR CONTROLADOR
		http.authorizeRequests().antMatchers("/css/estilo.css").permitAll();																	
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/registerUser").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/search").permitAll();
		http.authorizeRequests().antMatchers("/buscarUsuario").permitAll();
		http.authorizeRequests().antMatchers("/newAccount").permitAll();
		http.authorizeRequests().antMatchers("/juego/**").permitAll();  //REVISAR LAS URL DINÁMICAS
		http.authorizeRequests().antMatchers("/category/**").permitAll();
		http.authorizeRequests().antMatchers("/publicacion/**").permitAll();
		http.authorizeRequests().antMatchers("/user/**").permitAll();
		
		// /* significando cualquier ruta

		// Private pages (all other pages)
		http.authorizeRequests().anyRequest().authenticated();
		
		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("userName");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		//http.formLogin().failureUrl("/loginerror");
		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		// Disable CSRF at the moment
		//http.csrf().disable();
	}

}
