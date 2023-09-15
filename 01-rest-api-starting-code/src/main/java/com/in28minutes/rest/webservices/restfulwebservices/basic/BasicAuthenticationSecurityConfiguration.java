package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class BasicAuthenticationSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, @Autowired HandlerMappingIntrospector introspector) throws Exception {
		return http.headers(headers->headers.frameOptions().disable()).csrf(AbstractHttpConfigurer::disable).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
				auth->auth.antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated()
				).httpBasic(Customizer.withDefaults()).sessionManagement(
				session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				).csrf().disable().build();
	}
}
//HttpMethod.OPTIONS,"/**")
//.requestMatchers(new MvcRequestMatcher(introspector,"/**")).permitAll()

