package br.com.darlison.portifolio.application.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                description = "Serviço de gestão de Portifólios",
                version = "1.0.0",
                title = "Serviço de Portifólio"
        )
)
public class SwaggerConfig {
}
