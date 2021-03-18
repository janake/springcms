package org.prodet.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/node/*/edit", "/admin", "/admin/**", "/node/new").authenticated()
//			.antMatchers("/secure/**").access("hasRole('ROLE_ADMIN')").
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/security_check")
			.defaultSuccessUrl("/")
		.and()
			.logout()
			.logoutUrl("/logout").permitAll();

	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.inMemoryAuthentication()
//			.withUser("admin@example.com")
//			.password("pwd")
//			.roles("admin");
//	}
	
//	@Autowired
//	public void configureGlobal(
//			AuthenticationManagerBuilder auth,
//			UserDetailsService userDetailsService) throws Exception {
//		
//		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//	
//	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth, 
			UserDetailsService userDetailsService) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
