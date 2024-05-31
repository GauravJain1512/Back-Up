package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.springsecurity.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
			.authorizeRequests()
			.antMatchers("/signin").permitAll()
			.antMatchers("/public/**").hasRole("NORMAL")
			.antMatchers("/users/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/signin")
			.loginProcessingUrl("/dologin")
			.defaultSuccessUrl("/users/");
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	// Role -high level overview

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	

}
