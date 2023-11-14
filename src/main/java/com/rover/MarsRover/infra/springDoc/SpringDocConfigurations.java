package com.rover.MarsRover.infra.springDoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Mars Rover Cata")
                        .description("API Rest de la aplicación para el Mars Rover Cata, que contiene las funcionalidades para mover y girar el Rover" +
                                " validando que el movimiento sea dentro del mapa o si se encuentra algún obstaculo")
                        .contact(new Contact()
                                .name("Desarrollador Victor Agudelo")
                                .email("viactur11@gmail.com")
                                .url("https://www.linkedin.com/in/victoragudelodsw/")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://rover.MarsRover/api/licencia")
                        )
                );
    }
}
