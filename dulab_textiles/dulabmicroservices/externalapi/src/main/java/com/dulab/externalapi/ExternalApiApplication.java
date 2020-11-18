package com.dulab.externalapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Objects;

@SpringBootApplication(scanBasePackages = "com.dulab")
@EnableResourceServer
@EnableCaching
public class ExternalApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ExternalApiApplication.class, args);
	}

	/**
	 * This method is used to configure CORS.
	 * @return WebMvcConfigurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(final CorsRegistry registry) {
				registry.addMapping("/external.api/**").allowedOrigins("*");
			}
		};
	}

	/**
	 * This method is used to create a JDBC token store.
	 * @param dataSourceInput JDBC data source
	 * @return returns {@link JdbcTokenStore}
	 */
	@Bean("jdbcTokenStore")
	public TokenStore tokenStore(final DataSource dataSourceInput) {
		return new JdbcTokenStore(Objects.requireNonNull(dataSourceInput));
	}

}
