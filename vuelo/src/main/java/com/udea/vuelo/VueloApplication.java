package com.udea.vuelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class VueloApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueloApplication.class, args);
		
	}

//Para activar el intercambio de información entre back y frontend	
@Bean
public CorsFilter corsFilter() {
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	CorsConfiguration config = new CorsConfiguration();
	config.setAllowCredentials(true);
	config.addAllowedOrigin("http://localhost:3000");
	config.addAllowedHeader("*"); //GET, PULL, DELETE..
	config.addAllowedMethod("*");
	source.registerCorsConfiguration("/**",config);
	return new CorsFilter(source);
	
	
}
}
