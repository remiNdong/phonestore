package com.phonestore.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{ 		
		 http.csrf().disable()
		    .sessionManagement()
	          .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	          
	          .cors().configurationSource(new CorsConfigurationSource() {
	 			 @Override
	 			 public CorsConfiguration getCorsConfiguration(HttpServletRequest 
	 			request) {
	 			 CorsConfiguration config = new CorsConfiguration();
	 			 
	 			config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	 			 config.setAllowedMethods(Collections.singletonList("*"));
	 			 config.setAllowCredentials(true);
	 			 config.setAllowedHeaders(Collections.singletonList("*"));
	 			 config.setExposedHeaders(Arrays.asList("Authorization"));
	 			 config.setMaxAge(3600L);
	 			 return config;
	 			 }
	 			 }).and()
	          
	          	          
		    .authorizeHttpRequests()
		    .requestMatchers("/api/marques/**").permitAll()
		    .requestMatchers("/api/reparations/**").permitAll()
		    .requestMatchers("/api/associations/liste/**").permitAll()
		    .requestMatchers("/api/associations/listepratiquees/**").permitAll()
		    .requestMatchers("/api/modeles").permitAll()
		    .requestMatchers("/api/modeles/modele/**").permitAll()
		    .requestMatchers("/api/modeles/taille/**").permitAll()
		    .requestMatchers("/api/modeles/tailleEcranRange/**").permitAll()
		    .requestMatchers("/api/modeles/capacite/**").permitAll()
		    .requestMatchers("/api/modeles/prix/**").permitAll()
		    .requestMatchers("/api/image/loadImage/**").permitAll()
		    .requestMatchers("/api/image/uploadImage/**").hasAnyAuthority("EMP")
		    .requestMatchers("/api/image/delete/**").hasAnyAuthority("EMP")
		    .requestMatchers("/api/modeles/addmodele").hasAnyAuthority("EMP")
		    .requestMatchers("/api/modeles/updatemodele").hasAnyAuthority("EMP")
		    .requestMatchers("/api/associations/addassociation").hasAnyAuthority("EMP")
		    .requestMatchers("/api/associations/updateassociation").hasAnyAuthority("EMP")
		    .requestMatchers("/api/associations/one/**").hasAnyAuthority("EMP")
		    .requestMatchers("/api/usagers/all").hasAnyAuthority("EMP")
		    .requestMatchers("/api/usagers/allemployes").hasAnyAuthority("ADMIN")
		    .requestMatchers("/api/usagers/all/**").hasAnyAuthority("EMP")
		    .requestMatchers("/api/usagers/one/**").hasAnyAuthority("EMP")
		    .requestMatchers("/api/usagers/oneself/**").hasAnyAuthority("EMP","USER")
		    .requestMatchers("/api/prestations/list/**").hasAnyAuthority("EMP","USER")
		    .requestMatchers("/api/prestations/one/**").hasAnyAuthority("EMP","USER")
		    .requestMatchers("/api/prestations/addprestation").hasAnyAuthority("EMP")
		  
		  /*
		    .requestMatchers("/api/all/**").hasAnyAuthority("ADMIN","USER")
		   
		   
		    .anyRequest().authenticated()
		    
		    */
		    
		    .and().addFilterBefore(new JWTAuthorizationFilter(), BasicAuthenticationFilter.class);
		    
		   
		 return http.build();
		
	}
	

}