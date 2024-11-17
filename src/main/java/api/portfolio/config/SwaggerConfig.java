package api.portfolio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Backend Portfolio (Java): Mis Proyectos \uD83D\uDC69\u200D\uD83D\uDCBB")
                        .version("1.0.0")
                        .description("""
                                        👩‍💻 Tema: ¡API para la gestión de Portfolios. Detalles: API RESTful utilizando Java, Spring Boot y PostgreSQL. La API debe permitir la creación, lectura, actualización y eliminación de proyectos en una base de datos relacional. Relaciones Entre Entidades: User tiene una relación de uno a muchos con Project, Experience, Education, y Skill.
                                        User tiene una relación de uno a uno con Contact.
                                        Project, Experience, Education, y Skill tienen una relación de muchos a uno con User."""
                        )
                );
    }
}