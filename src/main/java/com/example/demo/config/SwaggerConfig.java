package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controllers"))
				.paths(PathSelectors.any()).build()
				.securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
				.securityContexts(Arrays.asList(securityContext()))
				.apiInfo(apiInfo());
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/**"))
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("ADMIN", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("Token Access", authorizationScopes));
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("JWT Spring Boot REST API")
	            .description("Um exemplo de aplicação JWT com Spring Boot REST API")
	            .version("1.0.0")
	            .license("Apache License Version 2.0")
	            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
	            .contact(new Contact("Bruno Petry", "https://spring.io/projects/spring-boot", "bruno@gmail.com.br"))
	            .build();
	}

}
