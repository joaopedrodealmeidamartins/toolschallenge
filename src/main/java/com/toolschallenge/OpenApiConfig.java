package com.toolschallenge;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI toolsChallengeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Pagamentos - Tools Java Challenge [C]")
                        .description("Simulação de API de pagamentos com autorização de transações e estorno.")
                        .version("v1")
                        .contact(new Contact()
                                .name("João Pedro de Almeida Martins")
                                .email("joaomartins.apk@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório do desafio")
                        .url("https://github.com/joaopedrodealmeidamartins/toolschallenge"));
    }
}
