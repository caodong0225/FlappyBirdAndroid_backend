package caodong0225.flappybird_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Configuration for Singularity Application
 * Provides API documentation for public and private endpoints.
 *
 * @author jyzxc
 * @since 2024-10-26
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customSwagger() {
        return new OpenAPI()
                .info(new Info()
                        .title("Flappy Bird API")
                        .version("1.0.0")
                        .description("API documentation for flappy bird backend."));

    }
}
