package br.com.jeff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Configuration
public class OpenIAConfig {

    private Logger logger = Logger.getLogger(OpenIAConfig.class.getName());

    @Value("${openai.api.key}")
    String openApiKey;

    @Bean
    RestTemplate template() {
        logger.info("Initializer RestTemplate");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
