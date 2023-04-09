package com.library.config;

// import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
// import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Server localServer = new Server();
        localServer.url("http://localhost:8080");
        localServer.setDescription("local");

        return new OpenAPI()
                .servers(List.of(localServer))
                // .components(new Components().addSecuritySchemes("bearer-key",
                //         new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info().title("PUB Course API")
                        .description("")
                        .version("v0.0.1")
                        .license(new License().name("Java TM").url("")));
    }

}
