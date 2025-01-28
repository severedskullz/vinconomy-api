package com.skully.vinconomy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				// Error pages, like 400's 401's etc. need to be permitted, otherwise we get a 403 Forbidden trying to display a 400 error and message
				//.dispatcherTypeMatchers(jakarta.servlet.DispatcherType.ERROR).permitAll()
				.anyRequest().permitAll()
				
			);
		
		http.sessionManagement( (sess) -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			
		//http.formLogin(Customizer.withDefaults());
		//http.logout(Customizer.withDefaults());
		
		http.cors((cors) -> cors.disable());
		http.csrf( (csrf) -> csrf.disable());
		http.formLogin( (form) -> form.disable() );
		http.httpBasic(Customizer.withDefaults());

	
		return http.build();
	}
	
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    	
}