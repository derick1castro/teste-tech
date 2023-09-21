package com.backend.Manager_restaurant.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    Contact contact = new Contact()
            .name("Dérick Castro Domingos")
            .email("derick1castro@gmail.com")
            .url("https://www.linkedin.com/in/castroderick/");

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful Restaurant Management with Java 17 and Spring Boot 3")
                        .version("v1")
                        .description("API RESTful capaz de gerenciar os restaurantes e os produtos do seu cardápio. Esta API permite criar, atualizar, excluir e recuperar informações sobre restaurantes, produtos e promoções.")
                        .contact(contact)
                        .termsOfService("https://www.exemplo.com/termos-de-servico")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://www.apache.org/licenses/LICENSE-2.0")
                        )
                );
    }

}
