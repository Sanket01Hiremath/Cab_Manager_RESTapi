package com.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AppConfig {
	@Bean
	public SecurityFilterChain masaiSecurityFilter(HttpSecurity http) throws Exception {
		
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.POST, "/masaiCab/user/register","/masaicab/driver/register").permitAll()
		//.anyRequest().authenticated()
		.antMatchers(HttpMethod.GET,"/masaiCab/user/login").authenticated()
		.antMatchers(HttpMethod.GET,"/masaiCab/user/findride").authenticated()
		.antMatchers(HttpMethod.PUT,"/masaicab/user/book/{driverId}/{x}/{y}").authenticated()
		.antMatchers(HttpMethod.GET,"/masaicab/users").hasAnyRole("Admin")
		.antMatchers(HttpMethod.PUT,"/masaicab/user/{userId}").authenticated()
		.and()
		.addFilterAfter(new jwdTokenGenerator(), BasicAuthenticationFilter.class)
		.addFilterBefore(new jwtTokenValidator(), BasicAuthenticationFilter.class)
		.httpBasic();

		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
	
}
